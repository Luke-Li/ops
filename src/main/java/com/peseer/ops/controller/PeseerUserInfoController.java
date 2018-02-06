package com.peseer.ops.controller;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.peseer.ops.model.account.LoginInfo;
import com.peseer.ops.model.account.UserInfo;
import com.peseer.ops.util.ReturnT;
import com.peseer.ops.util.StringUtil;

@ControllerBind(controllerKey = "/userinfo")
public class PeseerUserInfoController extends Controller{
	public void list(){
		String key = getPara("key");

		Integer page = getParaToInt("page",1);
		Integer type = getParaToInt("type",0);

		setAttr("page",UserInfo.dao.selectUserInfoByName(key,type,page));
		setAttr("key", key);
		setAttr("type", type);

		render("account.list.html");
	}

	public void edit(){
		String uid = getPara("uid");
		String update = getPara("update");

		if(StringUtil.isNullOrEmpty(uid)){
			setAttr("model", UserInfo.dao);
		}else{
			UserInfo user = UserInfo.dao.findById(uid);
			if(StringUtil.isNullOrEmpty(update) && user != null){
				user.setUid("");
				user.setCnName("");
			}
			setAttr("model",user);
		}
		setAttr("update",update);

		render("account.edit.html");
	}

	public void update(){
		UserInfo record = getModel(UserInfo.class,"model");

		if(UserInfo.dao.findById(record.getUid()) == null){
			record.save();
			LoginInfo.dao.addAccount(record.getUid());
		}else{
			record.update();
		}

		redirect("/userinfo/list");
	}

	public void delete(){
		if(UserInfo.dao.deleteAccountInfo(getPara("uid"))){
			LoginInfo.dao.deleteAccount(getPara("uid"));
			renderJson(ReturnT.SUCCESS);
		}else{
			renderJson(ReturnT.FAIL);
		}
	}

	public void pwd(){
		String uid = getPara("uid");
		if(!StringUtil.isNullOrEmpty(uid)){
			ReturnT.SUCCESS.setContent(LoginInfo.dao.getPwd(uid));
			renderJson(ReturnT.SUCCESS);
		}else{
			renderJson(ReturnT.FAIL);
		}
	}

	public void decryptAll(){
		try {
			LoginInfo.dao.decryptAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void encryptAll(){
		try {
			LoginInfo.dao.encryptAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
