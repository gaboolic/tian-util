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

}
