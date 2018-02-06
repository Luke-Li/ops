package com.peseer.ops.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.peseer.ops.model.base.BaseOpsCategoryBiz;

/**
 * 投资领域、新闻类型、报告类型的统一基础分类（除企业分类）
 */
@SuppressWarnings("serial")
public class OpsCategoryBiz extends BaseOpsCategoryBiz<OpsCategoryBiz> {
	public static final OpsCategoryBiz dao = new OpsCategoryBiz();

	private static Map<Object, Object> catMap = new HashMap<>();

	private void initMap(){
		List<OpsCategoryBiz> list= OpsCategoryBiz.dao.find("select * from " + OpsCategoryBiz.TableName);

		if(list != null){
			catMap.clear();
			for(OpsCategoryBiz tmp:list){
				catMap.put(tmp.getBizCatId(), tmp.getBizCatName());
				catMap.put(tmp.getBizCatName(), tmp.getBizCatId());
			}
		}
	}

	public Object getValue(Object key){
		if(catMap.isEmpty()){
			initMap();
		}

		return catMap.get(key);
	}
}
