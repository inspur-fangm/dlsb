package com.dc.dlsb.client.fallback;

import com.dc.dlsb.client.DlsbRmClient;
import com.dc.dlsb.model.DmeDrcDdrbCfg;
import com.dc.dlsb.model.DmeDrcSumFgpData;
import com.dc.dlsb.model.DmeDrcSumJzData;
import com.dc.dlsb.vomodel.SbDataDbMod;
import com.github.pagehelper.PageInfo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wanggang
 * @description 电量上报-rm-Hystrix
 * @date 2019/2/15
 */
@Component
public class DlsbRmServiceHystrix implements FallbackFactory<DlsbRmClient> {
     @Override
     public DlsbRmClient create(Throwable cause) {
          return new DlsbRmClient() {
               @Override
               public List<DmeDrcDdrbCfg> findBysbOmsOrgid(String sbOmsOrgid) {
                    return null;
               }

               @Override
               public DmeDrcSumFgpData getDcSbDataByDateAndDcid(String date, String dcid) {
                    return null;
               }

               @Override
               public DmeDrcSumJzData getJzSbDataBy(String date, String jzid, String dcid) {
                    return null;
               }

               @Override
               public int addNewSbData(SbDataDbMod sbDataDbMod) {
                    return 0;
               }

               @Override
               public int updateSbData(SbDataDbMod sbDataDbMod) {
                    return 0;
               }

               @Override
               public List<DmeDrcDdrbCfg> findBydclx(String[] dclx) {
                    return null;
               }

               @Override
               public DmeDrcDdrbCfg findByDlldbId(String dlldbid) {
                    return null;
               }

               @Override
               public int saveSbData(SbDataDbMod sbDataDbMod) {
                    return 0;
               }

               @Override
               public PageInfo<DmeDrcDdrbCfg> findCfgAll(Integer page, Integer limit) {
                    return null;
               }
          };
     }
}
