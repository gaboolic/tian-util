package tk.gbl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tk.gbl.util.anno.DocField;
import tk.gbl.util.anno.ValidField;
import tk.gbl.util.doc.ApiItem;
import tk.gbl.util.doc.DocItem;
import tk.gbl.util.doc.ParamItem;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2016/5/5
 * Time: 11:04
 *
 * @author Tian.Dong
 */
@RequestMapping("/apidoc")
@Controller
public class ApiDocController {

  @Resource
  List<Class> docClassList;

  @RequestMapping(value = "doc", method = RequestMethod.GET)
  public String doc(Model model) {

    List<DocItem> docItems = new ArrayList<>();
    model.addAttribute("docItems",docItems);
    for (Class cls : docClassList) {
      DocField clsDocField = (DocField) cls.getAnnotation(DocField.class);
      RequestMapping clsMapping = (RequestMapping) cls.getAnnotation(RequestMapping.class);
      DocItem docItem = new DocItem();
      if (clsDocField != null) {
        docItem.setDocName(clsDocField.description());
      }
      docItem.setDocPrefix(clsMapping.value()[0]);
      docItems.add(docItem);
      List<ApiItem> apiItems = new ArrayList<>();
      docItem.setApiItemList(apiItems);
      for (Method method : cls.getMethods()) {
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
        DocField methodDocField = method.getAnnotation(DocField.class);
        if (requestMapping != null) {
          ApiItem apiItem = new ApiItem();
          apiItems.add(apiItem);
          Class paramType = getValidObject(method);
          if(methodDocField!=null) {
            apiItem.setName(methodDocField.description());
          }
          apiItem.setUrl(requestMapping.value()[0]);
          List<ParamItem> paramItems = new ArrayList<>();
          apiItem.setParamList(paramItems);
          while (paramType != Object.class) {
            for (Field field : paramType.getDeclaredFields()) {
              DocField docField = field.getAnnotation(DocField.class);
              if (docField != null) {
                ParamItem paramItem = new ParamItem();
                paramItem.setRealType(field.getType());
                paramItem.setType(field.getType().getSimpleName());
                paramItem.setParam(field.getName());
                paramItem.setDesc(docField.description());
                paramItems.add(paramItem);
                if(!paramItem.isBasic()) {
                  dealSubParamItemList(paramItem);
                }
              }
            }
            paramType = paramType.getSuperclass();
          }
          List<ParamItem> retItems = new ArrayList<>();
          apiItem.setRetList(retItems);
          Class returnType = method.getReturnType();
          for (Field field : returnType.getDeclaredFields()) {
            DocField docField = field.getAnnotation(DocField.class);
            if (docField != null) {
              ParamItem paramItem = new ParamItem();
              paramItem.setType(field.getType().getTypeName());
              paramItem.setParam(field.getName());
              paramItem.setDesc(docField.description());
              paramItems.add(paramItem);
            }
          }
        }
      }
    }
    return "apidoc";
  }

  private void dealSubParamItemList(ParamItem rootParamItem) {
    List<ParamItem> subParamItems = new ArrayList<>();
    rootParamItem.setSubParamItemList(subParamItems);

    for (Field field : rootParamItem.getRealType().getDeclaredFields()) {
      DocField docField = field.getAnnotation(DocField.class);
      if (docField != null) {
        ParamItem paramItem = new ParamItem();
        paramItem.setRealType(field.getType());
        paramItem.setType(field.getType().getSimpleName());
        paramItem.setParam(field.getName());
        paramItem.setDesc(docField.description());
        subParamItems.add(paramItem);
        if(!paramItem.isBasic()) {
          dealSubParamItemList(paramItem);
        }
      }
    }
  }

  private static Class getValidObject(Method method) {
    Annotation[][] annotationList = method.getParameterAnnotations();
    for (int i = 0; i < annotationList.length; i++) {
      if (haveValidAnnotation(annotationList[i])) {
        return method.getParameterTypes()[i];
      }
    }
    return method.getParameterTypes()[0];
  }

  private static boolean haveValidAnnotation(Annotation[] annotations) {
    for (Annotation annotation : annotations) {
      if (annotation.annotationType().equals(ValidField.class)) {
        return true;
      }
    }
    return false;
  }

  private static Class getParamType(Class[] types) {
    for (Class cls : types) {
      if (cls.getAnnotation(DocField.class) != null) {
        return cls;
      }
    }
    return null;
  }
}
