package com.peseer.ops.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.ext.route.ControllerBind;
import com.peseer.ops.model.DailyOperationRecord;
import com.peseer.ops.model.OpsOrgGpInfo;
import com.peseer.ops.model.base.BaseOpsOrgGpInfo;
import com.peseer.ops.util.ReturnT;
import com.peseer.ops.util.ShiroLogin;

@ControllerBind(controllerKey = "/org")
public class OrgInfoController extends Controller{

	public void get(){
		setAttr("orgInfo",OpsOrgGpInfo.dao.getOrgInfoFromId(getPara("id")));
		renderJson();
	}

	public void list(){
		String orgName = getPara("orgName");
		String type = getPara("type");
		Integer page = getParaToInt("page",1);

		setAttr("page",OpsOrgGpInfo.dao.getOrgInfoFromName(orgName,type,page));
		setAttr("orgName", orgName);
		setAttr("type",type);

		render("org.list.html");
	}

	public void list_less(){
		String orgName = getPara("orgName");
		String type = getPara("type");
		Integer page = getParaToInt("page",1);

		setAttr("page",OpsOrgGpInfo.dao.getOrgInfoFromName(orgName,type,page));
		setAttr("orgName", orgName);
		setAttr("type", type);

		render("org.list.less.html");
	}

	public void insert(){
		OpsOrgGpInfo orgInfo = getModel(OpsOrgGpInfo.class);
		OpsOrgGpInfo.dao.insertOrgInfo(orgInfo);
		renderJson("status","success");
	}


	@Before(GET.class)
	public void edit(){
		Integer id = getParaToInt("id");
		if(null != id){
			setAttr("model", OpsOrgGpInfo.dao.getOrgInfoFromId(id));
		}else{
			setAttr("model", OpsOrgGpInfo.dao);
		}

		render("org.edit.html");
	}

	@Before(POST.class)
	public void update(){
		OpsOrgGpInfo model = getModel(OpsOrgGpInfo.class,"model");
		if(model.getOrgId() == null){
			//add
			model.save();
			model = OpsOrgGpInfo.dao.getOrgInfoFromName(model.getOrgCnName());

			DailyOperationRecord.dao.saveRecord(model.getOrgId(),BaseOpsOrgGpInfo.TableName, ShiroLogin.getUserName(), "add");
		}else{
			//update
			model.update();
			DailyOperationRecord.dao.saveRecord(model.getOrgId(),BaseOpsOrgGpInfo.TableName, ShiroLogin.getUserName(), "update");
		}
		redirect("/org/list");
	}

	public void delete(){
		if(OpsOrgGpInfo.dao.deleteOrgInfo(getParaToInt("id")))
		{
			DailyOperationRecord.dao.saveRecord(getParaToInt("id"),BaseOpsOrgGpInfo.TableName, ShiroLogin.getUserName(), "delete");
			renderJson(ReturnT.SUCCESS);
		}else{
			renderJson(ReturnT.FAIL);
		}
	}

	public void  selectById(){
		renderJson("orgInfo",OpsOrgGpInfo.dao.getOrgInfoFromId(getParaToInt("id")));
	}

	public void  selectByName(){
		renderJson("orgInfo",OpsOrgGpInfo.dao.getOrgInfoFromName(getPara("name")));
	}
}
