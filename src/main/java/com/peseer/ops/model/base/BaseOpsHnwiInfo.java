package com.peseer.ops.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseOpsHnwiInfo<M extends BaseOpsHnwiInfo<M>> extends Model<M> implements IBean {

	/**
	 * 表名
	 */
	public static final String TableName = "ops_hnwi_info";

	/**
	 * 自增Id
	 */
	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	/**
	 * 自增Id
	 */
	public java.lang.Integer getId() {
		return get("id");
	}

	/**
	 * 用户ID(关联ops_lp_info中type=2的 lp_id)
	 */
	public void setUid(java.lang.Integer uid) {
		set("uid", uid);
	}

	/**
	 * 用户ID(关联ops_lp_info中type=2的 lp_id)
	 */
	public java.lang.Integer getUid() {
		return get("uid");
	}

	/**
	 * 用户名
	 */
	public void setUserName(java.lang.String userName) {
		set("user_name", userName);
	}

	/**
	 * 用户名
	 */
	public java.lang.String getUserName() {
		return get("user_name");
	}

	/**
	 * 资本类型（1-控股企业 ； 2-参股企业）
	 */
	public void setType(java.lang.Integer type) {
		set("type", type);
	}

	/**
	 * 资本类型（1-控股企业 ； 2-参股企业）
	 */
	public java.lang.Integer getType() {
		return get("type");
	}

	/**
	 * 企业名称
	 */
	public void setEntName(java.lang.String entName) {
		set("ent_name", entName);
	}

	/**
	 * 企业名称
	 */
	public java.lang.String getEntName() {
		return get("ent_name");
	}

	/**
	 * 企业编号
	 */
	public void setEntUuid(java.lang.String entUuid) {
		set("ent_uuid", entUuid);
	}

	/**
	 * 企业编号
	 */
	public java.lang.String getEntUuid() {
		return get("ent_uuid");
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
