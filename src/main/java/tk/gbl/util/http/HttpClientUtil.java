package tk.gbl.util.http;

import org.apache.commons.httpclient.HttpClient;

/**
 * Created by tian on 2015/9/18.
 */
public class HttpClientUtil {
  public static HttpClient getHttpClient(){
    HttpClient httpClient = new HttpClient();
    int time = 5000;
    httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(time);
    httpClient.getHttpConnectionManager().getParams().setSoTimeout(time);
    return httpClient;
  }
}
