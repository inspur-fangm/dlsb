package com.dc.dlsb.dao;

import com.dc.dlsb.model.DmeDrcSumFgpData;
import com.dc.dlsb.model.DmeDrcSumFgpDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DmeDrcSumFgpDataMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUMFGPDATA2
     *
     * @mbggenerated
     */
    int countByExample(DmeDrcSumFgpDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUMFGPDATA2
     *
     * @mbggenerated
     */
    int deleteByExample(DmeDrcSumFgpDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUMFGPDATA2
     *
     * @mbggenerated
     */
    int insert(DmeDrcSumFgpData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUMFGPDATA2
     *
     * @mbggenerated
     */
    int insertSelective(DmeDrcSumFgpData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUMFGPDATA2
     *
     * @mbggenerated
     */
    List<DmeDrcSumFgpData> selectByExample(DmeDrcSumFgpDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUMFGPDATA2
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") DmeDrcSumFgpData record, @Param("example") DmeDrcSumFgpDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUMFGPDATA2
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") DmeDrcSumFgpData record, @Param("example") DmeDrcSumFgpDataExample example);
}