package com.bjc.crowd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjc.crowd.entity.Admin;
import com.bjc.crowd.entity.AdminExample;
import com.bjc.crowd.mapper.AdminMapper;
import com.bjc.crowd.service.IAdminService;

@Service
public class AdminService implements IAdminService{
	
	@Autowired
	private AdminMapper AdminMapper;

	@Override
	public void save(Admin admin) {
		AdminMapper.insert(admin);
	}

	@Override
	public List<Admin> getAll() {
		return AdminMapper.selectByExample(new AdminExample());
	}

}
