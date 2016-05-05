package tk.gbl.util.doc;

import java.util.List;

/**
 * Date: 2016/5/5
 * Time: 11:16
 *
 * @author Tian.Dong
 */
public class ApiItem {
  private String url;
  private String name;

  private List<ParamItem> paramList;

  private List<ParamItem> retList;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public List<ParamItem> getParamList() {
    return paramList;
  }

  public void setParamList(List<ParamItem> paramList) {
    this.paramList = paramList;
  }

  public List<ParamItem> getRetList() {
    return retList;
  }

  public void setRetList(List<ParamItem> retList) {
    this.retList = retList;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
