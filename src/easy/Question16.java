package easy;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xne8id/"/>
 * 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "race a car"
 * 输出: false
 */
public class Question16 {

    public static void main(String[] args) {
        String str = "A man, a plan, a canal: Panama";
        boolean result = isPalindrome2(str);
        System.out.println(result);
    }

    public static boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int start = 0;
        int end = s.length() - 1;

        while (start < end) {
            if (s.charAt(start) == ' ' || !Character.isLetterOrDigit(s.charAt(start))) {
                start++;
                continue;
            }
            if (s.charAt(end) == ' ' || !Character.isLetterOrDigit(s.charAt(end))) {
                end--;
                continue;
            }
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome1(String s) {
        if (s == null || s.length() == 0)
            return true;
        s = s.toLowerCase();
        int start = 0;
        int end = s.length() - 1;

        while (start < end) {
            while (start < end && !Character.isLetterOrDigit(s.charAt(start))) {
                start++;
            }
            while (start < end && !Character.isLetterOrDigit(s.charAt(end))) {
                end--;
            }
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome2(String s) {
        String actual = s.replaceAll("[^A-Za-z0-9]","").toLowerCase();
        String rev = new StringBuffer(actual).reverse().toString();
        return actual.equals(rev);
    }
}
