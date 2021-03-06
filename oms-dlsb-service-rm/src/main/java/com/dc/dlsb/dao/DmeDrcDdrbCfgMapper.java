package com.dc.dlsb.dao;

import com.dc.dlsb.model.DmeDrcDdrbCfg;
import com.dc.dlsb.model.DmeDrcDdrbCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DmeDrcDdrbCfgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DME_DRC_DDRB_CFG_W
     *
     * @mbggenerated
     */
    int countByExample(DmeDrcDdrbCfgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DME_DRC_DDRB_CFG_W
     *
     * @mbggenerated
     */
    int deleteByExample(DmeDrcDdrbCfgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DME_DRC_DDRB_CFG_W
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DME_DRC_DDRB_CFG_W
     *
     * @mbggenerated
     */
    int insert(DmeDrcDdrbCfg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DME_DRC_DDRB_CFG_W
     *
     * @mbggenerated
     */
    int insertSelective(DmeDrcDdrbCfg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DME_DRC_DDRB_CFG_W
     *
     * @mbggenerated
     */
    List<DmeDrcDdrbCfg> selectByExample(DmeDrcDdrbCfgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DME_DRC_DDRB_CFG_W
     *
     * @mbggenerated
     */
    DmeDrcDdrbCfg selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DME_DRC_DDRB_CFG_W
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") DmeDrcDdrbCfg record, @Param("example") DmeDrcDdrbCfgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DME_DRC_DDRB_CFG_W
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") DmeDrcDdrbCfg record, @Param("example") DmeDrcDdrbCfgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DME_DRC_DDRB_CFG_W
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(DmeDrcDdrbCfg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DME_DRC_DDRB_CFG_W
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(DmeDrcDdrbCfg record);
}