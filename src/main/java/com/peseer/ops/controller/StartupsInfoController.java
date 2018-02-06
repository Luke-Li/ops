package com.peseer.ops.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.plugin.activerecord.Page;
import com.peseer.ops.model.DailyOperationRecord;
import com.peseer.ops.model.OpsStartupsInfo;
import com.peseer.ops.util.ReturnT;
import com.peseer.ops.util.ShiroLogin;
import com.peseer.ops.util.StringUtil;

@ControllerBind(controllerKey = "/startupsinfo")
public class StartupsInfoController extends Controller {

	public void list() {
		String key = getPara("key");
		String type = getPara("type");
		Integer page = getParaToInt("page", 1);

		Page<OpsStartupsInfo> startupInfos = OpsStartupsInfo.dao.selectStartupsInfo(key, type, page);

		List<OpsStartupsInfo> list = startupInfos.getList();

		for (int i = 0; i < list.size(); i++) {
			OpsStartupsInfo tmp = list.get(i);
			if (tmp != null && !StringUtil.isNullOrEmpty(tmp.getBrief()) && tmp.getBrief().length() > 40) {
				String tmpString = tmp.getBrief().substring(0, 40) + "...";
				tmp.setBrief(tmpString);
				list.set(i, tmp);
			}
		}

		Page<OpsStartupsInfo> result = new Page<>(list, startupInfos.getPageNumber(), startupInfos.getPageSize(),
				startupInfos.getTotalPage(), startupInfos.getTotalRow());
		list = null;
		setAttr("page", result);
		setAttr("key", key);
		setAttr("type", type);

		render("startups.list.html");
	}

	public void edit(){
		Integer id = getParaToInt("id");

		if(null != id){
			OpsStartupsInfo detail = OpsStartupsInfo.dao.findById(id);
			String model = getPara("model");

			if(StringUtils.isNotBlank(model) && model.equals("copy")){
				detail = detail.remove("id");
			}
			setAttr("model", detail);
		}else{
			setAttr("model", OpsStartupsInfo.dao);
		}
		render("startups.edit.html");
	}

	@Before(POST.class)
	public void update(){
		OpsStartupsInfo model = getModel(OpsStartupsInfo.class,"model");
		if(model.getId() == null){
			//add
			model.save();
			model = OpsStartupsInfo.dao.findById(model.getId());
			DailyOperationRecord.dao.saveRecord(model.getId(),OpsStartupsInfo.TableName, ShiroLogin.getUserName(), "add");
		}else{
			//update
			model.update();
			DailyOperationRecord.dao.saveRecord(model.getId(),OpsStartupsInfo.TableName, ShiroLogin.getUserName(), "update");
		}
		redirect("/startupsinfo/list");
	}

	public void delete(){
		if(OpsStartupsInfo.dao.deleteById(getParaToInt("id"))){
			DailyOperationRecord.dao.saveRecord(getParaToInt("id"),OpsStartupsInfo.TableName, ShiroLogin.getUserName(), "delete");
			renderJson(ReturnT.SUCCESS);
		}else{
			renderJson(ReturnT.FAIL);
		}
	}
}
