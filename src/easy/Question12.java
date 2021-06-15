package easy;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnhbqj/
 *
 * 反转字符串
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 *
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * 示例 2：
 *
 * 输入：["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 *
 */
public class Question12 {

    public static void main(String[] args) {
        char[] chars = new char[]{'h','e','l','l','o'};
        reverseString1(chars);
        System.out.println(Arrays.toString(chars));
    }

    /**
     * 第一反应想到双指针交换
     */
    public static void reverseString(char[] s){
        int start = 0;
        int end = s.length-1;
        while (start < end){
            char temp = s[start];
            s[start++] = s[end];
            s[end--] = temp;
        }
    }

    public static void reverseString1(char[] s){
        int start = 0;
        int end = s.length-1;
        while (start < end){
            exchange(s,start++,end--);
        }
    }
    /**
     *  两数交换的多种方式
     */
    private static void exchange(char[] chars,int left,int right){
//        //第一种 临时变量
//        char temp = chars[left];
//        chars[left] = chars[right];
//        chars[right] = temp;
//
//        //第二种
//        chars[left] = (char) (chars[left] + chars[right]);
//        chars[right] = (char) (chars[left] - chars[right]);
//        chars[left] = (char) (chars[left] - chars[right]);
//
//        //第三种
//        chars[left] = (char) (chars[left] - chars[right]);
//        chars[right] = (char) (chars[left]+ chars[right]);
//        chars[left] = (char) (chars[right] - chars[left]);

        //第四种  异或运算，对同一个字符进行两次异或运算就会回到原来的值
        // 假设有个临时变量 t = a ^ b  那么 a = t ^ b , b = t ^ a ;
        chars[left] ^= chars[right];
        chars[right] ^= chars[left];
        chars[left] ^= chars[right];
    }
}
