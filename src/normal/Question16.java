package normal;

import java.util.*;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xv8ka1/"/>
 * <p>
 * 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * <p>
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * <p>
 * 示例 2：
 * 输入：digits = ""
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 */
public class Question16 {

    public static void main(String[] args) {
        String digits = "7";
        List<String> list = letterCombinations(digits);
        System.out.println(list);
    }

    //感觉是个暴力解法
    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() <= 0) return new ArrayList<>();
        Map<Character, char[]> map = new HashMap<>();
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r','s'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});

        List<String> result = new ArrayList<>();

        helper(map, digits, "", result);

        return result;
    }

    public static void helper(Map<Character, char[]> map, String digits, String cur, List<String> results) {
        char charAt = digits.charAt(0);
        char[] chars = map.get(charAt);
        boolean isLast = digits.length() == 1;
        for (char aChar : chars) {
            String curS = cur + aChar;
            if (isLast) {
                results.add(curS);
            } else {
                helper(map, digits.substring(1), curS, results);
            }
        }
    }

}
