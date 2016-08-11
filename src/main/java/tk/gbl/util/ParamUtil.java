package tk.gbl.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Date: 2015/9/17
 * Time: 15:05
 *
 * @author Tian.Dong
 */
public class ParamUtil {
  public static String genHttpParam(Object obj) throws IllegalAccessException, UnsupportedEncodingException {
    if (obj == null) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    for (Field field : obj.getClass().getDeclaredFields()) {
      field.setAccessible(true);
      sb.append(field.getName());
      sb.append("=");
      Object value = field.get(obj);
      if (value != null) {
        sb.append(URLEncoder.encode(value.toString(), "utf-8"));
      }
      sb.append("&");
    }
    sb.deleteCharAt(sb.length() - 1);
    return sb.toString();
  }

  public static String genParam(Map<String, Object> map) throws UnsupportedEncodingException {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry entry : map.entrySet()) {
      sb.append(entry.getKey());
      sb.append("=");
      Object value = entry.getValue();
      if (value != null) {
        sb.append(URLEncoder.encode(value.toString(), "UTF-8"));
      }
      sb.append("&");
    }
    sb.deleteCharAt(sb.length() - 1);
    return sb.toString();
  }
}
