package tk.gbl.util.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * Created by tian on 2015/9/18.
 */
public class HttpClientUtil {
  public static HttpClient getHttpClient(){
    HttpClient httpClient = new HttpClient();
    int time = 5000;
    httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(time);
    httpClient.getHttpConnectionManager().getParams().setSoTimeout(time);
    httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
    httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
    return httpClient;
  }
}
