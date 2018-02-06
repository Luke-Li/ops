package com.peseer.ops.model.online;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.kdata.service.EventStatService;
import com.peseer.ops.model.OpsExitEventDetail;
import com.peseer.ops.model.OpsInvestEventDetail;
import com.peseer.ops.model.online.base.BaseDailyEvent75;

/**
 * 从75同步的每日资本事件，包含投资和退出
 */
@SuppressWarnings("serial")
public class DailyEvent75 extends BaseDailyEvent75<DailyEvent75> {
	public static final DailyEvent75 dao = new DailyEvent75();
	public static EventStatService statService = new EventStatService();

	public DailyEvent75() {
		super();
		// TODO Auto-generated constructor stub
	}



	public DailyEvent75(OpsInvestEventDetail event) {
		super();
		if(null != event){
			this.setAmount(event.getAmount());
			this.setEntCnName(event.getEntCnName());
			this.setEventClass("invest");
			this.setEventDesc(event.getEventDesc());
			this.setEventId(event.getEventId());
			this.setEventTitle(event.getEventTitle());
			this.setEventType(event.getInvestType());
			this.setFundName(event.getFundName());
			this.setHappenDate(event.getHappenDate());
			this.setOrgCnName(event.getOrgCnName());
			this.setUserName(event.getUserName());
			this.setStockRight(event.getStockRight());
		}
	}

	public DailyEvent75(OpsExitEventDetail event) {
		super();
		if(null != event){
			this.setAmount(event.getAmount());
			this.setEntCnName(event.getEntCnName());
			this.setEventClass("exit");
			this.setEventDesc(event.getEventDesc());
			this.setEventId(event.getEventId());
			this.setEventTitle(event.getEventTitle());
			this.setEventType(event.getExitType());
			this.setFundName(event.getFundName());
			this.setHappenDate(event.getHappenDate());
			this.setOrgCnName(event.getOrgCnName());
			this.setUserName(event.getUserName());
			this.setStockRight(event.getStockRight());
			this.setBringRate(event.getBringRate());
			this.setFirstInvestDate(event.getFirstInvestDate());
			this.setIrr(event.getIrr());
			this.setTotalInvest(event.getTotalInvest());
		}
	}

	/**
	 * 更新66对应的投资事件
	 * @param event
	 */
	public void updateEvent(OpsInvestEventDetail event){
		if(event== null || event.getEventId() <=0){
			return;
		}
		String sql = String.format("select * from %s where event_id=? and event_class=?",TableName);

		DailyEvent75 tmpEvent = DailyEvent75.dao.findFirst(sql,event.getEventId(),"invest");
		DailyEvent75 updateEvent = new DailyEvent75(event);
		if(tmpEvent != null){
			 updateEvent.setId(tmpEvent.getId());
			 updateEvent.update();
		}else{
			updateEvent.save();
			statService.countAll();
		}
	}

	/**
	 * 更新66对应的退出事件
	 * @param event
	 */
	public void updateEvent(OpsExitEventDetail event){
		if(event== null || event.getEventId() <=0){
			return;
		}
		String sql = String.format("select * from %s where event_id = ? and event_class = ?",TableName);

		DailyEvent75 tmpEvent = DailyEvent75.dao.findFirst(sql, event.getEventId(),"exit");
		DailyEvent75 updateEvent = new DailyEvent75(event);
		if(tmpEvent != null){
			 updateEvent.setId(tmpEvent.getId());
			 updateEvent.update();
		}else{
			updateEvent.save();
			statService.countAll();
		}
	}

	/**
	 * 新增投资事件到66
	 * @param event
	 */
	public void saveEvent(OpsInvestEventDetail event){
		if(event == null){
			return;
		}

		DailyEvent75 tmpEvent = new DailyEvent75(event);
		tmpEvent.save();
		statService.countAll();
	}

	/**
	 * 新增退出事件到66
	 * @param event
	 */
	public void saveEvent(OpsExitEventDetail event){
		if(event == null){
			return;
		}

		DailyEvent75 tmpEvent = new DailyEvent75(event);
		tmpEvent.save();
		statService.countAll();
	}

	/**
	 * 删除66对应的投资事件
	 * @param event
	 */
	public void deleteEvent(OpsInvestEventDetail event){
		if(event== null || event.getEventId() <=0){
			return;
		}
		String sql = String.format("delete from %s where event_id = ? and event_class = ?",TableName);
		List<Object> para = new ArrayList<>();
		para.add(event.getEventId());
		para.add("invest");

		Db.update(sql,para);
		statService.CountAgain();
	}

	/**
	 * 删除66对应的退出事件
	 * @param event
	 */
	public void deleteEvent(OpsExitEventDetail event){
		if(event== null || event.getEventId() <=0){
			return;
		}
		String sql = String.format("delete from %s where event_id = ? and event_class = ?",TableName);
		List<Object> para = new ArrayList<>();
		para.add(event.getEventId());
		para.add("exit");

		Db.update(sql,para);
		statService.CountAgain();
	}

	/**
	 * 删除66对应的事件
	 * @param eventId
	 * @param eventType    invest/exit
	 */
	public void deleteEvent(int eventId, String eventType){
		String sql = String.format("delete from %s where event_id = ? and event_class = ?",TableName);
		List<Object> para = new ArrayList<>();
		para.add(eventId);
		para.add(eventType);

		Db.use("peseerOnline").update(sql,para.toArray());
		statService.CountAgain();
	}

}
