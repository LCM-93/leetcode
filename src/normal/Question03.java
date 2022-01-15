package normal;

import java.util.*;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvaszc/"/>
 * <p>
 * 字母异位词分组
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * <p>
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 * <p>
 * <p>
 * 示例 1:
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * <p>
 * 示例 2:
 * 输入: strs = [""]
 * 输出: [[""]]
 * <p>
 * 示例 3:
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 */
public class Question03 {

    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = groupAnagrams(strs);
        System.out.println(lists);

    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length <= 0) return new ArrayList<>();
//        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            if (!map.containsKey(hash(str))) {
                map.put(hash(str), new ArrayList<>());
            }
            map.get(hash(str)).add(str);
        }

//        for (Map.Entry<String, List<String>> stringListEntry : map.entrySet()) {
////            System.out.println(stringListEntry.getKey()+" => " +stringListEntry.getValue().toString());
//            result.add(stringListEntry.getValue());
//        }

        return new ArrayList<>(map.values());
    }

    public static String hash(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }
}
