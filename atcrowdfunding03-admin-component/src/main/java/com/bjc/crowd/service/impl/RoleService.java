package com.bjc.crowd.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bjc.crowd.entity.Role;
import com.bjc.crowd.entity.RoleExample;
import com.bjc.crowd.entity.RoleExample.Criteria;
import com.bjc.crowd.mapper.RoleMapper;
import com.bjc.crowd.service.IRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class RoleService implements IRoleService {
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
		PageHelper.startPage(pageNum, pageSize);
		List<Role> roles = roleMapper.selectRoleByKeyword(keyword);
		return new PageInfo<Role>(roles);
	}

	@Override
	public void save(Role role) {
		if(null == role) {
			return;
		}
		if(null != role.getId()) {
			roleMapper.updateByPrimaryKey(role);
		}
		if(null == role.getId()) {
			roleMapper.insert(role);
		}
	}

	@Override
	public void deleRole(List<Integer> rolesId) {
		RoleExample example = new RoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(rolesId);
		roleMapper.deleteByExample(example );
	}

	@Override
	public List<Role> getAssignedRole(Integer id) {
		return Optional.ofNullable(roleMapper.getAssignedRole(id)).orElseGet(ArrayList<Role>::new);
	}

	@Override
	public List<Role> getUnAssignedRole(Integer id) {
		return Optional.ofNullable(roleMapper.getUnAssignedRole(id)).orElseGet(ArrayList<Role>::new);
	}

	@Override
	public void saveAssign(Integer adminId,List<Integer> ids) {
		// 清空原有的数据
		roleMapper.clearByAdminId(adminId);
		// 如果不为空，那么保存
		if(!CollectionUtils.isEmpty(ids)) {
			roleMapper.saveAdminRoleRelationship(ids, adminId);
		}
	}

}
