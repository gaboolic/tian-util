package tk.gbl.util.doc;

import java.util.List;

/**
 * Date: 2015/10/12
 * Time: 17:44
 *
 * @author Tian.Dong
 */
public class DocItem {
  String docName;
  String docPrefix;
  List<ApiItem> apiItemList;

  public List<ApiItem> getApiItemList() {
    return apiItemList;
  }

  public void setApiItemList(List<ApiItem> apiItemList) {
    this.apiItemList = apiItemList;
  }

  public String getDocName() {
    return docName;
  }

  public void setDocName(String docName) {
    this.docName = docName;
  }

  public String getDocPrefix() {
    return docPrefix;
  }

  public void setDocPrefix(String docPrefix) {
    this.docPrefix = docPrefix;
  }
}
