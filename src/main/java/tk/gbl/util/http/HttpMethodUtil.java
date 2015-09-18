package tk.gbl.util.http;

import org.apache.commons.httpclient.methods.*;

import java.io.UnsupportedEncodingException;

/**
 *
 */
public class HttpMethodUtil {

  public static GetMethod getGetMethod(String url) {
    return new GetMethod(url);
  }

  public static PostMethod getPostMethod(String url, String contentType, String charset,
      String body) throws UnsupportedEncodingException {
    PostMethod method = new PostMethod(url);
    method.setRequestEntity(new StringRequestEntity(body, contentType, charset));
    return method;
  }

  public static PostMethod getJsonPostMethod(String url,String body){
    PostMethod method = new PostMethod(url);
    try {
      method.setRequestEntity(new StringRequestEntity(body, "application/json", "utf-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return method;
  }
  public static PostMethod getFormPostMethod(String url,String body) {
    PostMethod method = new PostMethod(url);
    try {
      method.setRequestEntity(new StringRequestEntity(body, "application/x-www-form-urlencoded", "utf-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return method;
  }
}
