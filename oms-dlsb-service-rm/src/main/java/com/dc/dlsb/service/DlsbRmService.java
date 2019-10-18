package com.dc.dlsb.service;

import com.dc.dlsb.model.DmeDrcDdrbCfg;
import com.dc.dlsb.model.DmeDrcSumFgpData;
import com.dc.dlsb.model.DmeDrcSumJzData;
import com.dc.dlsb.vomodel.SbDataDbMod;
import com.github.pagehelper.PageInfo;
import scala.collection.immutable.Page;

import java.util.List;

/**
 * @author wanggang
 * @description 电量上报-rm-service
 * @date 2019/2/15
 */
public interface DlsbRmService {

     /**
      * @Author wg
      * @Description //根据sbOmsOrgid查询rm用户配置表获取该机构可填报数据
      * @Date 10:36 2019/2/15
      * @Param
      * @return
      */
     List<DmeDrcDdrbCfg> findBysbOmsOrgid(String sbOmsOrgid);
     
     /**
      * @Author wg
      * @Description 根据日期和电厂id获取该电厂上报数据信息
      * @Date 14:35 2019/2/16
      * @Param 
      * @return 
      */
     DmeDrcSumFgpData getDcSbDataByDateAndDcid(String date,String dcid);

     /**
      * @Author wg
      * @Description 根据日期，机组id，电厂id获取机组上报数据
      * @Date 16:04 2019/2/16
      * @Param
      * @return
      */
     DmeDrcSumJzData getJzSbDataBy(String date, String jzid, String dcid);

     /**
      * @Author wg
      * @Description 新增上报数据
      * @Date 14:49 2019/2/16
      * @Param
      * @return
      */
     int addNewSbData(SbDataDbMod sbDataDbMod);

     /**
      * @Author wg
      * @Description 更新上报数据
      * @Date 15:38 2019/2/16
      * @Param
      * @return
      */
     int updateSbData(SbDataDbMod sbDataDbMod);

     /**
      * @Author wg
      * @Description 根据电厂类型获取电厂配置
      * @Date 14:12 2019/2/19
      * @Param
      * @return
      */
     List<DmeDrcDdrbCfg> findBydclx(String[] cfgType);

     /**
      * @Author wg
      * @Description 根据DLLDB_ID查询rm对应电厂配置
      * @Date 13:42 2019/2/23
      * @Param
      * @return
      */
     DmeDrcDdrbCfg findByDlldbId(String dlldbid);

     /**
      * @Author wg
      * @Description 保存上报数据
      * @Date 11:36 2019/3/26
      * @Param
      * @return
      */
     int saveSbData(SbDataDbMod sbDataDbMod);


     /**
      * 查询所有的电厂配置数据
      * @return
      */
     PageInfo<DmeDrcDdrbCfg> findCfgAll(Integer pageNo,Integer pageSize);

}
