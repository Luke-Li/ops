package com.peseer.ops.controller;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.ext.route.ControllerBind;
import com.peseer.ops.model.DailyOperationRecord;
import com.peseer.ops.model.OpsOrgUserInfo;
import com.peseer.ops.util.ReturnT;
import com.peseer.ops.util.ShiroLogin;

@ControllerBind(controllerKey = "/user")
public class UserInfoController extends Controller{

	public void getFromId(){
		setAttr("userInfo",OpsOrgUserInfo.dao.getUserInfoFromId(getParaToInt("id")));
		renderJson();
	}

	public void getFromUserId(){
		setAttr("userInfo",OpsOrgUserInfo.dao.getUserInfoFromUserId(getParaToInt("id")));
		renderJson();
	}

	public void getFromUserName(){
		setAttr("userInfo",OpsOrgUserInfo.dao.getUserInfoFromUserName(getParaToInt("name")));
		renderJson();
	}

	public void getFromOrgId(){
		setAttr("userInfo",OpsOrgUserInfo.dao.getUserInfoFromOrgId(getPara("orgId")));
		renderJson();
	}

	public void getFromOrgName(){
		setAttr("userInfo",OpsOrgUserInfo.dao.getUserInfoFromOrgName(getPara("orgName")));
		renderJson();
	}

	public void insert(){
		OpsOrgUserInfo userInfo = this.getModel(OpsOrgUserInfo.class);
		OpsOrgUserInfo.dao.insertUserInfo(userInfo);
		renderJson("status","success");
	}

	public void delete(){
		if(OpsOrgUserInfo.dao.deleteUserInfo(getParaToInt("id"))){
			DailyOperationRecord.dao.saveRecord(getParaToInt("id"),OpsOrgUserInfo.TableName, ShiroLogin.getUserName(), "delete");
			renderJson(ReturnT.SUCCESS);
		}else{
			renderJson(ReturnT.FAIL);
		}
	}


	@Before(GET.class)
	public void edit(){
		Integer id = getParaToInt("id");
		if(null != id){
			OpsOrgUserInfo detail = OpsOrgUserInfo.dao.getUserInfoFromId(id);
			String model = getPara("model");

			if(StringUtils.isNotBlank(model) && model.equals("copy")){
				detail = detail.remove("id");
			}

			setAttr("model", detail);
		}else{
			setAttr("model", OpsOrgUserInfo.dao);
		}

		render("user.edit.html");
	}

	@Before(POST.class)
	public void update(){
		OpsOrgUserInfo model = getModel(OpsOrgUserInfo.class,"model");
		if(model.getId() == null){
			//add
			model.save();
			model = OpsOrgUserInfo.dao.getUserInfoFromUserId(model.getUserId());
			DailyOperationRecord.dao.saveRecord(model.getId(),OpsOrgUserInfo.TableName, ShiroLogin.getUserName(), "add");
		}else{
			//update
			model.update();
			DailyOperationRecord.dao.saveRecord(model.getId(),OpsOrgUserInfo.TableName, ShiroLogin.getUserName(), "update");
		}
		redirect("/user/list");
	}

	public void list(){
		String key = getPara("key");
		String type = getPara("type");

		Integer page = getParaToInt("page",1);

		setAttr("page",OpsOrgUserInfo.dao.selectOrgUserInfo(key,type,page));
		setAttr("key", key);
		setAttr("type",type);

		render("user.list.html");
	}

	public void list_less(){
		String key = getPara("key");
		String type = getPara("type");

		Integer page = getParaToInt("page",1);

		setAttr("page",OpsOrgUserInfo.dao.selectOrgUserInfo(key,type,page));
		setAttr("key", key);
		setAttr("type",type);

		render("user.list.less.html");
	}
}
