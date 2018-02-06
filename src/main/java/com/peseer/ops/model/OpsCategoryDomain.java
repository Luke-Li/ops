package com.peseer.ops.model;

import com.peseer.ops.model.base.BaseOpsCategoryDomain;

/**
 * 投资人和机构关注投资领域的类型，映射于统一后的基础类型，只是针对投资领域类型将基础分类的名称修改成合适的别称
 */
@SuppressWarnings("serial")
public class OpsCategoryDomain extends BaseOpsCategoryDomain<OpsCategoryDomain> {
	public static final OpsCategoryDomain dao = new OpsCategoryDomain();
}
