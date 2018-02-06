	package com.peseer.ops.controller;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.ext.route.ControllerBind;
import com.peseer.ops.model.DailyOperationRecord;
import com.peseer.ops.model.OpsExitEventDetail;
import com.peseer.ops.model.online.DailyEvent75;
import com.peseer.ops.util.ReturnT;
import com.peseer.ops.util.ShiroLogin;

@ControllerBind(controllerKey = "/exit")
public class ExitEventController extends Controller {

	public void list(){
		String key = getPara("key");
		String type = getPara("type");

		Integer page = getParaToInt("page",1);

		setAttr("page",OpsExitEventDetail.dao.selectExitEvents(key,type,page));
		setAttr("key", key);
		setAttr("type",type);

		render("exit.list.html");
	}

	@Before(GET.class)
	public void edit(){
		Integer id = getParaToInt("id");
		if(null != id){
			OpsExitEventDetail detail = OpsExitEventDetail.dao.getExitEventFromId(id);

			String model = getPara("model");

					if(StringUtils.isNotBlank(model) && model.equals("copy")){
						detail = detail.remove("event_id");
					}

			setAttr("model", detail);
		}else{
			setAttr("model", OpsExitEventDetail.dao);
		}

		render("exit.edit.html");
	}

	@Before(POST.class)
	public void update(){
		OpsExitEventDetail model = getModel(OpsExitEventDetail.class,"model");
		if(model.getEventId() == null){
			//add
			model.save();
			model = OpsExitEventDetail.dao.getLastRecordFromName(model.getEventTitle());
			DailyEvent75.dao.saveEvent(model);
			DailyOperationRecord.dao.saveRecord(model.getEventId(),OpsExitEventDetail.TableName, ShiroLogin.getUserName(), "add");

		}else{
			//update
			model.update();
			DailyEvent75.dao.updateEvent(model);
			DailyOperationRecord.dao.saveRecord(model.getEventId(),OpsExitEventDetail.TableName, ShiroLogin.getUserName(), "update");

		}
		redirect("/exit/list");
	}

	public void getEvent(){
		setAttr("event",OpsExitEventDetail.dao.getExitEventFromId(getParaToInt("id")));
		this.renderJson();
	}

	public void createEvent(){
		OpsExitEventDetail event = getModel(OpsExitEventDetail.class);
		OpsExitEventDetail.dao.insertEvent(event);
		renderJson("status","success");
	}

	public void updateEvent(){
		OpsExitEventDetail event = getModel(OpsExitEventDetail.class);
		OpsExitEventDetail.dao.updateEvent(event);
		renderJson("status","success");
	}

	public void delete(){

		if(OpsExitEventDetail.dao.deleteEvent(getParaToInt("id"))){
			DailyEvent75.dao.deleteEvent(getParaToInt("id"), "exit");
			DailyOperationRecord.dao.saveRecord(getParaToInt("id"),OpsExitEventDetail.TableName, ShiroLogin.getUserName(), "delete");
			renderJson(ReturnT.SUCCESS);
		}else{
			renderJson(ReturnT.FAIL);
		}
	}
}
