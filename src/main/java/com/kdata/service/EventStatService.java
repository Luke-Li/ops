package com.kdata.service;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.peseer.ops.model.online.StatEventRoundOne;
import com.peseer.ops.model.online.StatEventRoundTwo;
import com.peseer.ops.util.StringUtil;
import com.peseer.ops.util.TimeUtil;

public class EventStatService {

	/**
	 * 清空统计表
	 */
	public void cleanStatTable(){
		String date = TimeUtil.getCurrentTime("yyyy-MM-dd");
//		String date = "2017-04-11";
		//records 为空，考虑添加记录后被删除，情况统计表
		Db.use("peseerOnline").update("delete from stat_event_round_two where count_date =?",date);
		Db.use("peseerOnline").update("delete from stat_event_round_one where count_date =?",date);
	}

	public void countAll(){
		countTwoFromOnline();
		countOneFromOnline();
	}

	public void CountAgain(){
		cleanStatTable();
		countAll();
	}

	public void countOneFromOnline(){

		String date = TimeUtil.getCurrentTime("yyyy-MM-dd");
//		String date = "2017-04-11";
		String investSql = "select b.father_id,  count(father_id) as count "
				+ "from daily_event_75 a "
				+ "left join event_round_two b "
				+ "on a.event_type=b.round_name  "
				+ "where a.update_time > ? "
				+ "group by b.father_id";

		List<Record> records = Db.use("peseerOnline").find(investSql,date);

		String statSql = String.format("select * from %s where "
				+ " count_date=?", StatEventRoundOne.TableName);

		StatEventRoundOne stat = StatEventRoundOne.dao.findFirst(statSql, date);
		if(stat == null){
			stat= new StatEventRoundOne();
		}


		if(records != null && !records.isEmpty()){

			for(Record record:records){
				if(record.getInt("father_id") != null){
					switch(record.getInt("father_id")){
					case 1: stat.setExitOne(StringUtil.parseInt(String.valueOf(record.getLong("count")),0)); break;
					case 2: stat.setExitTwo(StringUtil.parseInt(String.valueOf(record.getLong("count")),0)); break;
					case 3: stat.setInvestEarly(StringUtil.parseInt(String.valueOf(record.getLong("count")),0)); break;
					case 4: stat.setInvestMiddle(StringUtil.parseInt(String.valueOf(record.getLong("count")),0)); break;
					case 5: stat.setInvestLate(StringUtil.parseInt(String.valueOf(record.getLong("count")),0)); break;
					case 6: stat.setInvestOther(StringUtil.parseInt(String.valueOf(record.getLong("count")),0)); break;
					default: break;
					}
				}
			}

		}

		stat.setCountDate(date);
		if(stat.getId() != null){
			stat.setUpdateTime(new Date());
			stat.update();
		}else{
			stat.save();
		}
	}

	public void countTwoFromOnline(){
		String date = TimeUtil.getCurrentTime("yyyy-MM-dd");
//		String date = "2017-04-11";
		String investSql = "select b.id, count(a.event_type) as count "
				+ "from daily_event_75 a "
				+ "left join event_round_two b "
				+ "on a.event_type=b.round_name  "
				+ "where a.update_time > ? "
				+ "group by event_type";

		List<Record> records = Db.use("peseerOnline").find(investSql,date);

		String statSql = String.format("select * from %s where "
				+ "type_id=? and count_date=?", StatEventRoundTwo.TableName);

		if(records != null && !records.isEmpty()){
			for(Record record:records){
				if(record.getInt("id") != null){
					StatEventRoundTwo stat = StatEventRoundTwo.dao.findFirst(statSql,record.getInt("id"),date);
					if(stat == null){
						stat= new StatEventRoundTwo();
					}
					stat.setTypeId(record.getInt("id"));
					stat.setCount(StringUtil.parseInt(String.valueOf(record.getLong("count")),0));
					stat.setCountDate(date);
					if(stat.getId() != null){
						stat.setUpdateTime(new Date());
						stat.update();
					}else{
						stat.save();
					}
				}
			}
		}

	}
}
