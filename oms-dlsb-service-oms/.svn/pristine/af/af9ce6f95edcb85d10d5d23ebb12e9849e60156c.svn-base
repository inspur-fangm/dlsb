package com.dc.dlsb.service.impl;

import com.dc.dlsb.dao.OmsJzglJzxxMapper;
import com.dc.dlsb.model.OmsJzglJzxx;
import com.dc.dlsb.model.OmsJzglJzxxExample;
import com.dc.dlsb.service.DlsbOmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanggang
 * @description 电量上报-连接OMS系统ServiceImpl
 * @date 2019/2/16
 */
@Service
@Slf4j
public class DlsbOmsServiceImpl implements DlsbOmsService {

     @Autowired
     private OmsJzglJzxxMapper omsJzglJzxxdao;
     @Override
     public List<OmsJzglJzxx> findOmsJzxxByDcid(String dcid) {
          OmsJzglJzxxExample example = new OmsJzglJzxxExample();
          OmsJzglJzxxExample.Criteria ca = example.createCriteria();
          ca.andDcbhEqualTo(dcid).andXtztEqualTo("0");
          example.setOrderByClause("jzmc asc");
          List<OmsJzglJzxx> list = omsJzglJzxxdao.selectByExample(example);
          return list;
     }
}
