package com.peseer.ops.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseEventRoundOne<M extends BaseEventRoundOne<M>> extends Model<M> implements IBean {

	/**
	 * 表名
	 */
	public static final String TableName = "event_round_one";

	/**
	 * 轮次ID
	 */
	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	/**
	 * 轮次ID
	 */
	public java.lang.Integer getId() {
		return get("id");
	}

	/**
	 * 轮次名字
	 */
	public void setRoundName(java.lang.String roundName) {
		set("round_name", roundName);
	}

	/**
	 * 轮次名字
	 */
	public java.lang.String getRoundName() {
		return get("round_name");
	}

	/**
	 * 创建时间
	 */
	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}

	/**
	 * 创建时间
	 */
	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	/**
	 * 更新时间
	 */
	public void setUpdateTime(java.util.Date updateTime) {
		set("update_time", updateTime);
	}

	/**
	 * 更新时间
	 */
	public java.util.Date getUpdateTime() {
		return get("update_time");
	}

}
