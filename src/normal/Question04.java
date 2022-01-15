package normal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xv2kgi/"/>
 * 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 示例 4:
 * <p>
 * 输入: s = ""
 * 输出: 0
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 */
public class Question04 {

    public static void main(String[] args) {
        String s = " ";

        int i = lengthOfLongestSubstring(s);

        System.out.println("最长子串长度：" + i);
    }


    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() <= 0) return 0;
        int maxLength = 0;
        int left = 0,right =0;
        Set<Character> set = new HashSet<>();
        while (right < s.length()){
            while (set.contains(s.charAt(right))) set.remove(left++);
            set.add(s.charAt(right++));
            maxLength = Math.max(set.size(),maxLength);
        }
        return maxLength;
    }

    public static int lengthOfLongestSubstring1(String s){
        //边界条件判断
        if (s.length() == 0)
            return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0, j = 0; i < s.length(); ++i) {
            //判断是否有重复的元素，如果有重复的元素，就更新j的值
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            //把i指向的值存储到map中
            map.put(s.charAt(i), i);
            //更新j到i之间的最大距离，也就是无重复字符的最长子串
            max = Math.max(max, i - j + 1);
        }
        return max;
    }
}
