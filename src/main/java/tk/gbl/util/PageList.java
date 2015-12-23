package tk.gbl.util;

import java.util.ArrayList;

/**
 * Date: 2015/12/10
 * Time: 20:45
 *
 * @author Tian.Dong
 */
public class PageList<E> extends ArrayList<E>{

  int allCount;
  int pageSize;
  int pageCount;

  public int getAllCount() {
    return allCount;
  }

  public void setAllCount(int allCount) {
    this.allCount = allCount;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getPageCount() {
    return pageCount;
  }

  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }
}
