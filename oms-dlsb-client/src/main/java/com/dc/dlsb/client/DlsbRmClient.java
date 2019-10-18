package com.dc.dlsb.client;

import com.dc.dlsb.client.fallback.DlsbRmServiceHystrix;
import com.dc.dlsb.common.DlsbRmFeignConfiguration;
import com.dc.dlsb.model.DmeDrcDdrbCfg;
import com.dc.dlsb.model.DmeDrcSumFgpData;
import com.dc.dlsb.model.DmeDrcSumJzData;
import com.dc.dlsb.vomodel.SbDataDbMod;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wanggang
 * @description 电量上报-操作rm用户表
 * @date 2019/2/15
 */
@FeignClient(name="OMS-DLSB-SERVICE-RM",path = "/dlsbrm",configuration = DlsbRmFeignConfiguration.class,fallbackFactory = DlsbRmServiceHystrix.class)
public interface DlsbRmClient {

     @GetMapping(value = "/findBysbOmsOrgid")
     List<DmeDrcDdrbCfg> findBysbOmsOrgid(@RequestParam(value = "sbOmsOrgid") String sbOmsOrgid);

     @GetMapping(value = "/getDcSbDataByDateAndDcid")
     public DmeDrcSumFgpData getDcSbDataByDateAndDcid(@RequestParam(value = "date") String date,
                                                      @RequestParam(value = "dcid") String dcid);


     @GetMapping(value = "/getJzSbDataBy")
     public DmeDrcSumJzData getJzSbDataBy(@RequestParam(value = "date") String date,
                                          @RequestParam(value = "jzid") String jzid,
                                          @RequestParam(value = "dcid") String dcid);


     @PutMapping(value = "/addNewSbData")
     public int addNewSbData(@RequestBody SbDataDbMod sbDataDbMod);


     @PutMapping(value = "/updateSbData")
     public int updateSbData(@RequestBody SbDataDbMod sbDataDbMod);

     @GetMapping(value = "/findBydclx")
     public List<DmeDrcDdrbCfg> findBydclx(@RequestParam(value = "dclx") String[] dclx);

     @GetMapping(value = "/findByDlldbId")
     public DmeDrcDdrbCfg findByDlldbId(@RequestParam(value = "dlldbid") String dlldbid);

     @PostMapping(value = "/saveSbData")
     public int saveSbData(@RequestBody SbDataDbMod sbDataDbMod);

     @GetMapping(value = "/findCfgAll")
     public PageInfo<DmeDrcDdrbCfg> findCfgAll(@RequestParam(value = "page") Integer page,
                                               @RequestParam(value = "limit") Integer limit);
}
