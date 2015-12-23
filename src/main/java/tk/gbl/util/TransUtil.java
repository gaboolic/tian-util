package tk.gbl.util;

import tk.gbl.util.anno.ValidJump;
import tk.gbl.util.log.LoggerUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.*;

/**
 * 把entity对象数据填充到pojo对象
 * <p/>
 * Date: 2014/8/12
 * Time: 16:38
 *
 * @author Tian.Dong
 */
public class TransUtil {

  /**
   * 把entity对象数据填充到pojo对象
   *
   * @param from entity对象
   * @param to   pojo对象
   * @throws NoSuchFieldException
   * @throws IllegalAccessException
   */
  public static void trans(Object from, Object to) {
    if (from == null) {
      return;
    }
    try {
      try {
//        if (BasePojo.class.isAssignableFrom(to.getClass())) {
//          Method setToIdMethod = to.getClass().getMethod("setId", Integer.class);
//          Method getFromIdMethod = from.getClass().getMethod("getId");
//          setToIdMethod.invoke(to, getFromIdMethod.invoke(from));
//        }
      } catch (Exception e) {
      }
      for (Field field : to.getClass().getDeclaredFields()) {
        if (field.getAnnotation(ValidJump.class) != null) {
          continue;
        }
        field.setAccessible(true);
        //Field fromField = from.getClass().getDeclaredField(field.getName());
        Method getFromFieldMethod = from.getClass().getMethod("get" + upFirst(field.getName()));
        //fromField.setAccessible(true);
        // fromFieldValue，相当于from.getFromField();
        Object fromFieldValue = getFromFieldMethod.invoke(from);
        if (fromFieldValue == null) {
          continue;
        }
        if (fromFieldValue.getClass().equals(Integer.class)
            || fromFieldValue.getClass().equals(Double.class)
            || fromFieldValue.getClass().equals(Boolean.class)
            || fromFieldValue.getClass().equals(String.class)
            || fromFieldValue.getClass().equals(Date.class)
            || fromFieldValue.getClass().equals(Timestamp.class)
            || fromFieldValue.getClass().equals(Set.class)
            || fromFieldValue.getClass().equals(List.class)
            || fromFieldValue.getClass().equals(Map.class)
            ) {
          //基本类型 或者String，直接赋值
          field.set(to, fromFieldValue);
        } else {
          //对象，递归调用
          field.set(to, field.getType().newInstance());
          trans(fromFieldValue, field.get(to));
        }
      }
    } catch (Exception e) {
      LoggerUtil.error("TransUtil", e);
    }
  }

  private static String upFirst(String name) {
    return name.substring(0, 1).toUpperCase() + name.substring(1);
  }


  public static <T> T gen(Object from, Class<T> clazz) {
    Object to = null;
    try {
      to = clazz.newInstance();
    } catch (Exception e) {
      LoggerUtil.error("TransUtil", e);
    }
    trans(from, to);
    return (T) to;
  }

  public static <T> List<T> genList(List fromList, Class<T> toClass) {
    List toList = new ArrayList();
    for (Object from : fromList) {
      Object to = gen(from, toClass);
      toList.add(to);
    }
    return toList;
  }

  public static <T> PageList<T> genPageList(List fromList, Class<T> toClass) {
    PageList toList = new PageList();
    for (Object from : fromList) {
      Object to = gen(from, toClass);
      toList.add(to);
    }
    PageList fromPageList = (PageList) fromList;
    toList.setAllCount(fromPageList.getAllCount());
    toList.setPageCount(fromPageList.getPageCount());
    toList.setPageSize(fromPageList.getPageSize());
    return toList;
  }
}

