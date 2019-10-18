package com.dc.dlsbcontroller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dc.base.client.DataDictionaryService;
import com.dc.base.client.OrgService;
import com.dc.base.controller.BaseController;
import com.dc.base.model.SYS_ORG_INFO;
import com.dc.common.DclxCfg;
import com.dc.common.model.CurrUserInfo;
import com.dc.common.util.DataUtils;
import com.dc.common.util.DateUtils;
import com.dc.dlsb.client.DlsbOmsClient;
import com.dc.dlsb.client.DlsbRmClient;
import com.dc.dlsb.model.DmeDrcDdrbCfg;
import com.dc.dlsb.model.DmeDrcSumFgpData;
import com.dc.dlsb.model.DmeDrcSumJzData;
import com.dc.dlsb.model.OmsJzglJzxx;
import com.dc.dlsb.staticval.DlsbEnum;
import com.dc.dlsb.vomodel.SbDataDbMod;
import com.dc.dlsb.vomodel.SbDataModVo;
import lombok.extern.slf4j.Slf4j;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author wanggang
 * @description 电量上报-controller
 * @date 2019/2/14
 */
@SuppressWarnings("deprecation")
@Controller
@RequestMapping(value = "/dlsb")
@Slf4j
public class DlsbController extends BaseController {

     @Autowired
     private DataDictionaryService datadictionaryService;
     @Autowired
     private OrgService orgService;
     @Autowired
     private DlsbRmClient dlsbRmClient;
     @Autowired
     private DlsbOmsClient dlsbOmsClient;

     @Value("${dlsb.sbdate.sthour}")
     private int sthour;//填报开始时间
     @Value("${dlsb.sbdate.enhour}")
     private int enhour;//填报截止时间
     @Autowired
     private DclxCfg dclxCfg;
     @Value("${excelmodel.dlsbmodel}")
     private String dlsbmodel;
     /**
      * @return
      * @Author wg
      * @Description //到电量上报index页面
      * @Date 14:40 2019/2/14
      * @Param
      */
     @RequestMapping({"/goDlsbIndexPage.html"})
     public ModelAndView goDlsbIndexPage() {
          ModelAndView mv = new ModelAndView();
          JSONObject dlsb = new JSONObject();
          CurrUserInfo userinfo = this.getCurUserInfo();
          String userid = userinfo.getUserid();
          String ORGID = datadictionaryService.getParentTreeKey("DLSB_ORG_TREE",userid);
          SYS_ORG_INFO org = orgService.findById(ORGID);
          String czName = org.getOrgname();
          Date d = DateUtils.getDateBefore(new Date(), 1);
          String date = DateUtils.formatDate(d, "yyyy-MM-dd");
          dlsb.put("ORGID",ORGID);
          dlsb.put("czName",czName);
          dlsb.put("sdate",date);
          mv.addObject("dlsb", dlsb);
          mv.setViewName(DlsbEnum.PAGE_DLSB_INDEX.getKey());
          return mv;
     }

