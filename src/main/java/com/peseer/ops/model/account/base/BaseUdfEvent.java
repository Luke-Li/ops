package com.peseer.ops.model.account.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseUdfEvent<M extends BaseUdfEvent<M>> extends Model<M> implements IBean {

	/**
	 * 表名
	 */
	public static final String TableName = "udf_event";

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
	 * 情报类型:1-募资；2-投资；3-并购；4-退出；5-行业情报；6-交易情报；
	 */
	public void setType(java.lang.Integer type) {
		set("type", type);
	}

	/**
	 * 情报类型:1-募资；2-投资；3-并购；4-退出；5-行业情报；6-交易情报；
	 */
	public java.lang.Integer getType() {
		return get("type");
	}

	/**
	 * 事件标题
	 */
	public void setTitle(java.lang.String title) {
		set("title", title);
	}

	/**
	 * 事件标题
	 */
	public java.lang.String getTitle() {
		return get("title");
	}

	/**
	 * 当前状态:1-未处理；2-审核中；3-审核无效；4-审核成功
	 */
	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}

	/**
	 * 当前状态:1-未处理；2-审核中；3-审核无效；4-审核成功
	 */
	public java.lang.Integer getStatus() {
		return get("status");
	}

	/**
	 * 项目负责人
	 */
	public void setLeader(java.lang.String leader) {
		set("leader", leader);
	}

	/**
	 * 项目负责人
	 */
	public java.lang.String getLeader() {
		return get("leader");
	}

	/**
	 * 基金名称
	 */
	public void setFund(java.lang.String fund) {
		set("fund", fund);
	}

	/**
	 * 基金名称
	 */
	public java.lang.String getFund() {
		return get("fund");
	}

	/**
	 * 募资 - 资金募集方
	 */
	public void setGpName(java.lang.String gpName) {
		set("gp_name", gpName);
	}

	/**
	 * 募资 - 资金募集方
	 */
	public java.lang.String getGpName() {
		return get("gp_name");
	}

	/**
	 * 募资 - 资金提供方
	 */
	public void setLpName(java.lang.String lpName) {
		set("lp_name", lpName);
	}

	/**
	 * 募资 - 资金提供方
	 */
	public java.lang.String getLpName() {
		return get("lp_name");
	}

	/**
	 * 募资 - 计划募集额度
	 */
	public void setPlanLine(java.lang.String planLine) {
		set("plan_line", planLine);
	}

	/**
	 * 募资 - 计划募集额度
	 */
	public java.lang.String getPlanLine() {
		return get("plan_line");
	}

	/**
	 * 募资 - 实际募集额度
	 */
	public void setRealLine(java.lang.String realLine) {
		set("real_line", realLine);
	}

	/**
	 * 募资 - 实际募集额度
	 */
	public java.lang.String getRealLine() {
		return get("real_line");
	}

	/**
	 * 投资 - 投资方
	 */
	public void setVcName(java.lang.String vcName) {
		set("vc_name", vcName);
	}

	/**
	 * 投资 - 投资方
	 */
	public java.lang.String getVcName() {
		return get("vc_name");
	}

	/**
	 * 投资 - 被投资方
	 */
	public void setTeamName(java.lang.String teamName) {
		set("team_name", teamName);
	}

	/**
	 * 投资 - 被投资方
	 */
	public java.lang.String getTeamName() {
		return get("team_name");
	}

	/**
	 * 投资 - 轮次
	 */
	public void setRound(java.lang.String round) {
		set("round", round);
	}

	/**
	 * 投资 - 轮次
	 */
	public java.lang.String getRound() {
		return get("round");
	}

	/**
	 * 并购 - 金额
	 */
	public void setMaPay(java.lang.String maPay) {
		set("ma_pay", maPay);
	}

	/**
	 * 并购 - 金额
	 */
	public java.lang.String getMaPay() {
		return get("ma_pay");
	}

	/**
	 * 并购 - 买方
	 */
	public void setMaBuyer(java.lang.String maBuyer) {
		set("ma_buyer", maBuyer);
	}

	/**
	 * 并购 - 买方
	 */
	public java.lang.String getMaBuyer() {
		return get("ma_buyer");
	}

	/**
	 * 并购 - 标的
	 */
	public void setMaTarget(java.lang.String maTarget) {
		set("ma_target", maTarget);
	}

	/**
	 * 并购 - 标的
	 */
	public java.lang.String getMaTarget() {
		return get("ma_target");
	}

	/**
	 * 退出 - 退出方
	 */
	public void setExitName(java.lang.String exitName) {
		set("exit_name", exitName);
	}

	/**
	 * 退出 - 退出方
	 */
	public java.lang.String getExitName() {
		return get("exit_name");
	}

	/**
	 * 退出 - 账面回报率
	 */
	public void setExitBringRate(java.lang.String exitBringRate) {
		set("exit_bring_rate", exitBringRate);
	}

	/**
	 * 退出 - 账面回报率
	 */
	public java.lang.String getExitBringRate() {
		return get("exit_bring_rate");
	}

	/**
	 * 退出 - 账面IRR
	 */
	public void setExitIrr(java.lang.String exitIrr) {
		set("exit_irr", exitIrr);
	}

	/**
	 * 退出 - 账面IRR
	 */
	public java.lang.String getExitIrr() {
		return get("exit_irr");
	}

	/**
	 * 发生日期
	 */
	public void setHappenDate(java.lang.String happenDate) {
		set("happen_date", happenDate);
	}

	/**
	 * 发生日期
	 */
	public java.lang.String getHappenDate() {
		return get("happen_date");
	}

	/**
	 * 消息来源
	 */
	public void setSrcInfo(java.lang.String srcInfo) {
		set("src_info", srcInfo);
	}

	/**
	 * 消息来源
	 */
	public java.lang.String getSrcInfo() {
		return get("src_info");
	}

	/**
	 * 事件详情
	 */
	public void setContent(java.lang.String content) {
		set("content", content);
	}

	/**
	 * 事件详情
	 */
	public java.lang.String getContent() {
		return get("content");
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