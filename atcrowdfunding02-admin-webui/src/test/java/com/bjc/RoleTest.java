package com.bjc;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bjc.crowd.entity.Admin;
import com.bjc.crowd.entity.Role;
import com.bjc.crowd.mapper.AdminMapper;
import com.bjc.crowd.mapper.RoleMapper;
import com.bjc.crowd.service.IAdminService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath*:spring-persist-*.xml")
public class RoleTest {
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Test
	public void test1() throws Exception {
		List<Role> roles = roleMapper.selectRoleByKeyword("a");
		System.out.println(roles);
	}
}