     /**
      * @Author wg
      * @Description //根据sbOmsOrgid查询rm用户配置表获取该机构可填报数据
      * @Date 11:29 2019/2/15
      * @Param
      * @return
      */
     @RequestMapping({"/getDateEnumBySbOmsOrgid.do"})
     @ResponseBody
     public JSONObject getDateEnumBySbOmsOrgid(String orgid,String date){
          JSONObject json = new JSONObject();
          List<SbDataModVo> modVoList = new ArrayList<>();
          List<DmeDrcDdrbCfg> cfgList = new ArrayList<>();
          if (StringUtils.isNotEmpty(orgid)){
               cfgList = dlsbRmClient.findBysbOmsOrgid(orgid);
               if (cfgList != null && cfgList.size()>0){
                    for (DmeDrcDdrbCfg cfg:cfgList){
                         if (StringUtils.isNotEmpty(cfg.getOmsDcId())){
                              List<OmsJzglJzxx> jzxxList = dlsbOmsClient.findOmsJzxxByDcid(cfg.getOmsDcId());
                              if (jzxxList != null && jzxxList.size()>0){
                                   for (OmsJzglJzxx jzxx:jzxxList){
                                        SbDataModVo modVo = new SbDataModVo();
                                        modVo.setSbdcid(cfg.getDlldbId());
                                        modVo.setSbdcmc(cfg.getName());
                                        modVo.setDclx(cfg.getCfgType());
                                        modVo.setSbjzbh(jzxx.getJzbh());
                                        modVo.setSbjzmc(jzxx.getJzmc());
                                        modVo.setShujurq(date);
                                        DmeDrcSumJzData jzData = dlsbRmClient.getJzSbDataBy(date,jzxx.getJzbh(),cfg.getDlldbId());
                                        if (jzData != null){
                                             modVo.setSbshuju(jzData.getDatanum());
                                             modVo.setJlsj(jzData.getJlsj());
                                        }
                                        modVoList.add(modVo);
                                   }
                              }else{
                                   addElmToList(cfg,modVoList,date);
                                   /*SbDataModVo modVo = new SbDataModVo();
                                   modVo.setSbdcid(cfg.getDlldbId());
                                   modVo.setSbdcmc(cfg.getName());
                                   modVo.setDclx(cfg.getCfgType());
                                   modVo.setSbjzmc("-");
                                   modVo.setShujurq(date);
                                   DmeDrcSumFgpData dcData = dlsbRmClient.getDcSbDataByDateAndDcid(date,cfg.getDlldbId());
                                   if (dcData != null){
                                        modVo.setJlsj(dcData.getJlsj());
                                        modVo.setSbshuju(dcData.getAbasedata());
                                   }
                                   modVoList.add(modVo);*/
                              }
                         }else{
                              addElmToList(cfg,modVoList,date);
                              /*SbDataModVo modVo = new SbDataModVo();
                              modVo.setSbdcid(cfg.getDlldbId());
                              modVo.setSbdcmc(cfg.getName());
                              modVo.setDclx(cfg.getCfgType());
                              modVo.setSbjzmc("-");
                              modVo.setShujurq(date);
                              DmeDrcSumFgpData dcData = dlsbRmClient.getDcSbDataByDateAndDcid(date,cfg.getDlldbId());
                              if (dcData != null){
                                   modVo.setJlsj(dcData.getJlsj());
                                   modVo.setSbshuju(dcData.getAbasedata());
                              }
                              modVoList.add(modVo);*/
                         }
                    }

               }
          }
          json.put("rows",modVoList);
          return json;
     }

     /**
      * @Author wg
      * @Description 往list中添加元素
      * @Date 10:06 2019/2/21
      * @Param
      * @return
      */
     private void addElmToList(DmeDrcDdrbCfg cfg,List<SbDataModVo> modVoList,String date){
          SbDataModVo modVo = new SbDataModVo();
          modVo.setSbdcid(cfg.getDlldbId());
          modVo.setSbdcmc(cfg.getName());
          modVo.setDclx(cfg.getCfgType());
          modVo.setSbjzmc("-");
          modVo.setShujurq(date);
          DmeDrcSumFgpData dcData = dlsbRmClient.getDcSbDataByDateAndDcid(date,cfg.getDlldbId());
          if (dcData != null){
               modVo.setJlsj(dcData.getJlsj());
               modVo.setSbshuju(dcData.getAbasedata());
          }
          modVoList.add(modVo);
     }
     /**
      * @Author wg
      * @Description 保存数据
      * @Date 18:14 2019/2/18
      * @Param
      * @return
      */
     @RequestMapping({"/saveSbData.do"})
     @ResponseBody
     public JSONObject saveSbData(@RequestBody List<SbDataModVo> sbDataList) {
          JSONObject json = new JSONObject();
          String now = DateUtils.formatDate(new Date(),DateUtils.DATAFORMAT_YMDHMS);
          List<DmeDrcSumFgpData> saveDcDataList = new ArrayList<>();
          List<DmeDrcSumJzData> saveJzDataList = new ArrayList<>();
          if (sbDataList != null && sbDataList.size()>0){
               for (SbDataModVo sbDataModVo:sbDataList){
                    if (StringUtils.isNotEmpty(sbDataModVo.getSbjzbh())){
                         DmeDrcSumJzData dmeDrcSumJzData = new DmeDrcSumJzData();
                         dmeDrcSumJzData.setOiIdf(sbDataModVo.getSbdcid());
                         dmeDrcSumJzData.setDatadatef(sbDataModVo.getShujurq());
                         if (StringUtils.isEmpty(sbDataModVo.getSbshuju())){
                              dmeDrcSumJzData.setDatanum("0");
                         }else {
                              dmeDrcSumJzData.setDatanum(sbDataModVo.getSbshuju());
                         }
                         dmeDrcSumJzData.setJzbh(sbDataModVo.getSbjzbh());
                         dmeDrcSumJzData.setJzmc(sbDataModVo.getSbjzmc());
                         dmeDrcSumJzData.setJlsj(now);
                         saveJzDataList.add(dmeDrcSumJzData);

                    }else{
                         DmeDrcSumFgpData dmeDrcSumFgpData = new DmeDrcSumFgpData();
                         dmeDrcSumFgpData.setOiIdf(sbDataModVo.getSbdcid());
                         dmeDrcSumFgpData.setOiName(sbDataModVo.getSbdcmc());
                         dmeDrcSumFgpData.setDatadatef(sbDataModVo.getShujurq());
                         if (StringUtils.isEmpty(sbDataModVo.getSbshuju())){
                              dmeDrcSumFgpData.setAbasedata("0");
                         }else {
                              dmeDrcSumFgpData.setAbasedata(sbDataModVo.getSbshuju());
                         }
                         dmeDrcSumFgpData.setJlsj(now);
                         saveDcDataList.add(dmeDrcSumFgpData);
                    }
               }
          }
          SbDataDbMod saveDbMod = new SbDataDbMod();
          saveDbMod.setDcDataList(saveDcDataList);
          saveDbMod.setJzDataList(saveJzDataList);
          int saveSum = dlsbRmClient.saveSbData(saveDbMod);
          if (saveSum == sbDataList.size()){
               json.put("msg","ok");
          }else{
               json.put("msg","数据保存失败!");
          }
          return json;
     }

