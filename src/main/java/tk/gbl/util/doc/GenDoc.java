package tk.gbl.util.doc;

import org.springframework.web.bind.annotation.RequestMapping;
import tk.gbl.util.anno.DocField;
import tk.gbl.util.anno.ValidField;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Date: 2015/3/25
 * Time: 11:28
 *
 * @author Tian.Dong
 */
public class GenDoc {


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
