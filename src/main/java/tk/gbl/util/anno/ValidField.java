package tk.gbl.util.anno;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Documented
public @interface ValidField {
  String value() default "";

  String regex() default "";

  String description() default "";
}