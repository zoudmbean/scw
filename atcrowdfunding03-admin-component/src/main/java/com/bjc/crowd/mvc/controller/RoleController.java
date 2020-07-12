package com.bjc.crowd.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bjc.crowd.entity.Role;
import com.bjc.crowd.service.IRoleService;
import com.bjc.crowd.util.ResultEntity;
import com.github.pagehelper.PageInfo;

@RestController
public class RoleController {
	
	@Autowired
	private IRoleService roleService;
	
	@PostMapping("/role/save.json")
	public ResultEntity<String> save(Role role){
		roleService.save(role);
		return ResultEntity.successWithoutData();
	}
	
	@PostMapping("/role/removeByIds.json")
	public ResultEntity<String> deleRole(@RequestBody List<Integer> rolesId){
		roleService.deleRole(rolesId);
		return ResultEntity.successWithoutData();
	}
	
	@GetMapping("/role/getPageInfo.json")
	public ResultEntity<PageInfo<Role>> getPageInfo(
			@RequestParam(value="pageNum",defaultValue = "1") Integer pageNum, 
			@RequestParam(value="pageSize",defaultValue = "10") Integer pageSize, 
			@RequestParam(value="keywords",defaultValue = "") String keyword){
		
		PageInfo<Role> pageInfo = roleService.getPageInfo(pageNum, pageSize, keyword);
		
		return ResultEntity.successWithData(pageInfo);
	}

}
