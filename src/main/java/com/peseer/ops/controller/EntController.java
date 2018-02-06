package com.peseer.ops.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.peseer.ops.biz.EntFilter;
import com.peseer.ops.model.OpsFundInfo;
import com.peseer.ops.util.KeyValuePair;
import com.peseer.ops.util.ReturnT;

@ControllerBind(controllerKey = "/ent")
public class EntController extends Controller{

	public void detail(){
		renderJson(Db.use("qxb").findFirst("select * from qxb_ent_info where id = ? ",getParaToInt("id")));
	}

	/**
	 * 基金列表信息处理
	 */
	public void list(){
		String name = getPara("name");
		String type = getPara("type");
		Integer page = getParaToInt("page",1);

		setAttr("page",OpsFundInfo.dao.selectFundInfo(name,type,page));
		setAttr("name", name);

		render("ent.list.less.html");
	}

	public void query_ent(){
		String name = getPara("name");
		List<KeyValuePair<String, String>> result = EntFilter.queryEnt(name, getParaToInt("limit",50));
		renderJson(result);
	}

	public void query_uuid(){
		String name = getPara("name");
		String uuid = EntFilter.getUUID(name);

		ReturnT<String> result;
		if(StringUtils.isBlank(uuid)){
			result = ReturnT.FAIL;
			result.setMsg("当前公司未获取到UUID");
		}else{
			result = ReturnT.SUCCESS;
			result.setContent(uuid);
		}

		renderJson(result);
	}

	/**
	 * 基金列表信息处理
	 */
	public void list_less(){
		String name = getPara("name");
		Integer page = getParaToInt("page",1);

		String exectSql = " from qxb_ent_info where 1 = 1 ";

		exectSql += StringUtils.isBlank(name)?StringUtils.EMPTY: (" and name like ? ");

		Page<Record> records;
		if(StringUtils.isBlank(name)){
			records = new Page<>(null,0,0,0,0);
		}else{
			records = StringUtils.isBlank(name) ? Db.use("qxb").paginate(page,20,"select uuid,name,scope ",exectSql) : Db.use("qxb").paginate(page,20,"select uuid,name,scope ",exectSql,"%"+name+"%");
		}



		setAttr("page",records);
		setAttr("name", name);

		render("ent.list.less.html");
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
		}else{
			//update
			model.update();
		}
		redirect("/fund/list");
	}

	public void delete(){
		OpsFundInfo.dao.deleteFund(getParaToInt("id"));
		renderJson(ReturnT.SUCCESS);
	}

	public void  selectById(){
		renderJson("fundInfo",OpsFundInfo.dao.getFundInfoFromId(getParaToInt("id")));
	}

	public void  selectByName(){
		renderJson("fundInfo",OpsFundInfo.dao.getFundInfoFromName(getPara("name")));
	}
}
