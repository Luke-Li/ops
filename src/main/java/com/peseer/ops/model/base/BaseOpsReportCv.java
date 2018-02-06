package com.peseer.ops.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseOpsReportCv<M extends BaseOpsReportCv<M>> extends Model<M> implements IBean {

	/**
	 * 表名
	 */
	public static final String TableName = "ops_report_cv";

	/**
	 * 自增逐渐
	 */
	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	/**
	 * 自增逐渐
	 */
	public java.lang.Integer getId() {
		return get("id");
	}

	/**
	 * 行业类型ID（自定义）
	 */
	public void setIndustryId(java.lang.Integer industryId) {
		set("industry_id", industryId);
	}

	/**
	 * 行业类型ID（自定义）
	 */
	public java.lang.Integer getIndustryId() {
		return get("industry_id");
	}

	/**
	 * 行业类型名称（自定义）
	 */
	public void setIndustryType(java.lang.String industryType) {
		set("industry_type", industryType);
	}

	/**
	 * 行业类型名称（自定义）
	 */
	public java.lang.String getIndustryType() {
		return get("industry_type");
	}

	/**
	 * 报告路径
	 */
	public void setReportPath(java.lang.String reportPath) {
		set("report_path", reportPath);
	}

	/**
	 * 报告路径
	 */
	public java.lang.String getReportPath() {
		return get("report_path");
	}

	/**
	 * 报告名称
	 */
	public void setReportName(java.lang.String reportName) {
		set("report_name", reportName);
	}

	/**
	 * 报告名称
	 */
	public java.lang.String getReportName() {
		return get("report_name");
	}

	/**
	 * 报告发布日期
	 */
	public void setPubTime(java.util.Date pubTime) {
		set("pub_time", pubTime);
	}

	/**
	 * 报告发布日期
	 */
	public java.util.Date getPubTime() {
		return get("pub_time");
	}

	/**
	 * 报告发布频次（2.月报；3.季报；4.半年报；5.年报）
	 */
	public void setFreqType(java.lang.Integer freqType) {
		set("freq_type", freqType);
	}

	/**
	 * 报告发布频次（2.月报；3.季报；4.半年报；5.年报）
	 */
	public java.lang.Integer getFreqType() {
		return get("freq_type");
	}

}
