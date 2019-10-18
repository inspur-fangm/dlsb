package com.dc.dlsb.client;

import com.dc.dlsb.client.fallback.DlsbOmsServiceHystrix;
import com.dc.dlsb.common.DlsbOmsFeignConfiguration;
import com.dc.dlsb.model.OmsJzglJzxx;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author wanggang
 * @description 电量上报-获取OMS机组信息client
 * @date 2019/2/16
 */
@FeignClient(name = "OMS-DLSB-SERVICE-OMS",path = "/dlsboms",configuration = DlsbOmsFeignConfiguration.class,fallbackFactory = DlsbOmsServiceHystrix.class)
public interface DlsbOmsClient {

     @GetMapping(value = "/findOmsJzxxByDcid")
     List<OmsJzglJzxx> findOmsJzxxByDcid(@RequestParam(value = "dcid") String dcid);
}
