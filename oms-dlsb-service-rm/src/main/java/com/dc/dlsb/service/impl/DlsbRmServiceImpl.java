package com.dc.dlsb.service.impl;

import com.dc.dlsb.dao.DmeDrcDdrbCfgMapper;
import com.dc.dlsb.dao.DmeDrcSumFgpDataMapper;
import com.dc.dlsb.dao.DmeDrcSumJzDataMapper;
import com.dc.dlsb.model.*;
import com.dc.dlsb.service.DlsbRmService;
import com.dc.dlsb.vomodel.SbDataDbMod;
import com.dc.exception.ServiceException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanggang
 * @description DlsbRmService接口实现
 * @date 2019/2/15
 */
@Service
@Slf4j
public class DlsbRmServiceImpl implements DlsbRmService {

     @Autowired
     private DmeDrcDdrbCfgMapper dmeDrcDdrbCfgDao;
     @Autowired
     private DmeDrcSumFgpDataMapper dmeDrcSumFgpDataDao;
     @Autowired
     private DmeDrcSumJzDataMapper dmeDrcSumJzDataDao;


     @Override
     public List<DmeDrcDdrbCfg> findBysbOmsOrgid(String sbOmsOrgid) {
          DmeDrcDdrbCfgExample example = new DmeDrcDdrbCfgExample();
          DmeDrcDdrbCfgExample.Criteria ca = example.createCriteria();
          ca.andSbOmsOrgidEqualTo(sbOmsOrgid);
          List<DmeDrcDdrbCfg> list = dmeDrcDdrbCfgDao.selectByExample(example);
          return list;
     }

     @Override
     public DmeDrcSumFgpData getDcSbDataByDateAndDcid(String date, String dcid) {
          DmeDrcSumFgpData dmeDrcSumFgpData = new DmeDrcSumFgpData();
          DmeDrcSumFgpDataExample example = new DmeDrcSumFgpDataExample();
          DmeDrcSumFgpDataExample.Criteria ca = example.createCriteria();
          ca.andOiIdfEqualTo(dcid).andDatadatefEqualTo(date);
          List<DmeDrcSumFgpData> list = dmeDrcSumFgpDataDao.selectByExample(example);
          if(list != null && list.size()>0){
               return list.get(0);
          }else {
               return dmeDrcSumFgpData;
          }
     }

     @Override
     public DmeDrcSumJzData getJzSbDataBy(String date, String jzid, String dcid) {
          DmeDrcSumJzData dmeDrcSumJzData = new DmeDrcSumJzData();
          DmeDrcSumJzDataExample jzDataExample = new DmeDrcSumJzDataExample();
          DmeDrcSumJzDataExample.Criteria JzCa = jzDataExample.createCriteria();
          JzCa.andDatadatefEqualTo(date).andJzbhEqualTo(jzid).andOiIdfEqualTo(dcid);
          List<DmeDrcSumJzData> JzList = dmeDrcSumJzDataDao.selectByExample(jzDataExample);
          if (JzList != null && JzList.size()>0){
               return JzList.get(0);
          }else {
               return dmeDrcSumJzData;
          }
     }

     @Transactional(propagation = Propagation.REQUIRED,rollbackFor = ServiceException.class)
     @Override
     public int addNewSbData(SbDataDbMod sbDataDbMod) {
          List<DmeDrcSumFgpData> Dclist = sbDataDbMod.getDcDataList();
          int n = 0;
          if (Dclist != null && Dclist.size()>0){
               for (DmeDrcSumFgpData sumFgpData:Dclist){
                    dmeDrcSumFgpDataDao.insertSelective(sumFgpData);
               }
               n+=Dclist.size();
          }else{
               n+= 0;
          }
          List<DmeDrcSumJzData> JzList = sbDataDbMod.getJzDataList();
          if (JzList != null && JzList.size()>0){
               for (DmeDrcSumJzData jzData:JzList){
                    dmeDrcSumJzDataDao.insertSelective(jzData);
               }
               n+=JzList.size();
          }else {
               n+=0;
          }
          return n;
     }

