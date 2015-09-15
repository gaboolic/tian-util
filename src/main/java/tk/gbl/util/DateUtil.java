package tk.gbl.util;

import java.util.Date;


/**
 * 日期工具类
 * <p/>
 * Date: 2014/8/13
 * Time: 22:12
 *
 * @author mj.chow
 */
public class DateUtil {

  public static int countRemainingDays(Date today, Date iceDate) {
    long n1 = today.getTime();
    long n2 = iceDate.getTime();
    long diff = Math.abs(n1 - n2);
    int days = (int) (diff / (3600 * 1000 * 24));

    return days;
  }
  
}
