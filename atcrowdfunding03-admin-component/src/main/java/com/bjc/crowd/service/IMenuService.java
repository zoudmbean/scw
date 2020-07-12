package com.bjc.crowd.service;

import com.bjc.crowd.entity.Menu;

public interface IMenuService {

	Menu getMenuTree();

	void save(Menu menu);

	void deleteById(Integer id);
	
}
