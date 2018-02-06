package com.peseer.ops.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class LoginValidator extends Validator {
	protected void validate(Controller c) {
		validateRequiredString("name", "nameMsg", "请输入用户名");
		validateRequiredString("pass", "passMsg", "请输入密码");
	}

	protected void handleError(Controller c) {
		c.keepPara("name");
		c.render("login.html");
	}
}