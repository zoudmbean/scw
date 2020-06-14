package com.bjc.crowd.service;

import java.util.List;

import com.bjc.crowd.entity.Admin;

public interface IAdminService {
	
	void save(Admin admin);

	List<Admin> getAll();
}
