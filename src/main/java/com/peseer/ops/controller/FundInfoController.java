package com.peseer.ops.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.ext.route.ControllerBind;
import com.peseer.ops.model.DailyOperationRecord;
import com.peseer.ops.model.OpsFundInfo;
import com.peseer.ops.util.ReturnT;
import com.peseer.ops.util.ShiroLogin;

@ControllerBind(controllerKey = "/fund")
public class FundInfoController extends Controller{

	public void detail(){
		renderJson(OpsFundInfo.dao.getFundInfo(getParaToInt("id")));
	}

	/**
	 * 基金列表信息处理
	 */
	public void list(){
		String fundName = getPara("fundName");
		String type = getPara("type");
		Integer page = getParaToInt("page",1);

		setAttr("page",OpsFundInfo.dao.selectFundInfo(fundName,type,page));
		setAttr("fundName", fundName);
		setAttr("type", type);

		render("fund.list.html");
	}

	/**
	 * 基金列表信息处理
	 */
	public void list_less(){
		String fundName = getPara("fundName");
		String type = getPara("type");
		Integer page = getParaToInt("page",1);

		setAttr("page",OpsFundInfo.dao.selectFundInfo(fundName,type,page));
		setAttr("fundName", fundName);
		setAttr("type",type);

		render("fund.list.less.html");
	}

	@Before(GET.class)
	public void edit(){
		Integer id = getParaToInt("id");
		if(null != id){
			setAttr("model", OpsFundInfo.dao.getFundInfo(id));
		}else{
			setAttr("model", OpsFundInfo.dao);
		}


		render("fund.edit.html");
	}

	@Before(POST.class)
	public void update(){
		OpsFundInfo model = getModel(OpsFundInfo.class,"model");
		if(model.getFundId() == null){
			//add
			model.save();
			model = OpsFundInfo.dao.getFundInfoFromName(model.getFundName());
			DailyOperationRecord.dao.saveRecord(model.getFundId(),OpsFundInfo.TableName, ShiroLogin.getUserName(), "add");
		}else{
			//update
			model.update();
			DailyOperationRecord.dao.saveRecord(model.getFundId(),OpsFundInfo.TableName, ShiroLogin.getUserName(), "update");
		}
		redirect("/fund/list");
	}

	public void delete(){
		if(OpsFundInfo.dao.deleteFund(getParaToInt("id"))){
			DailyOperationRecord.dao.saveRecord(getParaToInt("id"),OpsFundInfo.TableName, ShiroLogin.getUserName(), "delete");
			renderJson(ReturnT.SUCCESS);
		}else{
			renderJson(ReturnT.FAIL);
		}
	}

	public void  selectById(){
		renderJson("fundInfo",OpsFundInfo.dao.getFundInfoFromId(getParaToInt("id")));
	}

	public void  selectByName(){
		renderJson("fundInfo",OpsFundInfo.dao.getFundInfoFromName(getPara("name")));
	}
}
