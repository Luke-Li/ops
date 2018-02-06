package com.peseer.ops.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

public class ShiroLogin {
	public static Subject Verification(String file, String userName, String passwd){
//		Factory<org.apache.shiro.mgt.SecurityManager> factory =
//	            new IniSecurityManagerFactory(file);
//	    //2、得到SecurityManager实例 并绑定给SecurityUtils
//	    org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
//	    SecurityUtils.setSecurityManager(securityManager);
	    //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
	    Subject subject = SecurityUtils.getSubject();
	    UsernamePasswordToken token = new UsernamePasswordToken(userName, passwd);

	    try {
	        //4、登录，即身份验证
	        subject.login(token);
	    } catch (AuthenticationException e) {
	        //5、身份验证失败
	    	e.printStackTrace();
	    	return null;
	    }
	    return subject;
	}

	 public static void logout(){
		 Subject subject = SecurityUtils.getSubject();
		 if (subject.isAuthenticated()) {
			 subject.logout();
		 }
	 }

	 public static String getUserName(){
		 return SecurityUtils.getSubject().getPrincipal().toString();
	 }
}
