package com.dc.dlsb.common;

import com.dc.common.util.Tools;
import feign.Logger;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wanggang
 * @description 电量上报-rm-FeignConfiguration
 * @date 2019/2/15
 */
@Configuration
@ConfigurationProperties(prefix = "dc.api.dlsbrm")
public class DlsbRmFeignConfiguration {
     private static final String API_DEFAULT_KEY = "dechi";
     private static final String API_DEFAULT_PW = "dechi";
     private String key;
     private String pw;

     public void setKey(String key)
     {
          this.key = key;
     }
     public void setPw(String pw)
     {
          this.pw = pw;
     }

     public String getKey()
     {
          return this.key;
     }

     public String getPw()
     {
          return this.pw;
     }

     @Bean
     Logger.Level feignLoggerLevel()
     {
          return Logger.Level.BASIC;
     }

     @Bean
     public BasicAuthRequestInterceptor dcBasicAuthRequestInterceptor()
     {
          if ((Tools.isBlank(this.key)) || (Tools.isBlank(this.pw))) {
               return new BasicAuthRequestInterceptor("dechi", "dechi");
          }
          return new BasicAuthRequestInterceptor(this.key, this.pw);
     }

     @Bean
     public Request.Options options()
     {
          return new Request.Options(5000, 10000);
     }
}

