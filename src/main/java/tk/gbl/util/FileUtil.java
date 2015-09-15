package tk.gbl.util;

import tk.gbl.util.log.LoggerUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Date: 2014/10/23
 * Time: 10:21
 *
 * @author Tian.Dong
 */
public class FileUtil {

  private String fileName;

  private Properties prop;

  private InputStream is;


  public FileUtil(String filename) {
    prop = new Properties();
    fileName = filename;
    init();
  }

  private void init() {
    try {
      is = getClass().getResourceAsStream(fileName);
      prop.load(is);
    } catch (Exception e) {
      LoggerUtil.error("",e);
    } finally {
      if (is != null) try {
        is.close();
      } catch (IOException e) {
        LoggerUtil.error("",e);
      }
    }
  }

  /**
   * 取得属性
   *
   * @param propertyName 属性名
   * @return 属性值
   */
  public String getProperties(String propertyName) {
    return prop.getProperty(propertyName);
  }

  /**
   * 取得属性
   *
   * @param propertyName 属性名
   * @return 属性值
   */
  public int getPropertiesInt(String propertyName) {
    int value = -1;
    String val = prop.getProperty(propertyName);
    try {
      value = Integer.parseInt(val);
    } catch (Exception e) {
      LoggerUtil.error("getPropertiesInt异常" + propertyName + ":" + val, e);
    }
    return value;
  }

  /**
   * 取得属性
   *
   * @param propertyName 属性名
   * @return 属性值
   */
  public Boolean getPropertiesBool(String propertyName) {
    Boolean bool = null;
    String val = prop.getProperty(propertyName);
    if(val.equals("true")) {
      bool = true;
    } else if(val.equals("false")) {
      bool = false;
    }
    return bool;
  }
}
