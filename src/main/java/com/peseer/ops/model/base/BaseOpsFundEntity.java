package com.peseer.ops.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseOpsFundEntity<M extends BaseOpsFundEntity<M>> extends Model<M> implements IBean {

	/**
	 * 表名
	 */
	public static final String TableName = "ops_fund_entity";

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
	 * 基金ID (关联 ops_fund_info)
	 */
	public void setFundId(java.lang.Integer fundId) {
		set("fund_id", fundId);
	}

	/**
	 * 基金ID (关联 ops_fund_info)
	 */
	public java.lang.Integer getFundId() {
		return get("fund_id");
	}

	/**
	 * 基金名称
	 */
	public void setFundName(java.lang.String fundName) {
		set("fund_name", fundName);
	}

	/**
	 * 基金名称
	 */
	public java.lang.String getFundName() {
		return get("fund_name");
	}

	/**
	 * 基金实体
	 */
	public void setFundEntity(java.lang.String fundEntity) {
		set("fund_entity", fundEntity);
	}

	/**
	 * 基金实体
	 */
	public java.lang.String getFundEntity() {
		return get("fund_entity");
	}

	/**
	 * 基金实体的UUID
	 */
	public void setEntityUuid(java.lang.String entityUuid) {
		set("entity_uuid", entityUuid);
	}

	/**
	 * 基金实体的UUID
	 */
	public java.lang.String getEntityUuid() {
		return get("entity_uuid");
	}

	/**
	 * 基金管理人ID（关联ops_org_user_info）
	 */
	public void setUserId(java.lang.Integer userId) {
		set("user_id", userId);
	}

	/**
	 * 基金管理人ID（关联ops_org_user_info）
	 */
	public java.lang.Integer getUserId() {
		return get("user_id");
	}

	/**
	 * 基金管理人姓名（关联ops_org_user_info）
	 */
	public void setUserName(java.lang.String userName) {
		set("user_name", userName);
	}

	/**
	 * 基金管理人姓名（关联ops_org_user_info）
	 */
	public java.lang.String getUserName() {
		return get("user_name");
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
