package com.bjc.crowd.mvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.bjc.crowd.constant.CrowdConstant;
import com.bjc.crowd.entity.Admin;
import com.bjc.crowd.service.IAdminService;

@Controller
public class AdminController {
	@Autowired
	private IAdminService adminService;
	
	@PostMapping("/admin/do/login/page.html")
	public String doLogin(Admin loginAdmin,HttpSession session) {
		Admin admin = adminService.getByLoginAccount(loginAdmin);
		session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, admin);
		return "admin-main";
	}
}
