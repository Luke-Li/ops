package com.peseer.ops.controller;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.ext.route.ControllerBind;
import com.peseer.ops.model.DailyOperationRecord;
import com.peseer.ops.model.OpsInvestEventDetail;
import com.peseer.ops.model.OpsReportCv;
import com.peseer.ops.util.ReturnT;
import com.peseer.ops.util.ShiroLogin;

@ControllerBind(controllerKey = "/cvreport")
public class ReportController extends Controller{
	public void list(){
		String key = getPara("key");
		String type = getPara("type");

		Integer page = getParaToInt("page",1);

		setAttr("page",OpsReportCv.dao.selectReports(key,type,page));
		setAttr("key", key);
		setAttr("type",type);

		render("cvreport.list.html");
	}

	@Before(GET.class)
	public void edit(){
		Integer id = getParaToInt("id");
		if(null != id){
			OpsReportCv detail = OpsReportCv.dao.findById(id);

			if(null != detail){
//				detail.SyncCatName();
				String model = getPara("model");

				if(StringUtils.isNotBlank(model) && model.equals("copy")){
					detail = detail.remove("id");
				}
			}


			setAttr("model", detail);
		}else{
			setAttr("model", OpsReportCv.dao);
		}

		render("cvreport.edit.html");
	}

	@Before(POST.class)
	public void update(){
		OpsReportCv model = getModel(OpsReportCv.class,"model");
		model.SyncIndustryId();
		if(model.getId() == null){
			//add
			model.save();
			model = OpsReportCv.dao.getLastRecordFromName(model.getReportName());
			DailyOperationRecord.dao.saveRecord(model.getId(),OpsReportCv.TableName, ShiroLogin.getUserName(), "add");

		}else{
			//update
			model.update();
			DailyOperationRecord.dao.saveRecord(model.getId(), OpsInvestEventDetail.TableName,ShiroLogin.getUserName(), "update");

		}
		redirect("/cvreport/list");
	}


	public void delete(){

		if(OpsReportCv.dao.deleteById(getParaToInt("id"))){
			DailyOperationRecord.dao.saveRecord(getParaToInt("id"), OpsReportCv.TableName,ShiroLogin.getUserName(), "delete");
			renderJson(ReturnT.SUCCESS);
		}else{
			renderJson(ReturnT.FAIL);
		}
	}

}
