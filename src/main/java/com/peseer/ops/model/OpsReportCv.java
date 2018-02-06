package com.peseer.ops.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.plugin.activerecord.Page;
import com.peseer.ops.model.base.BaseOpsReportCv;

/**
 * 投中报告
 */
@SuppressWarnings("serial")
public class OpsReportCv extends BaseOpsReportCv<OpsReportCv> {
	public static final OpsReportCv dao = new OpsReportCv();

	public static Map<String,String> industryMap = new HashMap<>();

	private String cat_name;

	public String getCat_name() {
		return cat_name;
	}

	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}

	public Page<OpsReportCv> selectReports(String key, String type, int from){
		String exectSql = String.format(" from %s where 1 = 1 ",TableName);

		List<Object> params = new ArrayList<>();
		if(StringUtils.isNoneBlank(key)){
			switch(type){
				case "1":
					exectSql = exectSql + " and report_name like ?";
					params.add("%"+key+"%");
					break;
				case "2":
					exectSql = exectSql + " and industry_type like ?";
					params.add("%"+key+"%");
					break;
				default: break;
			}
		}

//		exectSql += StringUtils.isBlank(key)?StringUtils.EMPTY: (" and fund_name like ? ");

		exectSql += " order by id desc ";

		return dao.paginate(from,20,"select * ",exectSql,params.toArray());
	}

	public OpsReportCv getLastRecordFromName(String reportName){
		return findFirst(String.format("select * from %s where report_name=? order by id desc", TableName), reportName);
	}

	public void SyncIndustryId(){
		switch(this.getIndustryType()){
			case "VC/PE":
				this.setIndustryId(2001001);
				break;
			case "IPO":
				this.setIndustryId(2001002);
				break;
			case "并购":
				this.setIndustryId(2001003);
				break;
			case "行业统计":
				this.setIndustryId(2001004);
				break;
			case "专题分析":
				this.setIndustryId(2001005);
				break;
			case "热点评论":
				this.setIndustryId(2001006);
				break;
			default:
				break;
		}
	}
//
//	public void SyncCatName(){
//		this.setCat_name((String) OpsCategoryBiz.dao.getValue(this.getBizCatId()));
//	}
}
