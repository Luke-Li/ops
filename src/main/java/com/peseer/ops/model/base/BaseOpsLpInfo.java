package com.peseer.ops.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseOpsLpInfo<M extends BaseOpsLpInfo<M>> extends Model<M> implements IBean {

	/**
	 * 表名
	 */
	public static final String TableName = "ops_lp_info";

	/**
	 * LP编号
	 */
	public void setLpId(java.lang.Integer lpId) {
		set("lp_id", lpId);
	}

	/**
	 * LP编号
	 */
	public java.lang.Integer getLpId() {
		return get("lp_id");
	}

	/**
	 * LP名称
	 */
	public void setLpName(java.lang.String lpName) {
		set("lp_name", lpName);
	}

	/**
	 * LP名称
	 */
	public java.lang.String getLpName() {
		return get("lp_name");
	}

	/**
	 * LP对应实体
	 */
	public void setLpEntity(java.lang.String lpEntity) {
		set("lp_entity", lpEntity);
	}

	/**
	 * LP对应实体
	 */
	public java.lang.String getLpEntity() {
		return get("lp_entity");
	}

	/**
	 * LP类型（1-企业； 2-个人； 3-机构）
	 */
	public void setLpType(java.lang.Integer lpType) {
		set("lp_type", lpType);
	}

	/**
	 * LP类型（1-企业； 2-个人； 3-机构）
	 */
	public java.lang.Integer getLpType() {
		return get("lp_type");
	}

	/**
	 * LP描述
	 */
	public void setLpTips(java.lang.String lpTips) {
		set("lp_tips", lpTips);
	}

	/**
	 * LP描述
	 */
	public java.lang.String getLpTips() {
		return get("lp_tips");
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