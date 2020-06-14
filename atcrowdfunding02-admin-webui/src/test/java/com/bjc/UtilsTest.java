package com.bjc;

import org.junit.Test;

import com.bjc.crowd.util.CrowdUtil;

public class UtilsTest {
	
	@Test
	public void testMD5() {
		String md5 = CrowdUtil.md5("123456");
		System.out.println(md5); // 202CB962AC59075B964B07152D234B70
								 // 202CB962AC59075B964B07152D234B70
	}

}
