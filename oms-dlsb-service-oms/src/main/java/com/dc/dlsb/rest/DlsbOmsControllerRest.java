package com.dc.dlsb.rest;

import com.dc.dlsb.model.OmsJzglJzxx;
import com.dc.dlsb.service.DlsbOmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wanggang
 * @description 电量上报-oms机组信息rest
 * @date 2019/2/16
 */
@RestController
@RequestMapping("/dlsboms")
@Api(value = "DlsbOmsControllerRest",tags = "电量上报oms服务")
public class DlsbOmsControllerRest {

     @Autowired
     private DlsbOmsService dlsbOmsService;

     @ApiOperation(value = "根据电厂id查询oms系统机组信息",tags = "根据电厂id查询oms系统机组信息")
     @ResponseBody
     @GetMapping(value = "/findOmsJzxxByDcid")
     public List<OmsJzglJzxx> findOmsJzxxByDcid(
             @ApiParam(value = "oms系统电厂id",required = true)
             @RequestParam(value = "dcid",required = true) String dcid){
          return dlsbOmsService.findOmsJzxxByDcid(dcid);
     }
}
