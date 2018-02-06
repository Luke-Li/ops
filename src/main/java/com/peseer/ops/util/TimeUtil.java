package com.peseer.ops.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
	private static SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式

	public static String getCurrentTime() {
		String time = sdf.format(Calendar.getInstance().getTime());
		return time;
	}

	/**
	 *
	 * @param format = "yyyy-MM-dd" or "yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String getCurrentTime(String format) {
		SimpleDateFormat sdFormat = new SimpleDateFormat(format);
		String time = sdFormat.format(Calendar.getInstance().getTime());
		return time;
	}

	public static String getDaysBefore(int day) {
		Date currentDate = Calendar.getInstance().getTime();
		String time = sdf.format(getDateBefore(currentDate, day));
		return time;
	}

	/**
	 * 得到几天前的时间
	 *
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}

	/**
	 * 得到几天后的时间
	 *
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	public static Date getCurrentDate(){
		return new Date();
	}

	public static Date getWeeksBefore(int weeks){
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		now.add(Calendar.DATE, weeks*7*-1);
//		now.set(Calendar.DATE, now.get(Calendar.WEEK_OF_MONTH) - weeks);
		return now.getTime();
//		return DateFormat.format(now);
	}

	public static Date getMonthsBefore(int months){
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		now.add(Calendar.MONTH, -1*months);
		return now.getTime();
//		return DateFormat.format(now);
	}

	/**
	 * get a date from "YYYY-MM-DD"
	 * @param dd
	 * @return
	 * @throws ParseException
	 */
	public static Date getSomeDay(String dd) throws ParseException{
		return new SimpleDateFormat("yyyy-MM-dd").parse(dd);
	}

	/**
	 * get a Time from "YYYY-MM-DD"
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static Date getSomeTime(String time){
		try {
			return sdf.parse(time);
		} catch (ParseException e) {
			return null;
		}
	}

}
