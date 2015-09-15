package tk.gbl.util.http;

import org.junit.Test;

import java.io.IOException;

/**
 * Date: 2015/9/15
 * Time: 14:22
 *
 * @author Tian.Dong
 */
public class HttpUtilTest {
  @Test
  public void test() throws IOException {
    System.out.println(HttpUtil.get("http://www.baidu.com"));
  }
}
