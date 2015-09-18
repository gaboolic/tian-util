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

  public static String get(String url) throws IOException {
    HttpMethod method = new GetMethod(url);
    HttpClient client = HttpClientUtil.getHttpClient();
    client.executeMethod(method);
    return method.getResponseBodyAsString();
  }

  public static String get(String url, String param) throws IOException {
    return get(url + "?" + param);
  }

  public static String get(String url, Map<String, Object> map) throws IOException {
    String param = ParamUtil.genParam(map);
    return get(url, param);
  }

  public static String get(String url, Object obj) throws IOException, IllegalAccessException {
    String param = ParamUtil.genHttpParam(obj);
    return get(url, param);
  }

  public static String post(PostMethod method) throws IOException {
    HttpClient client = HttpClientUtil.getHttpClient();
    client.executeMethod(method);
    return method.getResponseBodyAsString();
  }

  public static String post(String url, String body, String contentType, String charset, String cookie) throws IOException {
    PostMethod method = new PostMethod(url);
    method.setRequestHeader("Cookie", cookie);
    method.setRequestEntity(new StringRequestEntity(body, contentType, charset));

    HttpClient client = HttpClientUtil.getHttpClient();
    client.executeMethod(method);
    return method.getResponseBodyAsString();
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

  public static String postWithCookie(String url, String body, String cookie) throws IOException {
    return post(url, body, "application/x-www-form-urlencoded", "utf-8", cookie);
  }

  public static String postJson(String url, String body) throws IOException {
    return post(url, body, "application/json", "utf-8");
  }
}
