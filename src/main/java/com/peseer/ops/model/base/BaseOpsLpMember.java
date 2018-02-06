package com.peseer.ops.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseOpsLpMember<M extends BaseOpsLpMember<M>> extends Model<M> implements IBean {

	/**
	 * 表名
	 */
	public static final String TableName = "ops_lp_member";

	/**
	 * 自增编号
	 */
	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	/**
	 * 自增编号
	 */
	public java.lang.Integer getId() {
		return get("id");
	}

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
	 * LP成员名称
	 */
	public void setLpMember(java.lang.String lpMember) {
		set("lp_member", lpMember);
	}

	/**
	 * LP成员名称
	 */
	public java.lang.String getLpMember() {
		return get("lp_member");
	}

	/**
	 * LP成员类型（1-企业；2-个人）
	 */
	public void setLpMemberType(java.lang.Integer lpMemberType) {
		set("lp_member_type", lpMemberType);
	}

	/**
	 * LP成员类型（1-企业；2-个人）
	 */
	public java.lang.Integer getLpMemberType() {
		return get("lp_member_type");
	}

	/**
	 * LP成员对应实体
	 */
	public void setLpMemberEntity(java.lang.String lpMemberEntity) {
		set("lp_member_entity", lpMemberEntity);
	}

	/**
	 * LP成员对应实体
	 */
	public java.lang.String getLpMemberEntity() {
		return get("lp_member_entity");
	}

	/**
	 * LP成员出资比例
	 */
	public void setLpMemberRate(java.lang.Double lpMemberRate) {
		set("lp_member_rate", lpMemberRate);
	}

	/**
	 * LP成员出资比例
	 */
	public java.lang.Double getLpMemberRate() {
		return get("lp_member_rate");
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