     @Transactional(propagation = Propagation.REQUIRED,rollbackFor = ServiceException.class)
     @Override
     public int updateSbData(SbDataDbMod sbDataDbMod) {
          List<DmeDrcSumFgpData> Dclist = sbDataDbMod.getDcDataList();
          int n = 0;
          if (Dclist != null && Dclist.size()>0){
               for (DmeDrcSumFgpData sumFgpData:Dclist){
                    DmeDrcSumFgpDataExample sumFgpDataExample = new DmeDrcSumFgpDataExample();
                    DmeDrcSumFgpDataExample.Criteria DcCa = sumFgpDataExample.createCriteria();
                    DcCa.andDatadatefEqualTo(sumFgpData.getDatadatef()).andOiIdfEqualTo(sumFgpData.getOiIdf());
                    dmeDrcSumFgpDataDao.updateByExampleSelective(sumFgpData,sumFgpDataExample);
               }
               n+=Dclist.size();
          }else{
               n+= 0;
          }
          List<DmeDrcSumJzData> JzList = sbDataDbMod.getJzDataList();
          if (JzList != null && JzList.size()>0){
               for (DmeDrcSumJzData jzData:JzList){
                    DmeDrcSumJzDataExample jzDataExample = new DmeDrcSumJzDataExample();
                    DmeDrcSumJzDataExample.Criteria JzCa = jzDataExample.createCriteria();
                    JzCa.andDatadatefEqualTo(jzData.getDatadatef()).andJzbhEqualTo(jzData.getJzbh()).andOiIdfEqualTo(jzData.getOiIdf());
                    dmeDrcSumJzDataDao.updateByExampleSelective(jzData,jzDataExample);
               }
               n+=JzList.size();
          }else {
               n+=0;
          }
          return n;
     }

     @Override
     public List<DmeDrcDdrbCfg> findBydclx(String[] cfgType) {
          List<DmeDrcDdrbCfg> list = new ArrayList<>();
          if (cfgType != null && cfgType.length>0){
               DmeDrcDdrbCfgExample example = new DmeDrcDdrbCfgExample();
               for (String cfg:cfgType){
                    example.or().andCfgTypeLike("%"+cfg+"%");
               }
               example.setOrderByClause("dlsb_px asc");
               list = dmeDrcDdrbCfgDao.selectByExample(example);
          }
          return list;
     }

     @Override
     public DmeDrcDdrbCfg findByDlldbId(String dlldbid) {
          DmeDrcDdrbCfgExample example = new DmeDrcDdrbCfgExample();
          DmeDrcDdrbCfgExample.Criteria ca = example.createCriteria();
          ca.andDlldbIdEqualTo(dlldbid);
          List<DmeDrcDdrbCfg> list = dmeDrcDdrbCfgDao.selectByExample(example);
          return list.get(0);
     }

     @Transactional(propagation = Propagation.REQUIRED,rollbackFor = ServiceException.class)
     @Override
     public int saveSbData(SbDataDbMod sbDataDbMod) {
          List<DmeDrcSumFgpData> Dclist = sbDataDbMod.getDcDataList();
          int n = 0;
          if (Dclist != null && Dclist.size()>0){
               for (DmeDrcSumFgpData sumFgpData:Dclist){
                    DmeDrcSumFgpDataExample sumFgpDataExample = new DmeDrcSumFgpDataExample();
                    DmeDrcSumFgpDataExample.Criteria DcCa = sumFgpDataExample.createCriteria();
                    DcCa.andDatadatefEqualTo(sumFgpData.getDatadatef()).andOiIdfEqualTo(sumFgpData.getOiIdf());
                    int a = dmeDrcSumFgpDataDao.updateByExampleSelective(sumFgpData,sumFgpDataExample);
                    if (a ==0){
                         dmeDrcSumFgpDataDao.insertSelective(sumFgpData);
                    }
               }
               n+=Dclist.size();
          }else{
               n+= 0;
          }
          List<DmeDrcSumJzData> JzList = sbDataDbMod.getJzDataList();
          if (JzList != null && JzList.size()>0){
               for (DmeDrcSumJzData jzData:JzList){
                    DmeDrcSumJzDataExample jzDataExample = new DmeDrcSumJzDataExample();
                    DmeDrcSumJzDataExample.Criteria JzCa = jzDataExample.createCriteria();
                    JzCa.andDatadatefEqualTo(jzData.getDatadatef()).andJzbhEqualTo(jzData.getJzbh()).andOiIdfEqualTo(jzData.getOiIdf());
                    int d = dmeDrcSumJzDataDao.updateByExampleSelective(jzData,jzDataExample);
                    if (d==0){
                         dmeDrcSumJzDataDao.insertSelective(jzData);
                    }
               }
               n+=JzList.size();
          }else {
               n+=0;
          }
          return n;
     }


     @Override
     public PageInfo<DmeDrcDdrbCfg> findCfgAll(Integer pageNo, Integer pageSize) {
          //分页查询
          PageHelper.startPage(pageNo,pageSize);
          DmeDrcDdrbCfgExample example = new DmeDrcDdrbCfgExample();
          example.setOrderByClause("dlsb_px asc");
          List<DmeDrcDdrbCfg> list = dmeDrcDdrbCfgDao.selectByExample(example);

          return new PageInfo<>(list);
     }
}
