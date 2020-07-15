package com.bjc.crowd.mapper;

import com.bjc.crowd.entity.Auth;
import com.bjc.crowd.entity.AuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthMapper {
    int countByExample(AuthExample example);

    int deleteByExample(AuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Auth record);

    int insertSelective(Auth record);

    List<Auth> selectByExample(AuthExample example);

    Auth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByExample(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

	List<Integer> getAllAuthByRoleId(@Param("roleId") Integer roleId);

	void deleRoleRelation(Integer roleId);

	void saveRoleRelation(@Param("roleId") Integer roleId, @Param("authIds") List<Integer> authIds);
}