package com.peseer.ops.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.plugin.activerecord.Page;
import com.peseer.ops.model.base.BaseOpsStartupsInfo;

/**
 * 创业项目信息
 */
@SuppressWarnings("serial")
public class OpsStartupsInfo extends BaseOpsStartupsInfo<OpsStartupsInfo> {
	public static final OpsStartupsInfo dao = new OpsStartupsInfo();

	public Page<OpsStartupsInfo> selectStartupsInfo(String key, String type, int from){
		String exectSql = String.format(" from %s where 1 = 1 ",TableName);

		List<Object> params = new ArrayList<>();
		if(StringUtils.isNoneBlank(key)&&StringUtils.isNoneBlank(type)){
			switch(type){
				case "1":
					exectSql += " and ent_name like  ? ";
					params.add("%"+key+"%");
					 break;
				case "2":
					exectSql += " and domain like ? ";
					params.add("%"+key+"%");
					break;
				default: break;
			}
		}

		exectSql += " order by id desc ";

		return params.size() == 0 ? dao.paginate(from,20,"select * ",exectSql) : dao.paginate(from,20,"select * ",exectSql,params.toArray());
	}

	public boolean deleteStartupsInfoById(int id){
		try{
			return OpsStartupsInfo.dao.deleteById(id);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
