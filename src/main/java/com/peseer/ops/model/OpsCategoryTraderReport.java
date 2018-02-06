package com.peseer.ops.model;

import com.peseer.ops.model.base.BaseOpsCategoryTraderReport;

/**
 * 券商报告类型，映射于统一后的基础类型，只是针对券商报告类型将基础分类的名称修改成合适的别称
 */
@SuppressWarnings("serial")
public class OpsCategoryTraderReport extends BaseOpsCategoryTraderReport<OpsCategoryTraderReport> {
	public static final OpsCategoryTraderReport dao = new OpsCategoryTraderReport();
}
