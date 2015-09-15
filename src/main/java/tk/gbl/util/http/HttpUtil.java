package tk.gbl.util.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * Date: 2015/9/15
 * Time: 14:02
 *
 * @author Tian.Dong
 */
public class HttpUtil {
  public static String get(String url) throws IOException {
    HttpMethod method = new GetMethod(url);
    HttpClient client = new HttpClient();
    client.executeMethod(method);
    return method.getResponseBodyAsString();
  }

  public static String get(String url, Map<String, Object> map) throws IOException {
    String param = genParam(map);
    HttpMethod method = new GetMethod(url + "?" + param);
    HttpClient client = new HttpClient();
    client.executeMethod(method);
    return method.getResponseBodyAsString();
  }

  public static String get(String url, Object obj) throws IOException, IllegalAccessException {
    String param = genParam(obj);
    HttpMethod method = new GetMethod(url + "?" + param);
    HttpClient client = new HttpClient();
    client.executeMethod(method);
    return method.getResponseBodyAsString();
  }

  private static String genParam(Object obj) throws IllegalAccessException {
    if(obj == null) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    for(Field field:obj.getClass().getDeclaredFields()) {
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

  private static String genParam(Map<String, Object> map) {
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
