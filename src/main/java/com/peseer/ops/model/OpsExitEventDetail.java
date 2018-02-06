package com.peseer.ops.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.peseer.ops.model.base.BaseOpsExitEventDetail;
import com.peseer.ops.util.StringUtil;

/**
 * 退出事件明细
 */
@SuppressWarnings("serial")
public class OpsExitEventDetail extends BaseOpsExitEventDetail<OpsExitEventDetail> {
	public static final OpsExitEventDetail dao = new OpsExitEventDetail();

	public Page<OpsExitEventDetail> selectExitEvents(String key, String type, int from){
		String exectSql = String.format(" from %s where 1 = 1 ",TableName);

		List<Object> params = new ArrayList<>();
		if(StringUtils.isNoneBlank(key)&&StringUtils.isNoneBlank(type)){
			switch(type){
				case "1":
					exectSql = exectSql + " and event_id = ?";
					params.add(StringUtil.parseInt(key, -1));
					break;
				case "2":
					exectSql = exectSql + " and ent_uuid = ?";
					params.add(key);
					break;
				case "3":
					exectSql = exectSql + " and ent_cn_name like ?";
					params.add("%"+key+"%");
					break;
				case "4":
					exectSql = exectSql + " and fund_name like ?";
					params.add("%"+key+"%");
					break;
				case "5":
					exectSql = exectSql + " and user_name like ?";
					params.add("%"+key+"%");
					break;
				default: break;
			}
		}

//		exectSql += StringUtils.isBlank(key)?StringUtils.EMPTY: (" and fund_name like ? ");

		exectSql += " order by event_id desc ";

		return dao.paginate(from,20,"select * ",exectSql,params.toArray());
	}

	public OpsExitEventDetail getExitEventFromId(Object event_id){
		return findFirst(String.format("select * from %s where event_id=?", TableName), event_id);
//		return findById(event_id);
	}

	public void insertEvent(OpsExitEventDetail event){
		Record record = new Record();
		record.setColumns(event);
		Db.save(TableName, record);
	}

	public void updateEvent(OpsExitEventDetail event){
		Record record = new Record();
		record.setColumns(event);
		Db.update(TableName, record);
	}

	public boolean deleteEvent(Object id){
//		Db.update(String.format("delete from %s where event_id = ?",TableName),id);
		try{
			deleteById(id);
			return true;
		}catch(Exception ex){
			return false;
		}
	}

	public OpsExitEventDetail getLastRecordFromName(String eventName){
		return findFirst(String.format("select * from %s where event_title=? order by update_time desc", TableName), eventName);
	}
}
