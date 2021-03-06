package com.peseer.ops.model.account.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseUdfMonitor<M extends BaseUdfMonitor<M>> extends Model<M> implements IBean {

	/**
	 * 表名
	 */
	public static final String TableName = "udf_monitor";

	/**
	 * 
	 */
	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	/**
	 * 
	 */
	public java.lang.Integer getId() {
		return get("id");
	}

	/**
	 * 登录名
	 */
	public void setUid(java.lang.String uid) {
		set("uid", uid);
	}

	/**
	 * 登录名
	 */
	public java.lang.String getUid() {
		return get("uid");
	}

	/**
	 * 监控类型：1-企业；2-机构
	 */
	public void setType(java.lang.Integer type) {
		set("type", type);
	}

	/**
	 * 监控类型：1-企业；2-机构
	 */
	public java.lang.Integer getType() {
		return get("type");
	}

	/**
	 * 监控内容
	 */
	public void setContent(java.lang.String content) {
		set("content", content);
	}

	/**
	 * 监控内容
	 */
	public java.lang.String getContent() {
		return get("content");
	}

	/**
	 * 监控内容对应的企业UUID
	 */
	public void setUuid(java.lang.String uuid) {
		set("uuid", uuid);
	}

	/**
	 * 监控内容对应的企业UUID
	 */
	public java.lang.String getUuid() {
		return get("uuid");
	}

	/**
	 * 监控内容对应的企业ORGID
	 */
	public void setOrgId(java.lang.Integer orgId) {
		set("org_id", orgId);
	}

	/**
	 * 监控内容对应的企业ORGID
	 */
	public java.lang.Integer getOrgId() {
		return get("org_id");
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
