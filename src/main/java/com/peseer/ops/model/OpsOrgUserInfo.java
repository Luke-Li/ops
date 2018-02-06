package com.peseer.ops.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.peseer.ops.model.base.BaseOpsOrgUserInfo;
import com.peseer.ops.util.StringUtil;

/**
 * 机构人物数据
 */
@SuppressWarnings("serial")
public class OpsOrgUserInfo extends BaseOpsOrgUserInfo<OpsOrgUserInfo> {
	public static final OpsOrgUserInfo dao = new OpsOrgUserInfo();

	public OpsOrgUserInfo getUserInfoFromId(Object id){
		return findFirst(String.format("select * from %s where id = ?",TableName), id);
	}

	public List<OpsOrgUserInfo> getUserInfoFromOrgName(String orgName){
		return find(String.format("select * from %s where organize_name = ?",TableName), orgName);
	}

	public List<OpsOrgUserInfo> getUserInfoFromOrgId(String orgId){
		return find(String.format("select * from %s where organize_id = ?",TableName), orgId);
	}

	public OpsOrgUserInfo getUserInfoFromUserId(Object userId){
		return findFirst(String.format("select * from %s where user_id = ?",TableName), userId);
	}

	public List<OpsOrgUserInfo> getUserInfoFromUserName(Object userName){
		return find(String.format("select * from %s where user_name = ?",TableName), userName);
	}

	public void insertUserInfo(OpsOrgUserInfo userInfo){
		Record record = new Record();
		record.setColumns(userInfo);
		Db.save(TableName, record);
	}

	public void updateUserInfo(OpsOrgUserInfo userInfo){
		Record record = new Record();
		record.setColumns(userInfo);
		Db.update(TableName, record);
	}

	public boolean deleteUserInfo(Object id){
		try{
			deleteById(id);
			return true;
		}catch(Exception ex){
			return false;
		}
	}

	public Page<OpsOrgUserInfo> selectOrgUserInfo(String key, String type, int from){
		String exectSql = String.format(" from %s where 1 = 1 ",TableName);

		List<Object> params = new ArrayList<>();
		if(StringUtils.isNoneBlank(key)&&StringUtils.isNoneBlank(type)){
			switch(type){
				case "1":
					exectSql += " and user_id = ? ";
					params.add(StringUtil.parseInt(key, 0));
					 break;
				case "2":
					exectSql += " and organize_id = ? ";
					params.add(StringUtil.parseInt(key, 0));
					break;
				case "3": exectSql +=  " and organize_name like ? ";params.add("%"+key+"%"); break;
				case "4": exectSql += " and user_name = ? ";params.add(key); break;
				default: break;
			}
		}

		exectSql += " order by id desc ";

		return params.size() == 0 ? dao.paginate(from,20,"select * ",exectSql) : dao.paginate(from,20,"select * ",exectSql,params.toArray());
	}
}
