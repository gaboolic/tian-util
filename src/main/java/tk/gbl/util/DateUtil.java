package tk.gbl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 日期工具类
 * <p/>
 * Date: 2014/8/13
 * Time: 22:12
 *
 * @author mj.chow
 */
public class DateUtil {
  static Map<Integer, String> monthCnMap = new HashMap<Integer, String>();

  static {
    monthCnMap.put(0, "一月");
    monthCnMap.put(1, "二月");
    monthCnMap.put(2, "三月");
    monthCnMap.put(3, "四月");
    monthCnMap.put(4, "五月");
    monthCnMap.put(5, "六月");
    monthCnMap.put(6, "七月");
    monthCnMap.put(7, "八月");
    monthCnMap.put(8, "九月");
    monthCnMap.put(9, "十月");
    monthCnMap.put(10, "十一月");
    monthCnMap.put(11, "十二月");
  }

  public static String getCnMonth(String date) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date d = null;
    try {
      d = sdf.parse(date);
    } catch (ParseException e) {
      return null;
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(d);
    int month = cal.get(Calendar.MONTH);//获取月份
    return monthCnMap.get(month);
  }

  public static int getDay(String date) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date d = null;
    try {
      d = sdf.parse(date);
    } catch (ParseException e) {
      return 0;
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(d);
    int day = cal.get(Calendar.DAY_OF_MONTH);//获取月份
    return day;
  }

  public static String getDateStr(Date date) {
    if (date == null) {
      return "";
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String d = sdf.format(date);
    return d;
  }

  public static int countRemainingDays(Date today, Date iceDate) {
    long n1 = today.getTime();
    long n2 = iceDate.getTime();
    long diff = Math.abs(n1 - n2);
    int days = (int) (diff / (3600 * 1000 * 24));

    return days;
  }

}
