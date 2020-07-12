package com.bjc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.bjc.crowd.entity.Menu;
import com.bjc.crowd.service.IMenuService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath*:spring-persist-*.xml")
public class MenuTest {
	
	@Autowired
	private IMenuService menuService;
	
	@Test
	public void testTree() {
		Menu menuTree = menuService.getMenuTree();
		System.out.println(JSONObject.toJSONString(menuTree));
	}
}
