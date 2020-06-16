package com.bjc.crowd.mvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bjc.crowd.constant.CrowdConstant;
import com.bjc.crowd.entity.Admin;
import com.bjc.crowd.service.IAdminService;
import com.github.pagehelper.PageInfo;

@Controller
public class AdminController {
	@Autowired
	private IAdminService adminService;
	
	@GetMapping("/admin/getByPageInfo.html")
	public String getByPageInfo(Model model,
			@RequestParam(value="keywords",defaultValue = "") String keywords,
			@RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
			@RequestParam(value="pageSize",defaultValue = "10") Integer pageSize) {
		PageInfo<Admin> pageInfo = adminService.getPageInfo(keywords, pageNum, pageSize);
		model.addAttribute("pageInfo", pageInfo);
		return "admin-page";
	}
	
	@GetMapping("/logout.html")
	public String logout(HttpSession session) {
		// 让session失效
		session.invalidate();
		return "redirect:/admin/to/login/page.html";
	}
	
	@PostMapping("/admin/do/login.html")
	public String doLogin(Admin loginAdmin,HttpSession session) {
		Admin admin = adminService.getByLoginAccount(loginAdmin);
		session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, admin);
		return "redirect:/admin/to/main/page.html";
	}
}
