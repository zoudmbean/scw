package com.bjc.crowd.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjc.crowd.entity.Admin;
import com.bjc.crowd.entity.AdminExample;
import com.bjc.crowd.entity.AdminExample.Criteria;
import com.bjc.crowd.exception.LoginException;
import com.bjc.crowd.mapper.AdminMapper;
import com.bjc.crowd.service.IAdminService;
import com.bjc.crowd.util.CrowdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class AdminService implements IAdminService{
	
	@Autowired
	private AdminMapper adminMapper;

	@Override
	public void save(Admin admin) {
		adminMapper.insert(admin);
	}

	@Override
	public List<Admin> getAll() {
		return adminMapper.selectByExample(new AdminExample());
	}

	@Override
	public Admin getByLoginAccount(Admin loginAdmin) {
		// 1. 登录用户判空
		if(null == loginAdmin || StringUtils.isEmpty(loginAdmin.getLoginAcct()) || StringUtils.isEmpty(loginAdmin.getUserPswd())) {
			throw new LoginException("登录失败，用户名密码不能为空！");
		}
		// 2. 根据用户名查询数据库中的用户对象
		AdminExample example = new AdminExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andLoginAcctEqualTo(loginAdmin.getLoginAcct());
		List<Admin> list = adminMapper.selectByExample(example);
		
		// 3. 如果查询结果小于1 表示数据库没有该账号
		if(null == list || list.size() < 1) {
			throw new LoginException("登录失败，用户名不存在！");
		}
		
		// 4. 再判断密码是否正确
		Admin dbAdmin = list.get(0);  			// -1EF523C6B645A65441A91FA80DF077C
		String pwd = loginAdmin.getUserPswd(); //  -1EF523C6B645A65441A91FA80DF077C2
		String md5 = CrowdUtil.md5(pwd);
		if(!dbAdmin.getUserPswd().equals(md5)) {
			throw new LoginException("登录失败，用户名或密码不正确！");
		}
		return dbAdmin;
	}

	@Override
	public PageInfo<Admin> getPageInfo(String keywords, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Admin> admins = adminMapper.selectAdminByKeyword(keywords);
		return new PageInfo<Admin>(admins);
	}

}
