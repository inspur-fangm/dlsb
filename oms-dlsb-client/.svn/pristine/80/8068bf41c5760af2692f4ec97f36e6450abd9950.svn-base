package com.dc.dlsb.client.fallback;

import com.dc.dlsb.client.DlsbOmsClient;
import com.dc.dlsb.model.OmsJzglJzxx;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wanggang
 * @description 电量上报-oms-Hystrix
 * @date 2019/2/16
 */
@Component
public class DlsbOmsServiceHystrix implements FallbackFactory<DlsbOmsClient> {
     @Override
     public DlsbOmsClient create(Throwable cause) {
          return new DlsbOmsClient() {

               @Override
               public List<OmsJzglJzxx> findOmsJzxxByDcid(String dcid) {
                    return null;
               }
          };
     }
}
