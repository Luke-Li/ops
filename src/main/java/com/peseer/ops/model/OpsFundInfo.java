package com.peseer.ops.model;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.peseer.ops.model.base.BaseOpsFundInfo;
import com.peseer.ops.util.StringUtil;

/**
 * 基金信息（基础数据来源于cvsource）
 */
@SuppressWarnings("serial")
public class OpsFundInfo extends BaseOpsFundInfo<OpsFundInfo> {
	public static final OpsFundInfo dao = new OpsFundInfo();

	/**
	 * 根据ID获取基金信息
	 * @param fundId
	 * @return
	 */
	public OpsFundInfo getFundInfo(Object fundId){
		if(null != fundId)
			return dao.findById(fundId);
		else
			return null;
	}

	/**
	 * 基金查询分页
	 * @param fundName
	 * @param page
	 * @return
	 */
	public Page<OpsFundInfo> selectFundInfo(String key,String type, Integer from){

		String exectSql = String.format(" from %s where 1 = 1 ",TableName);

		List<Object> params = new ArrayList<>();
		if(!StringUtil.isNullOrEmpty(key)&&!StringUtil.isNullOrEmpty(type)){
			switch(type){
				case "1":
					exectSql += " and fund_id = ? ";
					params.add(StringUtil.parseInt(key, 0));
					 break;
				case "2":
					exectSql += " and fund_name like ? ";
					params.add("%"+key+"%");
					break;
				default: break;
			}
		}

		exectSql += " order by fund_id desc ";

		return dao.paginate(from,20,"select * ",exectSql,params.toArray());
	}

	public void insertFundInfo(OpsFundInfo fundInfo){
		Record record = new Record();
		record.setColumns(fundInfo);
		Db.save(TableName, record);
	}

	public void updateFundInfo(OpsFundInfo fundInfo){
		Record record = new Record();
		record.setColumns(fundInfo);
		Db.update(TableName, record);
	}

	/**
	 * 删除基金
	 * @param id
	 * @return
	 */
	public boolean deleteFund(Object id){
		try{
			deleteById(id);
			return true;
		}catch(Exception ex){
			return false;
		}
	}

	public OpsFundInfo getFundInfoFromId(Object FundId) {
		return findFirst(String.format("select * from %s where fund_id = ?",TableName), FundId);
	}

	public OpsFundInfo getFundInfoFromName(Object FundName) {
		return findFirst(String.format("select * from %s where fund_name = ?",TableName), FundName);
	}

}
