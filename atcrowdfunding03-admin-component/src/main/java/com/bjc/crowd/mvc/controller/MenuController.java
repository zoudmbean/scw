package com.bjc.crowd.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bjc.crowd.entity.Menu;
import com.bjc.crowd.service.IMenuService;
import com.bjc.crowd.util.ResultEntity;

@RestController
@RequestMapping("menu/")
public class MenuController {
	
	@Autowired
	private IMenuService menuService;
	
	@GetMapping("getMenuTree.json")
	public ResultEntity<Menu> getMenuTree(){
		Menu menu = menuService.getMenuTree();
		return ResultEntity.successWithData(menu);
	}
	
	@PostMapping("save.json")
	public ResultEntity<String> save(Menu menu){
		menuService.save(menu);
		return ResultEntity.successWithoutData();
	}
	
	@GetMapping("delete/{id}.json")
	public ResultEntity<String> delete(@PathVariable("id") Integer id){
		menuService.deleteById(id);
		return ResultEntity.successWithoutData();
	}

}
