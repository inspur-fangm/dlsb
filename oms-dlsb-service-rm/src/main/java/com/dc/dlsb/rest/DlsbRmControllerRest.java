package com.dc.dlsb.rest;

import com.dc.dlsb.model.*;
import com.dc.dlsb.service.DlsbRmService;
import com.dc.dlsb.vomodel.SbDataDbMod;
import com.dc.exception.ServiceException;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author wanggang
 * @description 电量上报-rm-rest
 * @date 2019/2/15
 */
@RestController
@RequestMapping("/dlsbrm")
@Api(value = "DlsbRmControllerRest",tags = "电量上报rm服务")
public class DlsbRmControllerRest {

     @Autowired
     private DlsbRmService dlsbRmService;


     @ApiOperation(value = "根据sbOmsOrgid查询rm用户配置表获取该机构可填报数据",tags = "根据sbOmsOrgid查询rm用户配置表获取该机构可填报数据")
     @ResponseBody
     @GetMapping(value = "/findBysbOmsOrgid")
     public List<DmeDrcDdrbCfg> findBysbOmsOrgid(@ApiParam(value = "数据上报机构id",required = true) @RequestParam(value = "sbOmsOrgid",required = true) String sbOmsOrgid){
          return dlsbRmService.findBysbOmsOrgid(sbOmsOrgid);
     }

    @ApiOperation(value = "根据日期，电厂id获取到该日期该电厂上报数据",tags = "根据日期，电厂id获取到该日期该电厂上报数据")
    @ResponseBody
    @GetMapping(value = "/getDcSbDataByDateAndDcid")
     public DmeDrcSumFgpData getDcSbDataByDateAndDcid(@ApiParam(value = "数据日期",required = true) @RequestParam(value = "date",required = true) String date,
                                            @ApiParam(value = "电厂id",required = true) @RequestParam(value = "dcid",required = true) String dcid) {
          return dlsbRmService.getDcSbDataByDateAndDcid(date,dcid);
     }

     @ApiOperation(value = "根据日期，机组id，上报电厂id获取该机组上报数据",tags = "根据日期，机组id，上报电厂id获取该机组上报数据")
     @ResponseBody
     @GetMapping(value = "/getJzSbDataBy")
     public DmeDrcSumJzData getJzSbDataBy(@ApiParam(value = "数据日期",required = true) @RequestParam(value = "date",required = true) String date,
                                 @ApiParam(value = "机组id",required = true) @RequestParam(value = "jzid",required = true) String jzid,
                                 @ApiParam(value = "电厂id",required = true) @RequestParam(value = "dcid",required = true) String dcid) {
          return dlsbRmService.getJzSbDataBy(date,jzid,dcid);
     }

     @ApiOperation(value = "新增上报数据",tags = "新增上报数据")
     @ResponseBody
     @PutMapping(value = "/addNewSbData")
     public int addNewSbData(@ApiParam(value = "上报数据明细",required = true) @Valid @RequestBody SbDataDbMod sbDataDbMod) {
          return dlsbRmService.addNewSbData(sbDataDbMod);
     }

     @ApiOperation(value = "更新上报数据",tags = "更新上报数据")
     @ResponseBody
     @PutMapping(value = "/updateSbData")
     public int updateSbData(@ApiParam(value = "更新数据明细",required = true) @Valid @RequestBody SbDataDbMod sbDataDbMod) {
          return dlsbRmService.updateSbData(sbDataDbMod);
     }

     @ApiOperation(value = "根据电厂类型查询到RM数据库配置表",tags = "根据电厂类型查询到RM数据库配置表")
     @ResponseBody
     @GetMapping(value = "/findBydclx")
     public List<DmeDrcDdrbCfg> findBydclx(@ApiParam(value = "电厂类型:火电厂",required = true) @RequestParam(value = "dclx",required = true) String[] dclx) {
          return dlsbRmService.findBydclx(dclx);
     }

     @ApiOperation(value = "根据dlldbid查询配置",tags = "根据dlldbid查询配置")
     @ResponseBody
     @GetMapping(value = "/findByDlldbId")
     public DmeDrcDdrbCfg findByDlldbId(@ApiParam(value = "配置表对应DLLDB_ID",required = true) @RequestParam(value = "dlldbid",required = true) String dlldbid){
          return dlsbRmService.findByDlldbId(dlldbid);
     }

     @ApiOperation(value = "保存上报数据，判断是更新还是添加",tags = "保存上报数据，判断是更新还是添加")
     @ResponseBody
     @PostMapping(value = "/saveSbData")
     public int saveSbData(@ApiParam(value = "保存数据明细",required = true) @RequestBody SbDataDbMod sbDataDbMod) {
          return dlsbRmService.saveSbData(sbDataDbMod);
     }

     @ApiOperation(value = "分页查询展示配置数据",tags = "分页查询展示配置数据")
     @ResponseBody
     @GetMapping(value = "/findCfgAll")
     public PageInfo<DmeDrcDdrbCfg> findCfgAll(
             @ApiParam(value = "当前第几页",required = true) @RequestParam(value = "page",required = true) Integer page,
             @ApiParam(value = "显示的条数",required = true) @RequestParam(value = "limit",required = true) Integer limit
     ){
        return dlsbRmService.findCfgAll(page,limit);
     }
}
