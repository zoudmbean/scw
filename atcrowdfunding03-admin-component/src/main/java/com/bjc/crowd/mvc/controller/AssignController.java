package com.bjc.crowd.mvc.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bjc.crowd.entity.Role;
import com.bjc.crowd.service.IRoleService;

@Controller
public class AssignController {
	
	@Autowired
	private IRoleService roleService;
	
	@GetMapping("/assign/toAssign/{id}.html")
	public String toAssignRolePage(@PathVariable("id") Integer id,Model model) {
		
		// 1. 查询已分配的角色
		List<Role> assignedRoleList = roleService.getAssignedRole(id);
		
		// 1. 查询未分配的角色
		List<Role> unAssignedRoleList = roleService.getUnAssignedRole(id);
		
		model.addAttribute("assignedRoleList", assignedRoleList);
		model.addAttribute("unAssignedRoleList", unAssignedRoleList);
		
		return "assign-role";
	}
	
	@PostMapping("/assign/save.html")
	public String save(
			@RequestParam("adminId") Integer adminId,
			@RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
			@RequestParam(value = "keywords",defaultValue = "") String keywords,
			@RequestParam(value = "ids",required = false) List<Integer> ids
			) {
		roleService.saveAssign(adminId,ids);
		return "redirect:/admin/getByPageInfo.html?pageNum="+pageNum+"&keywords="+keywords;
	}
	
}
