package tk.gbl.util.image;

import sun.reflect.annotation.ExceptionProxy;

/**
 * Date: 2014/10/21
 * Time: 16:46
 *
 * @author Tian.Dong
 */
public class LeftRightJudge {
  public static boolean judgeIsLeft(int[][] temp) {
    temp = Cut.cutForLeftJudge(temp);
    //Output.output(temp);
    int ww = 0;
    for (int h = 0; h < temp.length; h++) {
      for (int w = 0; w < temp[0].length; w++) {
        if (temp[h][w] == 1) {
          ww = w;
          break;
        }
      }
    }
    int mid = 0;
    try {
      mid = temp[0].length / 2;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ww < mid;
  }
}
