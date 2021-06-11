package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2cv1c/
 * 加一
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * 示例 2：
 * <p>
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * 示例 3：
 * <p>
 * 输入：digits = [0]
 * 输出：[1]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 */
public class Question07 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
       int[] result =  plusOne(nums);
        System.out.println(Arrays.toString(result));
    }

    //这是推荐解法  自己已经蒙了  陷入循环不能自拔
    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if(digits[i] != 9){
                digits[i]++;
                return digits;
            }else{
                digits[i] = 0;
            }
        }
        int[] result = new int[digits.length+1];
        result[0] = 1;
        return result;
    }
}
