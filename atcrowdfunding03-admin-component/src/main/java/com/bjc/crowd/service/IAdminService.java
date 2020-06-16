package com.bjc.crowd.service;

import java.util.List;

import com.bjc.crowd.entity.Admin;
import com.github.pagehelper.PageInfo;

public interface IAdminService {
	
	void save(Admin admin);

	List<Admin> getAll();

	Admin getByLoginAccount(Admin loginAdmin);
	
	/** 查询 */
	PageInfo<Admin> getPageInfo(String keywords,Integer pageNum,Integer pageSize);
}
