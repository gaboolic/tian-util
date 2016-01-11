package tk.gbl.util.image;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Date: 2016/1/5
 * Time: 10:43
 *
 * @author Tian.Dong
 */
public class ImageTest {
  //@Test
  public void test() throws IOException {
    int[][] img = Binary.deal(new File("C:\\Users\\tian\\Desktop\\1-首页.png"));
    ArrayToImage.createImage(img,new File("C:\\Users\\tian\\Desktop\\1-首页222.png"));
  }
}
