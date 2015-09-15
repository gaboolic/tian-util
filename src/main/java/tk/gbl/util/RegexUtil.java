package tk.gbl.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
  public static String regex(String source, String regex) {
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(source);
    if (m.find()) {
      return m.group();
    }
    return null;
  }

  public static String regex(String source, String regex,int index) {
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(source);
    if (m.find()) {
      return m.group(index);
    }
    return null;
  }
}