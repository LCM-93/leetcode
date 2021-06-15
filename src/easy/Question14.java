package easy;


import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn5z8r/
 * 字符串中的第一个唯一字符
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * s = "leetcode"
 * 返回 0
 * <p>
 * s = "loveleetcode"
 * 返回 2
 *  
 */
public class Question14 {
    public static void main(String[] args) {
        String s = "aabb";
        int result = firstUniqChar(s);
        System.out.println(result);
    }

    public static int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), -1);
            } else {
                map.put(s.charAt(i), i);
            }
        }
        int result = s.length();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() != -1) {
                result = result < entry.getValue() ? result : entry.getValue();
            }
        }
        return result >= s.length() ? -1 : result;
    }


    public static int firstUniqChar1(String s) {
        //用hashmap记录每个字符出现的次数，再次遍历，出现一次的
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            map.put(aChar,map.getOrDefault(aChar,0)+1);
        }
        for (int i = 0; i < s.length(); i++) {
            if(map.get(s.charAt(i)) == 1) return i;
        }
        return -1;
    }

    //使用java api特性解决
    public static int firstUniqChar2(String s){
        for (int i = 0; i < s.length(); i++) {
            if(s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i)))return i;
        }
        return -1;
    }
}
