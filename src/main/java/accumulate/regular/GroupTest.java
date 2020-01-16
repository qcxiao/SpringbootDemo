package accumulate.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: yaodao
 * @Date: 2018/8/7 14:17
 * @参考: https://segmentfault.com/a/1190000009162306#articleHeader22
 */
public class GroupTest {
    public static void main(String[] args) {
        String str = "jsonp1891397_0__1_64531_phone({\"context\":{},\"dscContext\":{},\"ext\":\"\"})";

        Pattern pattern = Pattern.compile("\\((?<content>.+?)\\)");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.group(0));
            System.out.println(matcher.group("content"));
            System.out.println(matcher.group(1));
        }
    }
}
