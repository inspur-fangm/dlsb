package com.dc.dlsb.vomodel;

import com.dc.dlsb.model.DmeDrcSumFgpData;
import com.dc.dlsb.model.DmeDrcSumJzData;

import java.util.List;

/**
 * @author wanggang
 * @description 上报数据保存model
 * @date 2019/2/16
 */
public class SbDataDbMod {

     private List<DmeDrcSumFgpData> DcDataList;

     private List<DmeDrcSumJzData> JzDataList;

     public void setDcDataList(List<DmeDrcSumFgpData> dcDataList) {
          DcDataList = dcDataList;
     }

     public void setJzDataList(List<DmeDrcSumJzData> jzDataList) {
          JzDataList = jzDataList;
     }

     public List<DmeDrcSumFgpData> getDcDataList() {
          return DcDataList;
     }

     public List<DmeDrcSumJzData> getJzDataList() {
          return JzDataList;
     }

}
