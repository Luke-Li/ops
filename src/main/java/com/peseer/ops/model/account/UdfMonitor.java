package com.peseer.ops.model.account;

import com.peseer.ops.model.account.base.BaseUdfMonitor;

/**
 * 用户自定义信息 - 监控模块
 */
@SuppressWarnings("serial")
public class UdfMonitor extends BaseUdfMonitor<UdfMonitor> {
	public static final UdfMonitor dao = new UdfMonitor();
}
