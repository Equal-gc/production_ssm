package com.megagao.production.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.megagao.production.ssm.domain.Custom;
import com.megagao.production.ssm.domain.CustomExample;

public interface CustomMapper {
	
	//扩展的mapper接口方法
	int deleteBatch(String[] ids);
	
	int changeStatus(String[] ids);
	
	int updateNote(Custom record);
	
	List<Custom> searchCustomByCustomName(String customName,String companyId);
	
	List<Custom> searchCustomByCustomId(String customId);
    List<Custom> searchCustomByCustomId2(String customId,String companyId);
	
	//逆向工程生成的mapper接口
    int countByExample(CustomExample example);

    int deleteByExample(CustomExample example);

    int deleteByPrimaryKey(String customId,String companyId);

    int insert(Custom record);

    int insertSelective(Custom record);

    List<Custom> selectByExample(CustomExample example);
    List<Custom> selectByExample2(CustomExample example,String companyId);

    Custom selectByPrimaryKey(String customId);

    int updateByExampleSelective(@Param("record") Custom record, @Param("example") CustomExample example);

    int updateByExample(@Param("record") Custom record, @Param("example") CustomExample example);

    int updateByPrimaryKeySelective(Custom record);

    int updateByPrimaryKey(Custom record);
}