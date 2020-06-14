package com.bjc.crowd.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjc.crowd.entity.Admin;
import com.bjc.crowd.entity.Student;
import com.bjc.crowd.service.IAdminService;

@Controller
public class TestController {
	@Autowired
	private IAdminService adminService;
	
	@PostMapping("/send/object/compose.html")
	@ResponseBody
	public String testAjax3(@RequestBody Student student) {
		System.out.println(student);
		return "success";
	}
	@PostMapping("/send/array/two.html")
	@ResponseBody
	public String testAjax2(@RequestBody List<Integer> list) {
		list.forEach(System.out::println);
		return "success";
	}
	
	@PostMapping("/send/array/one.html")
	@ResponseBody
	public String testAjax1(@RequestParam("array[]") List<Integer> list) {
		list.forEach(System.out::println);
		return "success";
	}
	
	@GetMapping("/test/ssm.html")
	public String test(Model model) {
		List<Admin> admins = adminService.getAll();
		if(null != admins) {
			throw new RuntimeException("自定义异常信息！");
		}
		model.addAttribute("admins", admins);
		return "target";
	}
}
