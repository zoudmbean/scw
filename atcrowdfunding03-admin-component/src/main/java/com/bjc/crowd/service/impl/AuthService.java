package com.bjc.crowd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bjc.crowd.entity.Auth;
import com.bjc.crowd.entity.AuthExample;
import com.bjc.crowd.mapper.AuthMapper;
import com.bjc.crowd.service.IAuthService;

@Service
public class AuthService implements IAuthService{
	
	@Autowired
	private AuthMapper authMapper;

	@Override
	public List<Auth> getAllAuth() {
		return authMapper.selectByExample(new AuthExample());
	}

	@Override
	public List<Integer> getAllAuthByRoleId(Integer roleId) {
		return authMapper.getAllAuthByRoleId(roleId);
	}

	@Override
	public void setAuthByRoleId(Integer roleId, List<Integer> authIds) {
		if(null == roleId) {
			throw new RuntimeException("权限ID不能为空");
		}
		authMapper.deleRoleRelation(roleId);
		if(!CollectionUtils.isEmpty(authIds)) {
			authMapper.saveRoleRelation(roleId,authIds);
		}
	}

}
