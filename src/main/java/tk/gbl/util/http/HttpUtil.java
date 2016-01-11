package tk.gbl.util.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import tk.gbl.util.ParamUtil;

import java.io.IOException;
import java.util.Map;

/**
 * Date: 2015/9/15
 * Time: 14:02
 *
 * @author Tian.Dong
 */
public class HttpUtil {

  public static String get(GetMethod method) throws IOException {
    HttpClient client = HttpClientUtil.getHttpClient();
    client.executeMethod(method);
    return method.getResponseBodyAsString();
  }

  public static String get(String url, String param, String cookie) throws IOException {
    if (param != null && !param.equals("")) {
      url = url + "?" + param;
    }
    GetMethod method = new GetMethod(url);
    method.setRequestHeader("Cookie", cookie);
    return get(method);
  }

  public static String get(String url, String param) throws IOException {
    return get(url, param, null);
  }

  public static String get(String url, Map<String, Object> map) throws IOException {
    String param = ParamUtil.genParam(map);
    return get(url, param);
  }

  public static String get(String url, Object obj) throws IOException, IllegalAccessException {
    String param = ParamUtil.genHttpParam(obj);
    return get(url, param);
  }

  public static String get(String url) throws IOException {
    return get(url, null, null);
  }

  public static String getWithCookie(String url, String param, String cookie) throws IOException {
    return get(url, param, cookie);
  }

  public static String getWithCookie(String url, String cookie) throws IOException {
    return getWithCookie(url, null, cookie);
  }


  public static String post(PostMethod method) throws IOException {
    HttpClient client = HttpClientUtil.getHttpClient();
    client.executeMethod(method);
    return method.getResponseBodyAsString();
  }

  public static String post(String url, String body, String contentType, String charset, String cookie) throws IOException {
    if (cookie != null) {
      cookie = cookie.trim();
    }
    PostMethod method = new PostMethod(url);
    method.setRequestHeader("Cookie", cookie);
    method.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36");
    //method.setRequestHeader("X-Requested-With","ShockwaveFlash/18.0.0.232");
    method.setRequestEntity(new StringRequestEntity(body, contentType, charset));
    return post(method);
  }

  public static String post(String url, String body, String contentType, String charset) throws IOException {
    return post(url, body, contentType, charset, null);
  }

  public static String post(String url, String body, String contentType) throws IOException {
    return post(url, body, contentType, "utf-8");
  }

  public static String post(String url, String body) throws IOException {
    return post(url, body, "application/x-www-form-urlencoded", "utf-8");
  }

  public static String post(String url, Map<String, Object> map) throws IOException {
    String param = ParamUtil.genParam(map);
    return post(url, param);
  }

  public static String postWithCookie(String url, String body, String cookie) throws IOException {
    return post(url, body, "application/x-www-form-urlencoded", "utf-8", cookie);
  }

  public static String postJson(String url, String body) throws IOException {
    return post(url, body, "application/json", "utf-8");
  }
}
