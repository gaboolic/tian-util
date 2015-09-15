package tk.gbl.util.warp.xml;

import java.lang.annotation.*;

/**
 * Date: 2015/8/18
 * Time: 18:25
 *
 * @author Tian.Dong
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Documented
public @interface XmlField {
  String value() default "";

  boolean isAttr() default false;

  boolean isList() default false;

  String listName() default "";
}
