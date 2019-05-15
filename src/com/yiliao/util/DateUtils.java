package com.yiliao.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.MissingResourceException;

import org.apache.commons.lang.StringUtils;

/**
 * 日期Util类
 * 
 * @author zhoushuhua
 */
public class DateUtils {

	public static final String defaultDatePattern = "yyyy-MM-dd";

	public static final String FullDatePattern = "yyyy-MM-dd HH:mm:ss";

	public static final String HFDatePattern = "yyyy-MM-dd HH:mm";

	public static final String DATE_FORMAT_PATTERN = "MM/dd/yyyy";

	static {
		// 尝试试从messages_zh_Cn.properties中获取defaultDatePattner.
		try {
			// Locale locale = LocaleContextHolder.getLocale();
			// defaultDatePattern =
			// ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE_KEY,
			// locale).getString("date.default_format");
		} catch (MissingResourceException mse) {
			// do nothing
		}
	}

	/**
	 * 获得默认的 date pattern
	 * 
	 * @return String
	 */
	public static String getDatePattern() {
		return defaultDatePattern;
	}

	/**
	 * 返回预设Format的当前日期字符串
	 * 
	 * @return String
	 */
	public static String getToday() {
		return format(now());
	}

	/**
	 * 返回当前时间
	 * 
	 * @return Date实例
	 */
	public static Date now() {
		return nowCal().getTime();
	}

	/**
	 * 当前时间
	 * 
	 * @return Calendar实例
	 */
	public static Calendar nowCal() {
		return Calendar.getInstance();
	}

	/**
	 * Date型转化到Calendar型
	 * 
	 * @param date
	 * @return Calendar
	 */
	public static Calendar date2Cal(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}

	/**
	 * 当前时间的下一天
	 * 
	 * @return Calendar
	 */
	public static Calendar nextDay() {
		return nextDay(nowCal());
	}

	/**
	 * 当前时间的下一月
	 * 
	 * @return Calendar
	 */
	public static Calendar nextMonth() {
		return nextMonth(nowCal());
	}

	/**
	 * 当前时间的下一年
	 * 
	 * @return Calendar
	 */
	public static Calendar nextYear() {
		return nextMonth(nowCal());
	}

	/**
	 * 下一天
	 * 
	 * @param cal
	 * @return Calendar
	 */
	public static Calendar nextDay(Calendar cal) {
		if (cal == null) {
			return null;
		}
		return afterDays(cal, 1);
	}

	/**
	 * 下一月
	 * 
	 * @param cal
	 * @return Calendar
	 */
	public static Calendar nextMonth(Calendar cal) {
		if (cal == null) {
			return null;
		}
		return afterMonths(cal, 1);
	}

	/**
	 * 下一年
	 * 
	 * @param cal
	 * @return Calendar
	 */
	public static Calendar nextYear(Calendar cal) {
		if (cal == null) {
			return null;
		}
		return afterYesrs(cal, 1);
	}

