package com.peseer.ops.controller;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.peseer.ops.model.OpsInvestEventDetail;

/**
 * IndexController
 */
@ControllerBind(controllerKey = "/")
public class IndexController extends Controller {
	public void index() {
		setAttr("page", OpsInvestEventDetail.dao.paginate(getParaToInt("page", 1), 10));
		render("index.html");
	}
}





