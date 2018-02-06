package com.peseer.ops.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseOpsOrgUserInfo<M extends BaseOpsOrgUserInfo<M>> extends Model<M> implements IBean {

	/**
	 * 表名
	 */
	public static final String TableName = "ops_org_user_info";

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
	 * 机构ID
	 */
	public void setOrganizeId(java.lang.Integer organizeId) {
		set("organize_id", organizeId);
	}

	/**
	 * 机构ID
	 */
	public java.lang.Integer getOrganizeId() {
		return get("organize_id");
	}

	/**
	 * 机构名称
	 */
	public void setOrganizeName(java.lang.String organizeName) {
		set("organize_name", organizeName);
	}

	/**
	 * 机构名称
	 */
	public java.lang.String getOrganizeName() {
		return get("organize_name");
	}

	/**
	 * 用户ID
	 */
	public void setUserId(java.lang.Integer userId) {
		set("user_id", userId);
	}

	/**
	 * 用户ID
	 */
	public java.lang.Integer getUserId() {
		return get("user_id");
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
	 * 性别
	 */
	public void setSex(java.lang.String sex) {
		set("sex", sex);
	}

	/**
	 * 性别
	 */
	public java.lang.String getSex() {
		return get("sex");
	}

	/**
	 * 职务
	 */
	public void setTitle(java.lang.String title) {
		set("title", title);
	}

	/**
	 * 职务
	 */
	public java.lang.String getTitle() {
		return get("title");
	}

	/**
	 * 关注领域
	 */
	public void setFocusDomain(java.lang.String focusDomain) {
		set("focus_domain", focusDomain);
	}

	/**
	 * 关注领域
	 */
	public java.lang.String getFocusDomain() {
		return get("focus_domain");
	}

	/**
	 * 投资项目
	 */
	public void setInvestProjects(java.lang.String investProjects) {
		set("invest_projects", investProjects);
	}

	/**
	 * 投资项目
	 */
	public java.lang.String getInvestProjects() {
		return get("invest_projects");
	}

	/**
	 * 联系方式
	 */
	public void setContact(java.lang.String contact) {
		set("contact", contact);
	}

	/**
	 * 联系方式
	 */
	public java.lang.String getContact() {
		return get("contact");
	}

	/**
	 * 简介
	 */
	public void setDescription(java.lang.String description) {
		set("description", description);
	}

	/**
	 * 简介
	 */
	public java.lang.String getDescription() {
		return get("description");
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
