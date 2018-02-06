package com.peseer.ops.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class GlobalInterceptor implements Interceptor {
	public void intercept(Invocation ai) {
		
		//Controller controller = ai.getController();

		//处理jbox		
/*		String model = controller.getPara("model");
		if(StringUtils.equalsIgnoreCase(model, "jbox")){
			controller.setAttr("jbox", true);
		}*/
		
		ai.invoke();
		/*
		if(ShiroMethod.notAuthenticated()){
			controller.redirect("/admin/login");
		}else{
			String actionClassName = ai.getController().getClass().getName();
			String actionMethodName = ai.getMethodName();
			// 检查是否存在日志监控的方法 可以检查是否是授权用户
			System.out.println(actionClassName + " : " +actionMethodName);
			ai.invoke();
        }*/
	}
}
