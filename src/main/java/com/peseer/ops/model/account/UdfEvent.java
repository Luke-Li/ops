package com.peseer.ops.model.account;

import com.peseer.ops.model.account.base.BaseUdfEvent;

/**
 * 用户自定义信息 - 事件情报
 */
@SuppressWarnings("serial")
public class UdfEvent extends BaseUdfEvent<UdfEvent> {
	public static final UdfEvent dao = new UdfEvent();
}
