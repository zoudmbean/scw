package com.bjc.crowd.service;

import java.util.List;

import com.bjc.crowd.entity.Role;
import com.github.pagehelper.PageInfo;

public interface IRoleService {
	public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword);

	public void save(Role role);

	public void deleRole(List<Integer> rolesId);

	public List<Role> getAssignedRole(Integer id);

	public List<Role> getUnAssignedRole(Integer id);

	public void saveAssign(Integer adminId,List<Integer> ids);
}
