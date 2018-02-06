package com.peseer.ops.model.online;

import com.peseer.ops.model.online.base.BaseStatDashboard;

/**
 * 首页仪表板统计信息，包含（统计字段，当日量，总量）
 */
@SuppressWarnings("serial")
public class StatDashboard extends BaseStatDashboard<StatDashboard> {
	public static final StatDashboard dao = new StatDashboard();
}
