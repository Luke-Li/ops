package com.peseer.ops.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseOpsOrgGpInfo<M extends BaseOpsOrgGpInfo<M>> extends Model<M> implements IBean {

	/**
	 * 表名
	 */
	public static final String TableName = "ops_org_gp_info";

	/**
	 * 机构Id
	 */
	public void setOrgId(java.lang.Integer orgId) {
		set("org_id", orgId);
	}

	/**
	 * 机构Id
	 */
	public java.lang.Integer getOrgId() {
		return get("org_id");
	}

	/**
	 * 机构名称
	 */
	public void setOrgCnName(java.lang.String orgCnName) {
		set("org_cn_name", orgCnName);
	}

	/**
	 * 机构名称
	 */
	public java.lang.String getOrgCnName() {
		return get("org_cn_name");
	}

	/**
	 * 中文名简称
	 */
	public void setOrgCnShort(java.lang.String orgCnShort) {
		set("org_cn_short", orgCnShort);
	}

	/**
	 * 中文名简称
	 */
	public java.lang.String getOrgCnShort() {
		return get("org_cn_short");
	}

	/**
	 * 机构别名
	 */
	public void setOrgNickname(java.lang.String orgNickname) {
		set("org_nickname", orgNickname);
	}

	/**
	 * 机构别名
	 */
	public java.lang.String getOrgNickname() {
		return get("org_nickname");
	}

	/**
	 * 机构英文名称
	 */
	public void setOrgEnName(java.lang.String orgEnName) {
		set("org_en_name", orgEnName);
	}

	/**
	 * 机构英文名称
	 */
	public java.lang.String getOrgEnName() {
		return get("org_en_name");
	}

	/**
	 * RDD整理映射企业实体
	 */
	public void setOrgMapEntity(java.lang.String orgMapEntity) {
		set("org_map_entity", orgMapEntity);
	}

	/**
	 * RDD整理映射企业实体
	 */
	public java.lang.String getOrgMapEntity() {
		return get("org_map_entity");
	}

	/**
	 * 机构描述
	 */
	public void setOrgCnDesc(java.lang.String orgCnDesc) {
		set("org_cn_desc", orgCnDesc);
	}

	/**
	 * 机构描述
	 */
	public java.lang.String getOrgCnDesc() {
		return get("org_cn_desc");
	}

	/**
	 * 基金信息
	 */
	public void setFundInfo(java.lang.String fundInfo) {
		set("fund_info", fundInfo);
	}

	/**
	 * 基金信息
	 */
	public java.lang.String getFundInfo() {
		return get("fund_info");
	}

	/**
	 * 投资信息
	 */
	public void setInvestInfo(java.lang.String investInfo) {
		set("invest_info", investInfo);
	}

	/**
	 * 投资信息
	 */
	public java.lang.String getInvestInfo() {
		return get("invest_info");
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