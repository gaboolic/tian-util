package tk.gbl.util;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Date: 2015/9/17
 * Time: 15:05
 *
 * @author Tian.Dong
 */
public class ParamUtil {
  public static String genParam(Object obj) throws IllegalAccessException {
    if (obj == null) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    for (Field field : obj.getClass().getDeclaredFields()) {
      sb.append(field.getName());
      sb.append("=");
      Object value = field.get(obj);
      if (value != null) {
        sb.append(value);
      }
      sb.append("&");
    }
    return sb.toString();
  }

  public static String genParam(Map<String, Object> map) {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry entry : map.entrySet()) {
      sb.append(entry.getKey());
      sb.append("=");
      Object value = entry.getValue();
      if (value != null) {
        sb.append(value);
      }
      sb.append("&");
    }
    return sb.toString();
  }
}
