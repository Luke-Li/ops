package com.peseer.ops.model;

import com.peseer.ops.model.base.BaseOpsCategoryMedia;

/**
 * 新闻类型，派生于统一后的基础类型，只是针对新闻类型将基础分类的名称修改成合适的别称
 */
@SuppressWarnings("serial")
public class OpsCategoryMedia extends BaseOpsCategoryMedia<OpsCategoryMedia> {
	public static final OpsCategoryMedia dao = new OpsCategoryMedia();
}
