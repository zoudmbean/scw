package com.bjc.crowd.service;

import java.util.List;

import com.bjc.crowd.entity.Auth;

public interface IAuthService {

	List<Auth> getAllAuth();

	List<Integer> getAllAuthByRoleId(Integer roleId);

	void setAuthByRoleId(Integer roleId, List<Integer> authIds);
	
}
