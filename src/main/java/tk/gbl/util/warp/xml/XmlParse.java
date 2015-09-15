package tk.gbl.util.warp.xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import tk.gbl.util.log.LoggerUtil;
import tk.gbl.util.warp.CustomArrayList;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Xml解析
 * 基于dom4j 和自定义XmlField注解
 * <p/>
 * Date: 2015-8-19
 * Time: 下午2:12:30
 *
 * @author tian.dong@corp.elong.com
 */
public class XmlParse {
  public static <T> T parse(String xml, Class<T> target) throws Exception {
    int offset = xml.indexOf("<?xml");
    if (offset != -1) {
      xml = xml.substring(offset);
    }
    Object obj = target.newInstance();
    Document doc = null;
    try {
      doc = DocumentHelper.parseText(xml);
    } catch (Exception e) {
      LoggerUtil.error("解析xml文档失败" + xml, e);
      throw e;
    }
    Element root = doc.getRootElement();

    for (Field field : obj.getClass().getSuperclass().getDeclaredFields()) {
      setField(obj, field, root);
    }
    setDefaultField(obj, root);
    return (T) obj;
  }


  /* 用xml的一个节点给obj的某个field赋值*/
  private static void setField(Object obj, Field rootField, Element root) throws Exception {
    if (root == null || root.getText() == null) {
      return;
    }
    rootField.setAccessible(true);
    XmlField xmlField = rootField.getAnnotation(XmlField.class);
    String name = rootField.getName();
    if (xmlField.value() != null && !xmlField.value().equals("")) {
      name = xmlField.value();
    }
    String value = null;
    if (xmlField.isAttr()) {
      Attribute attribute = root.attribute(name);
      if (attribute != null) {
        value = attribute.getText();
      }
    } else {
      Element element = root.element(name);
      if (element != null) {
        value = element.getTextTrim();
      }
    }
    if (rootField.getType().equals(String.class)) {
            /* 基本类型 */
      rootField.set(obj, value);
    } else if (rootField.getType().equals(Integer.class)
        || rootField.getType().equals(int.class)) {
      if (value != null) {
        rootField.set(obj, Integer.parseInt(value));
      }
    } else if (rootField.getType().equals(Long.class)
        || rootField.getType().equals(long.class)) {
      if (value != null) {
        rootField.set(obj, Long.parseLong(value));
      }
    } else if (rootField.getType().equals(Double.class)
        || rootField.getType().equals(double.class)) {
      if (value != null) {
        rootField.set(obj, Double.parseDouble(value));
      }
    } else {
            /* 复杂对象*/
      Class fieldClass = rootField.getType();
      Object fieldObject = null;
            /* 如果field的类是List类型*/
      if (xmlField.isList()) {
        fieldObject = new CustomArrayList();


        Type type = rootField.getGenericType();
        Type[] actualTypes = null;
        if (type instanceof ParameterizedType) {
          ParameterizedType paramType = (ParameterizedType) type;
          actualTypes = paramType.getActualTypeArguments();
        }

        if (root.element(name) == null) {
          String itemName = xmlField.listName();
          for (Object element : root.elements(itemName)) {
            Object itemObj = ((Class) actualTypes[0]).newInstance();
            Element ele = (Element) element;
            for (Field field : itemObj.getClass().getDeclaredFields()) {
              setField(itemObj, field, ele);
            }
            ((CustomArrayList) fieldObject).add(itemObj);
          }
        } else {
          Element listElement = root.element(name);
          for (Object itemElementObj : listElement.elements()) {
            Element itemElement = (Element) itemElementObj;
            Object itemObj = ((Class) actualTypes[0]).newInstance();
            for (Field field : itemObj.getClass().getDeclaredFields()) {
              setField(itemObj, field, itemElement);
            }
            ((CustomArrayList) fieldObject).add(itemObj);
          }
        }
      } else {
         /* 如果不是List而是普通对象，则new一个实例，递归赋值*/
        fieldObject = fieldClass.newInstance();
        setDefaultField(fieldObject, root.element(name));
      }
       /* 将创建好的fieldObject 赋值给rootField*/
      rootField.set(obj, fieldObject);
    }
  }

  /*用一个节点赋值给Field */
  private static void setDefaultField(Object fieldObject, Element root) throws Exception {
    for (Field field : fieldObject.getClass().getDeclaredFields()) {
      setField(fieldObject, field, root);
    }
  }
}