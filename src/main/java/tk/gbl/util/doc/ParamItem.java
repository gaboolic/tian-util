package tk.gbl.util.doc;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Date: 2015/10/12
 * Time: 17:45
 *
 * @author Tian.Dong
 */
public class ParamItem {
  private boolean basic;
  private String type;
  private String param;
  private String desc;
  private Class<?> realType;
  private List<ParamItem> subParamItemList;

  public String getParam() {
    return param;
  }

  public void setParam(String param) {
    this.param = param;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public boolean isBasic() {
    return basic;
  }

  public void setBasic(boolean basic) {
    this.basic = basic;
  }

  public void setRealType(Class<?> realType) {
    this.realType = realType;
    if (realType.equals(String.class)
        || realType.equals(Date.class)
        || realType.equals(Integer.class)
        || realType.equals(int.class)
        || realType.equals(Long.class)
        || realType.equals(long.class)
        || realType.equals(Double.class)
        || realType.equals(double.class)
        || realType.equals(BigDecimal.class)
        || realType.equals(Timestamp.class)
        ) {
      this.basic = true;
    }
  }

  public Class<?> getRealType() {
    return realType;
  }

  public List<ParamItem> getSubParamItemList() {
    return subParamItemList;
  }

  public void setSubParamItemList(List<ParamItem> subParamItemList) {
    this.subParamItemList = subParamItemList;
  }
}
