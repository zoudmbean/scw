package com.bjc.crowd.mvc.interceptor;

import java.security.AccessControlException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bjc.crowd.constant.CrowdConstant;
import com.bjc.crowd.entity.Admin;

/**登录拦截器
 * @author Administrator
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1. 得到session
		HttpSession session = request.getSession();
		// 2. 获取登录用户对象
		Admin loginUser =  (Admin) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);
		// 3. 判断：如果登录用户不存在，抛异常
		if(null == loginUser) {
			throw new AccessControlException(CrowdConstant.MESSAGE_ACCESS_FORBIDEN);
		}
		return true;
	}
}
