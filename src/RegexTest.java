import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

    public static void main(String[] args) {
        String s = "";
        System.out.println(1);
//        boolean result = isQuMall(s);

        boolean result = isMatch1(s);

        System.out.println("result:" +result);
    }


    private static boolean isUserCenter(String msg) {
        String pattern = "^usercenter://.+?codeKey=.+?";
        return regexMatcher(pattern, msg);
    }

    private static boolean isSdoLogin(String msg) {
        String pattern = "^http://(t|id).sdo.com([^,\\s]+?,){2}[^,\\s]*$";

        return regexMatcher(pattern, msg);
    }

    private static boolean isYouNiLogin(String msg) {
        String pattern = "^http://(t|id).sdo.com([^#\\s]+?#){2}[^#\\s]*$";
        return regexMatcher(pattern, msg);
    }

    private static boolean isQuMall(String msg) {
        String pattern = "^(http|https)://(m.qu|qu).sdo.com";
        return regexMatcher(pattern, msg);
    }


    private static boolean isMatch1(String msg) {
        String pattern = "(\\\\\"USERSESSID\\\\\"\\s*:\\s*\\\\\")([^\"\\\\)]*)((\\\\\"))";
        return regexMatcher(pattern, msg);
    }


    private static boolean isMatch(String msg) {
        String pattern = "([&|\\?])(device_id=)(([^&]*))";
        return regexMatcher(pattern, msg);
    }

    private static String replaceAll(String msg){
        String pattern = "([&|\\?])(device_id=)(([^&]*))";
        return replaceAll(pattern,msg);
    }

    private static String replaceAll(String pattern, String msg) {
        if (msg == null) return "";
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(msg);
        return matcher.replaceAll("$1$2xxxxxxxx");
    }

    private static boolean regexMatcher(String pattern, String msg) {
        if (msg == null) return false;
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(msg);
        boolean b = matcher.find();
        if (b) {
//            String group = matcher.group();
//            System.out.println(group);
            int i = matcher.groupCount();
            for (int i1 = 0; i1 < i; i1++) {
                System.out.println(i1 + " ===> " + matcher.group(i1));
            }
        }
        return b;
    }
}
