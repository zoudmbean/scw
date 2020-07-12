package com.bjc.crowd.mapper;

import com.bjc.crowd.entity.Role;
import com.bjc.crowd.entity.RoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    List<Role> selectRoleByKeyword(String keywords);

	List<Role> getAssignedRole(Integer id);

	List<Role> getUnAssignedRole(Integer id);
	
	void saveAdminRoleRelationship(@Param("roleIdList") List<Integer> roleIdLists,@Param("adminId") Integer adminId );

	void clearByAdminId(Integer adminId);
}