package com.bjc.crowd.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class CrowdUtil {
	
	/**
	 * @param 需要加密的字符串
	 * @return
	 */
	public static String md5(String source) {
		
		// 1. 传入的密码非空判断
		if(StringUtils.isEmpty(source)) {
			throw new RuntimeException("密码不能为空！");
		}
		
		// 2. 加密
		String algorithm = "md5";
		try {
			// 3. 得到加密之后的字节数组
			byte[] digest = MessageDigest.getInstance(algorithm).digest(source.getBytes());
			// 4. 将字节数组转成16位的字符串 
			String pwd = new BigInteger(digest).toString(16).toUpperCase();
			// 5. 返回
			return pwd;
		} catch (NoSuchAlgorithmException e) {
			// throw new RuntimeException("不存在" + algorithm + "加密算法", e);
			System.out.println("不存在" + algorithm + "加密算法: " + e);
		}
		return null;
	}
	
	/**
	 * 判断当前请求是否为Ajax请求
	 * @param request 请求对象
	 * @return
	 * 		true：当前请求是Ajax请求
	 * 		false：当前请求不是Ajax请求
	 */
	public static boolean judgeRequestType(HttpServletRequest request) {
		
		// 1.获取请求消息头
		String acceptHeader = request.getHeader("Accept");
		String xRequestHeader = request.getHeader("X-Requested-With");
		
		// 2.判断
		return (acceptHeader != null && acceptHeader.contains("application/json"))
				
				||
				
				(xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest"));
	}

}
