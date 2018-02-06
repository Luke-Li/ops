package com.peseer.ops.controller;

import org.apache.shiro.subject.Subject;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.peseer.ops.util.ReturnT;
import com.peseer.ops.util.ShiroLogin;
import com.peseer.ops.util.StringUtil;

@ControllerBind(controllerKey = "/ops")
public class LoginController extends Controller{
	private static String shiroFile = "C:/dev/cv/ops_tool/target/ops/WEB-INF/shiro.ini";

//	@ActionKey("/login")
	public void login(){
		String userName = getPara("username");
		String passwd = getPara("password");

		if(StringUtil.isNullOrEmpty(userName)||
				StringUtil.isNullOrEmpty(passwd)){
			render("login.html");
			return;
		}

		Subject subject = ShiroLogin.Verification(shiroFile, userName, passwd);

		if(subject != null){
			redirect("/fund/list");
		}else{
			render("login.html");
		}
	}

//	@ActionKey("/logout")
	public void logout(){
		ShiroLogin.logout();
//		redirect("/ops/login");
		renderJson(ReturnT.SUCCESS);
	}

}
