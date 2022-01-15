package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xngt85/"/>
 * <p>
 * Fizz Buzz
 * 给你一个整数 n ，找出从 1 到 n 各个整数的 Fizz Buzz 表示，并用字符串数组 answer（下标从 1 开始）返回结果，其中：
 * <p>
 * answer[i] == "FizzBuzz" 如果 i 同时是 3 和 5 的倍数。
 * answer[i] == "Fizz" 如果 i 是 3 的倍数。
 * answer[i] == "Buzz" 如果 i 是 5 的倍数。
 * answer[i] == i （以字符串形式）如果上述条件全不满足。
 *  
 * <p>
 * 示例 1：
 * 输入：n = 3
 * 输出：["1","2","Fizz"]
 * <p>
 * 示例 2：
 * 输入：n = 5
 * 输出：["1","2","Fizz","4","Buzz"]
 * <p>
 * 示例 3：
 * 输入：n = 15
 * 输出：["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 104
 */
public class Question40 {

    public static void main(String[] args) {
        List<String> strings = fizzBuzz1(20);
        System.out.println(strings.toString());
    }


    public static List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            result.add(i + "");
        }
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0) {
                result.set(i - 1, "Fizz");
            }
        }
        for (int i = 1; i <= n; i++) {
            if (i % 5 == 0) {
                result.set(i - 1, "Buzz");
            }
        }
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                result.set(i - 1, "FizzBuzz");
            }
        }

        return result;
    }

    public static List<String> fizzBuzz1(int n) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            result.add(i + "");
            if (i % 3 == 0) {
                result.set(i - 1, "Fizz");
            }
            if (i % 5 == 0) {
                result.set(i - 1, "Buzz");
            }
            if (i % 15 == 0) {
                result.set(i - 1, "FizzBuzz");
            }
        }
        return result;
    }
}
