package com.dc.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wanggang
 * @description 获取电厂类型配置
 * @date 2019/2/19
 */
@Component
@ConfigurationProperties(prefix = "oms.dlsb")
@Data
public class DclxCfg {
     private List<Map<String,String>> dclx;

     public String getDclxByName(String name){
          String value = "";
          List<Map<String, String>> list =this.dclx;
          for (Map<String, String> map : list) {
               if (name.equalsIgnoreCase(map.get("name"))) {
                    value = map.get("value");
               }
          }
          return value;
     }
}
