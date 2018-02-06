package com.peseer.ops.biz;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.peseer.ops.util.KeyValuePair;

public class EntFilter {
	private static final Logger logger = LoggerFactory.getLogger(EntFilter.class.getName());

	private static Hashtable<String,String> crawledEnts = new  Hashtable<>();

	private EntFilter(){

	}
	/**
	 * 把公司加入到已抓取队列中
	 * @param entInfo
	 */
	public static void addCrawledSets(Record entInfo){
		String name = entInfo.getStr("name");
		String uuid = entInfo.getStr("uuid");

		crawledEnts.put(standardEntName(name),uuid);

		String history_names = entInfo.getStr("history_names");
		if(StringUtils.isNotBlank(history_names)){
			String[] history_nameArray = StringUtils.split(history_names, ',');
			for (String hisName : history_nameArray) {
				crawledEnts.put(standardEntName(hisName),uuid);
			}
		}
	}

	/**
	 * 已下载是否列表中是否包含公司
	 * @param name
	 * @return
	 */
	public static boolean contains(String name){
		return crawledEnts.containsKey(standardEntName(name));
	}

	/**
	 * 比较两个公司名是否相等
	 * @param name1
	 * @param name2
	 * @return
	 */
	public static boolean compareEnt(String name1,String name2){
		return StringUtils.equalsIgnoreCase(standardEntName(name1), standardEntName(name2));
	}

	/**
	 * 标准化公司名称
	 * @param name
	 * @return
	 */
	private static String standardEntName(String name){
		return StringUtils.isBlank(name) ? StringUtils.EMPTY : name.replace("（","(").replace("）",")").trim();
	}

	/**
	 * 是否是需要抓取的公司
	 * @param name
	 * @return
	 */
	public static boolean isEnt(String name){
		if(StringUtils.isBlank(name))return false;

		return name.length()>4 ? true : false;
	}

	public static String getUUID(String name){
		if(StringUtils.isBlank(name))return StringUtils.EMPTY;

		name = standardEntName(name);

		if(crawledEnts.isEmpty()){
			init();
		}

		return crawledEnts.containsKey(name) ? crawledEnts.get(name) : StringUtils.EMPTY;
	}

	public static List<KeyValuePair<String, String>> queryEnt(String name,Integer limit){
		List<KeyValuePair<String, String>> list = new ArrayList<>();
		if(StringUtils.isNotBlank(name)){
			name = standardEntName(name);
			for (Entry<String, String> element : crawledEnts.entrySet()) {
				if(element.getKey().contains(name) || element.getValue().equals(name)){
					KeyValuePair<String, String> pair = new KeyValuePair<>(element.getKey(),element.getValue());
					list.add(pair);

					if(list.size()>= limit)break;
				}
			}
		}


		return list;
	}

	public static void init() {
		crawledEnts.clear();

		logger.info("开始初始化过滤器.....");
		List<Record> ents = Db.use("qxb").find(" select uuid,name,history_names from qxb_ent_info ");
		logger.info("历史公司数量："+ents.size());
		/*
		crawledEnts.put(standardEntName("易采（北京）网络科技有限公司"), "f26e3f6b-4d31-4ee0-84fc-773ced04954c");
		crawledEnts.put(standardEntName("投中信息网络科技有限公司"), "f26e3f6b-84fc-773ced04954c");
		crawledEnts.put(standardEntName("南京龙柏投资中心（有限合伙）"),"9b096fd7-cec7-4fc5-b675-c2a12cfc3447");
		*/
		for (Record qxbEntInfo : ents) {
			addCrawledSets(qxbEntInfo);
		}
		logger.info("过滤器初始化完成。");
	}
}
