package easy;

/**
 * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnmav1/
 * 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 * <p>
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 */
public class Question20 {

    public static void main(String[] args) {
        String[] strings = new String[]{"dog","racecar","car"};
        System.out.println(longestCommonPrefix(strings));

    }


    public static String longestCommonPrefix(String[] strs) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            char target = strs[0].charAt(i);
            boolean isSame = true;
            for (int j = 1; j < strs.length; j++) {
                if(strs[j].length() <= i) return stringBuilder.toString();
                if(strs[j].charAt(i) != target) isSame =false;
            }
            if(isSame) stringBuilder.append(target);
            else return stringBuilder.toString();
        }
        return stringBuilder.toString();
    }


    public static String longestCommonPrefix1(String[] strs) {
        //边界条件判断
        if (strs == null || strs.length == 0)
            return "";
        //默认第一个字符串是他们的公共前缀
        String pre = strs[0];
        int i = 1;
        while (i < strs.length) {
            //不断的截取
            while (strs[i].indexOf(pre) != 0)
                pre = pre.substring(0, pre.length() - 1);
            i++;
        }
        return pre;
    }
}
