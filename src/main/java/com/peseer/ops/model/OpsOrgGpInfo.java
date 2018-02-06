package com.peseer.ops.model;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.peseer.ops.model.base.BaseOpsOrgGpInfo;
import com.peseer.ops.util.StringUtil;

/**
 * 机构(GP)信息数据
 */
@SuppressWarnings("serial")
public class OpsOrgGpInfo extends BaseOpsOrgGpInfo<OpsOrgGpInfo> {
	public static final OpsOrgGpInfo dao = new OpsOrgGpInfo();

	public OpsOrgGpInfo getOrgInfoFromId(Object orgId) {
		return findFirst("select * from ops_org_gp_info where org_id = ?", orgId);
	}

	public OpsOrgGpInfo getOrgInfoFromName(Object orgName) {
		return findFirst("select * from ops_org_gp_info where org_cn_name = ?", orgName);
	}

	public Page<OpsOrgGpInfo> getOrgInfoFromName(String orgName, String type, int from) {

		String exectSql = String.format(" from %s where 1 = 1 ", TableName);

		List<Object> params = new ArrayList<>();
		if(!StringUtil.isNullOrEmpty(orgName)&&!StringUtil.isNullOrEmpty(type)){
			switch(type){
				case "1":
					exectSql += " and org_id = ? ";
					params.add(StringUtil.parseInt(orgName, 0));
					 break;
				case "2":
					exectSql += " and (org_cn_name like ? or org_cn_short like ?) ";
					params.add("%"+orgName+"%");
					params.add("%"+orgName+"%");
					break;
				default: break;
			}
		}

		exectSql += " order by org_id desc ";

		return dao.paginate(from,20,"select * ",exectSql,params.toArray());
	}

	public void insertOrgInfo(OpsOrgGpInfo orgInfo) {
		Record record = new Record();
		record.setColumns(orgInfo);
		Db.save(TableName, record);
	}

	public void updateOrgInfo(OpsOrgGpInfo orgInfo) {
		Record record = new Record();
		record.setColumns(orgInfo);
		Db.update(TableName, record);
	}

	public boolean deleteOrgInfo(Object id) {
//		return Db.update(String.format("delete from %s where org_id = ?", TableName), id);
		try{
			deleteById(id);
			return true;
		}catch(Exception ex){
			return false;
		}
	}
}