     /**
      * @Author wg
      * @Description 验证当前时间是否能修改或填报数据
      * @Date 15:33 2019/2/18
      * @Param
      * @return
      */
     @RequestMapping("/checkIsCanSbData.do")
     @ResponseBody
     public JSONObject checkIsCanSbData(@RequestParam(value = "date") String date){
          JSONObject json = new JSONObject();
          Calendar calendar = Calendar.getInstance();
          Date now = calendar.getTime();
          double dnum = DateUtils.getDistanceOfTwoDate(DateUtils.parseDate(date),now);
          if (1 == dnum){
               int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
               if (sthour <= nowHour && nowHour<= enhour){
                    json.put("msg","ok");
               }else {
                    json.put("msg","该数据现在不可修改!");
               }
          }else{
               json.put("msg","该数据现在不可修改!");
          }
          return json;
     }

     /**
      * @Author wg
      * @Description 到电量上报查询Index页面
      * @Date 10:55 2019/2/19
      * @Param
      * @return
      */
     @RequestMapping("/goSearchIndex.html")
     public ModelAndView goDlsbSearchDataIndexPage(){
          ModelAndView mv = new ModelAndView();
          mv.setViewName(DlsbEnum.PAGE_DLSB_SEARCHINDEX.getKey());
          return mv;
     }

     /**
      * @Author wg
      * @Description 到电量上报数据index页面
      * @Date 11:51 2019/2/19
      * @Param
      * @return
      */
     @RequestMapping("/goIndexByDclx.hmle")
     public ModelAndView goSearchSbDataIndex(String dclx){
          ModelAndView mv = new ModelAndView();
          mv.addObject("dclx",dclx);
          Date d = DateUtils.getDateBefore(new Date(), 1);
          String date = DateUtils.formatDate(d, "yyyy-MM-dd");
          mv.addObject("sdate",date);
          mv.setViewName(DlsbEnum.PAGE_DLSB_SEARCH_INDEX.getKey());
          return mv;
     }

     /**
      * @Author wg
      * @Description 根据电厂类型获取电厂及填报数据
      * @Date 14:46 2019/2/19
      * @Param
      * @return
      */
     @RequestMapping("/getSbDataByDclx.do")
     @ResponseBody
     public JSONObject getSbDataByDclx(String dclx,String date){
          JSONObject json = new JSONObject();
          List<SbDataModVo> modVoList = new ArrayList<>();
          String dccfg = dclxCfg.getDclxByName(dclx);
          if (StringUtils.isNotEmpty(dccfg)){
               String[] dccfgs = dccfg.split(",");
               List<DmeDrcDdrbCfg> cfgList = dlsbRmClient.findBydclx(dccfgs);
               if (cfgList !=null && cfgList.size()>0){
                    for (DmeDrcDdrbCfg cfg:cfgList){
                         if (StringUtils.isNotEmpty(cfg.getDlldbId())){
                              SbDataModVo modVo = new SbDataModVo();
                              DmeDrcSumFgpData dcData = dlsbRmClient.getDcSbDataByDateAndDcid(date,cfg.getDlldbId());
                              modVo.setSbdcid(cfg.getDlldbId());
                              modVo.setSbdcmc(cfg.getName());
                              modVo.setDclx(cfg.getCfgType());
                              modVo.setShujurq(date);
                              if (dcData != null){
                                   modVo.setJlsj(dcData.getJlsj());
                                   modVo.setSbshuju(dcData.getAbasedata());
                              }
                              modVoList.add(modVo);
                         }
                    }
               }
          }
          json.put("rows",modVoList);
          return json;
     }

