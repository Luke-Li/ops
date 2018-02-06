	package com.peseer.ops.controller;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.ext.route.ControllerBind;
import com.peseer.ops.model.DailyOperationRecord;
import com.peseer.ops.model.OpsInvestEventDetail;
import com.peseer.ops.model.online.DailyEvent75;
import com.peseer.ops.util.ReturnT;
import com.peseer.ops.util.ShiroLogin;

@ControllerBind(controllerKey = "/invest")
public class InvestEventController extends Controller {

	public void list(){
		String key = getPara("key");
		String type = getPara("type");

		Integer page = getParaToInt("page",1);

		setAttr("page",OpsInvestEventDetail.dao.selectInvestEvents(key,type,page));
		setAttr("key", key);
		setAttr("type",type);

		render("invest.list.html");
	}

	@Before(GET.class)
	public void edit(){
		Integer id = getParaToInt("id");
		if(null != id){
			OpsInvestEventDetail detail = OpsInvestEventDetail.dao.getInvestEventFromId(id);

			String model = getPara("model");

					if(StringUtils.isNotBlank(model) && model.equals("copy")){
						detail = detail.remove("event_id");
					}

			setAttr("model", detail);
		}else{
			setAttr("model", OpsInvestEventDetail.dao);
		}

		render("invest.edit.html");
	}

	@Before(POST.class)
	public void update(){
		OpsInvestEventDetail model = getModel(OpsInvestEventDetail.class,"model");
		if(model.getEventId() == null){
			//add
			model.save();
			model = OpsInvestEventDetail.dao.getLastRecordFromName(model.getEventTitle());
			DailyEvent75.dao.saveEvent(model);
			DailyOperationRecord.dao.saveRecord(model.getEventId(),OpsInvestEventDetail.TableName, ShiroLogin.getUserName(), "add");

		}else{
			//update
			model.update();
			DailyEvent75.dao.updateEvent(model);
			DailyOperationRecord.dao.saveRecord(model.getEventId(), OpsInvestEventDetail.TableName,ShiroLogin.getUserName(), "update");

		}
		redirect("/invest/list");
	}

	public void getEvent(){
		setAttr("event",OpsInvestEventDetail.dao.getInvestEventFromId(getParaToInt("id")));
		this.renderJson();
	}

	public void createEvent(){
		OpsInvestEventDetail event = getModel(OpsInvestEventDetail.class);
		OpsInvestEventDetail.dao.insertEvent(event);
		renderJson("status","success");
	}

	public void updateEvent(){
		OpsInvestEventDetail event = getModel(OpsInvestEventDetail.class);
		OpsInvestEventDetail.dao.updateEvent(event);
		renderJson("status","success");
	}

	public void delete(){

		if(OpsInvestEventDetail.dao.deleteEvent(getParaToInt("id"))){
			DailyEvent75.dao.deleteEvent(getParaToInt("id"), "invest");
			DailyOperationRecord.dao.saveRecord(getParaToInt("id"), OpsInvestEventDetail.TableName,ShiroLogin.getUserName(), "delete");
			renderJson(ReturnT.SUCCESS);
		}else{
			renderJson(ReturnT.FAIL);
		}
	}
}
