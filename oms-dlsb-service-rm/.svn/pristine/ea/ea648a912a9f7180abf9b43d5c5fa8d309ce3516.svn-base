package com.dc;

import com.dc.swagger2.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration//此注释自动载入应用程序所需的所有Bean
@MapperScan("com.dc.**.dao")
@EnableTransactionManagement//声明事务管理
@EnableSwagger2Doc//与Spring MVC程序配合组织出强大RESTful API文档
@EnableEurekaClient//使用Eureka作为注册中心
@EnableFeignClients//可通过EnableFeignClients调用其他服务的api
@EnableCircuitBreaker//注解开启断路器功能
@EnableScheduling//快速开启任务调度功能
@SpringBootApplication//主类声明
public class OmsDlsbServiceRmApplication {

     public static void main(String[] args) {
          SpringApplication.run(OmsDlsbServiceRmApplication.class, args);
     }

}

