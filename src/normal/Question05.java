package normal;


/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvn3ke/"/>
 * 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * <p>
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 * <p>
 * 示例 3：
 * 输入：s = "a"
 * 输出："a"
 * <p>
 * 示例 4：
 * 输入：s = "ac"
 * 输出："a"
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母（大写和/或小写）组成
 */
public class Question05 {

    public static void main(String[] args) {
        String s = "babad";
        String s1 = longestPalindrome2(s);
        System.out.println(s1);
    }

    public static String longestPalindrome2(String s) {
        int maxLength = 0;
        int maxStart = 0;
        int start = 0;

        int length = s.length();
        while (start < length) {
            int left = start;
            while (start < length - 1 && s.charAt(start) == s.charAt(start + 1)) {
                start++;
            }
            int right = start;
            start++;
            while (left > 0 && right < length-1 && s.charAt(right+1) == s.charAt(left-1)) {
                right++;
                left--;
            }
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                maxStart = left;
            }
        }

        return s.substring(maxStart, maxStart + maxLength);

    }


    /**
     * 暴力求解 找出所有回文子串
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() <= 0) return "";
        int start = 0, maxLength = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (j - i < maxLength) continue;
                if (isPalindrome(s, i, j)) {
                    start = i;
                    maxLength = j - i + 1;
                }
            }
        }
        return s.substring(start, maxLength);
    }

    public static boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) return false;
        }
        return true;
    }

    /**
     * 中心扩散法
     *
     * @return
     */
    public static String longestPalindrome1(String s) {
        if (s.length() < 2) return s;
        int maxLenght = 0;
        int start = 0;

        int length = s.length();
        for (int i = 0; i < length; ) {
            if (length - i < maxLenght / 2) break; //如果剩下的长度不足最大长度的一半  可以直接停止了
            int left = i, right = i;
            while (right < length - 1 && s.charAt(right + 1) == s.charAt(right)) { //过滤相邻相同的字符
                right++;
            }
            i = right + 1; //下一次遍历直接从右边加以为开始

            while (right < length - 1 && left > 0 && s.charAt(right + 1) == s.charAt(left - 1)) { //扩散比较
                right++;
                left--;
            }

            if (right - left + 1 > maxLenght) {
                start = left;
                maxLenght = right - left + 1;
            }
        }

        return s.substring(start, start + maxLenght);
    }
}
