package com.bjc;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bjc.crowd.entity.Admin;
import com.bjc.crowd.mapper.AdminMapper;
import com.bjc.crowd.service.IAdminService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath*:spring-persist-*.xml")
public class AdminTest {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private AdminMapper mapper;
	
	@Autowired
	private IAdminService adminService;
	
	@Test
	public void test3() {
		Admin admin = new Admin(null, "Mary", "123456", "玛利亚", "jerry@qq.com", null);
		adminService.save(admin);
	}
	
	@Test
	public void test2() {
		Admin admin = new Admin(null, "jerry", "123456", "杰瑞", "jerry@qq.com", null);
		mapper.insert(admin);
	}
	
	@Test
	public void test1() throws Exception {
		System.out.println(dataSource.getConnection());
	}
}