	/**
	 * 后n天
	 * 
	 * @param cal
	 * @param n
	 * @return Calendar
	 */
	public static Calendar afterDays(Calendar cal, int n) {
		if (cal == null) {
			return null;
		}
		Calendar c = (Calendar) cal.clone();
		c.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + n);
		return c;
	}

	/**
	 * 下N秒
	 * 
	 * @param cal
	 * @param n
	 * @return
	 */
	public static Calendar afterSecond(Calendar cal, int n) {
		if (cal == null) {
			return null;
		}
		Calendar c = (Calendar) cal.clone();
		c.set(Calendar.SECOND, cal.get(Calendar.SECOND) + n);
		return c;
	}

	/**
	 * 后n月
	 * 
	 * @param cal
	 * @param n
	 * @return Calendar
	 */
	public static Calendar afterMonths(Calendar cal, int n) {
		if (cal == null) {
			return null;
		}
		Calendar c = (Calendar) cal.clone();
		c.set(Calendar.MONTH, cal.get(Calendar.MONTH) + n);
		return c;
	}

	/**
	 * 后n年
	 * 
	 * @param cal
	 * @param n
	 * @return Calendar
	 */
	public static Calendar afterYesrs(Calendar cal, int n) {
		if (cal == null) {
			return null;
		}
		Calendar c = (Calendar) cal.clone();
		c.set(Calendar.YEAR, cal.get(Calendar.YEAR) + n);
		return c;
	}

	/**
	 * 使用预设Format格式化Date成字符串
	 * 
	 * @return String
	 */
	public static String format(Date date) {
		return date == null ? "" : format(date, getDatePattern());
	}

	/**
	 * 使用参数Format格式化Date成字符串
	 * 
	 * @return String
	 */
	public static String format(Date date, String pattern) {
		return date == null ? "" : new SimpleDateFormat(pattern).format(date);
	}

	public static String format(long time, String pattern) {
		return 0 == time ? "" : new SimpleDateFormat(pattern).format(new Date(
				time));
	}

	/**
	 * 试用参数Format格式化Calendar成字符串
	 * 
	 * @param cal
	 * @param pattern
	 * @return String
	 */
	public static String format(Calendar cal, String pattern) {
		return cal == null ? "" : new SimpleDateFormat(pattern).format(cal
				.getTime());
	}

	/**
	 * 使用预设格式将字符串转为Date
	 * 
	 * @return Date
	 */
	public static Date parse(String strDate) throws ParseException {
		return StringUtils.isBlank(strDate) ? null : parse(strDate,
				getDatePattern());
	}

	/**
	 * 使用参数Format将字符串转为Date
	 * 
	 * @return Date
	 */
	public static Date parse(String strDate, String pattern)
			throws ParseException {
		return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(
				pattern).parse(strDate);
	}

	/**
	 * 在日期上增加数个整月
	 * 
	 * @return Date
	 */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

	/**
	 * get String value(MM/dd/yyyy) of time
	 * 
	 * @param d
	 * @return String
	 */
	public static String dateToString(Date d) {
		if (d == null) {
			return null;
		}
		SimpleDateFormat lenientDateFormat = new SimpleDateFormat(
				defaultDatePattern);
		return lenientDateFormat.format(d);
	}

	// public Date getDataBaseNowDate(){
	// String sql="select to_char(sysdate,'yyyy-MM-dd HH24:mm:ss') from dual";
	//
	// Query query = getSession().createSQLQuery(sql);
	// //开启缓存
	// query.setCacheable(true);
	// try {
	// return parse (query.list().get(0).toString(),"yyyy-MM-dd HH:mm:ss");
	// } catch (HibernateException e) {
	// e.printStackTrace();
	// return null;
	// } catch (ParseException e) {
	// e.printStackTrace();
	// return null;
	// }
	// }

	/**
	 * Date 转 String yyyyMMdd 转换自定义日期格式 默认为：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 *            字符串日期
	 * @param pattern
	 *            转换格式 (可传 null 或 "")
	 * @return 转换后结果（String）
	 * @author eswyao@126.com
	 */
	public String dateToString(String strDate, String pattern) {

		try {
			Date fdate = parse(strDate, "yyyyMMdd");
			if ("".equals(pattern) || null == pattern) {
				pattern = FullDatePattern;
			}

			if (null != strDate || "".equals(strDate)) {
				SimpleDateFormat sformat = new SimpleDateFormat(pattern);
				String date = sformat.format(fdate);
				return date;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * 获得该月第一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getFirstDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();

		if (year > 0) {
			cal.set(Calendar.YEAR, year);
		}
		if (month > 0) {
			cal.set(Calendar.MONTH, month - 1);
		}
		// 获取某月最小天数
		int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		// 设置日历中月份的最小天数
		cal.set(Calendar.DAY_OF_MONTH, firstDay);
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String firstDayOfMonth = sdf.format(cal.getTime());
		return firstDayOfMonth;
	}

	/**
	 * 获得该月最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		if (year > 0) {
			cal.set(Calendar.YEAR, year);
		}
		if (month > 0) {
			cal.set(Calendar.MONTH, month - 1);
		}
		// 获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		String lastDayOfMonth = sdf.format(cal.getTime());
		return lastDayOfMonth;
	}

	/**
	 * 获取过去第几天的日期
	 * 
	 * @param past
	 * @return
	 */
	public static String getPastDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)
				- past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}

	/**
	 * 获取过去任意天内的日期数组
	 * 
	 * @param intervals
	 *            intervals天内
	 * @return 日期数组
	 */
	public static ArrayList<String> arbitrarilyDays(int intervals) {
		ArrayList<String> pastDaysList = new ArrayList<String>();
		for (int i = 0; i < intervals; i++) {
			pastDaysList.add(getPastDate(i));
		}
		return pastDaysList;
	}

	
	/**
	 * 获取指定日期的前一天或者后一天
	 * @param past
	 * @param cal
	 * @return
	 */
	public static String getPastDate(int past, Date date) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day=cal.get(Calendar.DATE); 
		cal.set(Calendar.DATE,day-past); 
		Date today = cal.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}
	
	
	/**
	 * 获取指定日期过去任意天内的日期数组
	 * @param intervals    intervals天内
	 * @param Date  日期
	 * @return 日期数组
	 */
	public static List<String> arbitrarilyDays(int intervals,Date date) {
		List<String> pastDaysList = new ArrayList<String>();
		for (int i = 0; i < intervals; i++) {
			pastDaysList.add(getPastDate(i,date));
		}
		return pastDaysList;
	}

	/**
	 * date2比date1多的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differentDays(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1 = cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if (year1 != year2) // 同一年
		{
			int timeDistance = 0;
			for (int i = year1; i < year2; i++) {
				if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) // 闰年
				{
					timeDistance += 366;
				} else // 不是闰年
				{
					timeDistance += 365;
				}
			}

			return timeDistance + (day2 - day1);
		} else // 不同年
		{
			System.out.println("判断day2 - day1 : " + (day2 - day1));
			return day2 - day1;
		}
	}
	
	public static String getConvert(int time) {
		try {

			if(time < 60) {
				return time+"秒";
			}else if((time/60)<60) {
			    return time/60+"分"+time%60+"秒";
			}else {
				return time/60/60+"小时"+(time%3600)/60+"分"+time%60%60+"秒";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		
		System.out.println(getConvert(1184));
		
	}
	 
}