package com.bjc.crowd.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjc.crowd.entity.Menu;
import com.bjc.crowd.mapper.MenuMapper;
import com.bjc.crowd.service.IMenuService;

@Service
public class MenuService implements IMenuService {
	
	@Autowired
	private MenuMapper menuMapper;

	@Override
	public Menu getMenuTree() {
		
		// 1. 获取所有的菜单
		List<Menu> menus = menuMapper.selectByExample(null);
		
		// 2. 定义map集合建立id与menu的映射关系
		Map<Integer,Menu> menusMap = new HashMap<>();
		menus.forEach(menu -> {
			menusMap.put(menu.getId(), menu);
		});
		
		// 3. 
		Menu root = null;
		
		// 4. 遍历menus
		for(Menu menu : menus) {
			Integer pid = menu.getPid();
			// 如果pid为null，表示为根节点
			if(null == pid) {
				root = menu;
				continue;
			}
			// 否则不为根节点,从集合中获取父菜单
			Menu fatherMenu = menusMap.get(pid);
			if(null != fatherMenu) {
				fatherMenu.getChildren().add(menu);
			}
		}
		
		return root;
	}

	/**
	 *	更新/添加菜单
	 */
	@Override
	public void save(Menu menu) {
		if(null == menu) {
			throw new RuntimeException("服务器没接收到数据！");
		}
		Integer id = menu.getId();
		if(null == id) { // 保存
			menuMapper.insert(menu);
		} else {
			menuMapper.updateByPrimaryKeySelective(menu);
		}
	}

	@Override
	public void deleteById(Integer id) {
		menuMapper.deleteByPrimaryKey(id);
	}

}
