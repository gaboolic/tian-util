package tk.gbl.util.http;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
    String content = HttpUtil.get("http://tieba.baidu.com/f?kw=java&ie=utf-8&pn=50");
    Document htmlDoc = Jsoup.parse(content);
    Element ul = htmlDoc.getElementById("thread_list");

//    System.out.println(ul);
    ul.children().forEach(
        o->System.out.println(o)
    );
  }
}