     /**
      * @Author wg
      * @Description 到中调修改上报数据页面
      * @Date 17:22 2019/2/22
      * @Param
      * @return
      */
     @RequestMapping("/goEditPage.do")
     public ModelAndView goZdEditDlPage(String sbdcid,String date){
          ModelAndView mv = new ModelAndView();
          List<SbDataModVo> modVoList = new ArrayList<>();
          if (StringUtils.isNotEmpty(sbdcid)){
               DmeDrcDdrbCfg dccfg = dlsbRmClient.findByDlldbId(sbdcid);
               if (dccfg != null){
                    if (StringUtils.isNotEmpty(dccfg.getOmsDcId())){
                         List<OmsJzglJzxx> jzxxList = dlsbOmsClient.findOmsJzxxByDcid(dccfg.getOmsDcId());
                         if (jzxxList != null && jzxxList.size()>0){
                              for (OmsJzglJzxx jzxx:jzxxList){
                                   SbDataModVo modVo = new SbDataModVo();
                                   modVo.setSbdcid(dccfg.getDlldbId());
                                   modVo.setSbdcmc(dccfg.getName());
                                   modVo.setDclx(dccfg.getCfgType());
                                   modVo.setSbjzbh(jzxx.getJzbh());
                                   modVo.setSbjzmc(jzxx.getJzmc());
                                   modVo.setShujurq(date);
                                   DmeDrcSumJzData jzData = dlsbRmClient.getJzSbDataBy(date,jzxx.getJzbh(),dccfg.getDlldbId());
                                   if (jzData != null){
                                        modVo.setSbshuju(jzData.getDatanum());
                                        modVo.setJlsj(jzData.getJlsj());
                                   }
                                   modVoList.add(modVo);
                              }
                         }else {
                              addElmToList(dccfg,modVoList,date);
                         }
                    }else {
                         addElmToList(dccfg,modVoList,date);
                    }
               }
          }
          mv.addObject("list",modVoList);
          mv.setViewName(DlsbEnum.PAGE_DLSB_EDIT_PAGE.getKey());
          return mv;
     }

     /**
      * @Author wg
      * @Description 导出ecxel
      * @Date 11:20 2019/2/25
      * @Param
      * @return
      */
     @RequestMapping("/exportExcel.do")
     public void exportExcel(String date, String dclx , HttpServletRequest request, HttpServletResponse response) {
          try {
               String content = request.getParameter("content");
               String res = DataUtils.getFromBASE64(content);
               log.debug(res);
               Map<String,Object> mapData = new HashMap<String, Object>();
               JSONArray dataArray = JSONArray.parseArray(res);
               List<Map<String,Object>> listItem=new ArrayList<Map<String,Object>>();
               if (dataArray != null && dataArray.size()>0){
                    for (int i=0;i<dataArray.size();i++){
                         JSONObject dataItem = dataArray.getJSONObject(i);
                         Map<String,Object> mapTemp=new HashMap<String, Object>();
                         String sbdcmc = dataItem.getString("sbdcmc");
                         String sbshuju = dataItem.getString("sbshuju");
                         if (StringUtils.isEmpty(sbshuju)){
                              sbshuju = "";
                         }
                         mapTemp.put("sbdcmc",sbdcmc);
                         mapTemp.put("sbshuju",sbshuju);
                         listItem.add(mapTemp);
                    }
               }
               mapData.put("dlsb",listItem);
               String dccfg = dclxCfg.getDclxByName(dclx);
               String outfilename=String.format("%s电量数据.xls", date+dccfg);
               mapData.put("dateDC",date+dccfg);
               String fileName = processFileName(request, outfilename);
               response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
               OutputStream os=response.getOutputStream();
               File file = ResourceUtils.getFile(dlsbmodel);
               InputStream in =new BufferedInputStream(new FileInputStream(file));
               XLSTransformer transformer = new XLSTransformer();
               Workbook workbook = transformer.transformXLS(in, mapData);
               workbook.write(os);

               os.flush();
               os.close();
               System.out.println(String.format("export %s report to excel done.", date));
          }catch (Exception e){
               e.getStackTrace();
          }
     }

     private static String processFileName(HttpServletRequest request, String fileNames) {
          String codedfilename = null;

          try {
               String agent = request.getHeader("USER-AGENT");
               if (null != agent && -1 != agent.indexOf("MSIE") || null != agent && -1 != agent.indexOf("Trident")) {
                    String name = URLEncoder.encode(fileNames, "UTF8");
                    codedfilename = name;
               } else if (null != agent && -1 != agent.indexOf("Mozilla")) {
                    codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
               }
          } catch (Exception var5) {
               var5.printStackTrace();
          }

          return codedfilename;
     }
}
