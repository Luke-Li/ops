package com.peseer.ops.model;

import com.peseer.ops.model.base.BaseDynamicCookie;

/**
 * 动态COOKIE
 */
@SuppressWarnings("serial")
public class DynamicCookie extends BaseDynamicCookie<DynamicCookie> {
	public static final DynamicCookie dao = new DynamicCookie();

	public DynamicCookie abc(){

		return dao.findFirst("select * from "+TableName +" limit 1");
	}


}
