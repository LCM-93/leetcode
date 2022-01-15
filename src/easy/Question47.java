package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xncfnv/"/>
 * <p>
 * 杨辉三角
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * <p>
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * <p>
 * <p>
 * 示例 1:
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * <p>
 * 示例 2:
 * 输入: numRows = 1
 * 输出: [[1]]
 *  
 * <p>
 * 提示:
 * <p>
 * 1 <= numRows <= 30
 */
public class Question47 {

    public static void main(String[] args) {
        List<List<Integer>> generate = generate1(5);
        System.out.println(generate.toString());
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        int[][] nums = new int[numRows][numRows];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j <= i; j++) {
                if (j - 1 < 0 || i - 1 < 0) {
                    nums[i][j] = 1;
                } else {
                    nums[i][j] = nums[i - 1][j - 1] + nums[i - 1][j];
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            int[] ints = Arrays.copyOf(nums[i], i + 1);
            List<Integer> assetList = new ArrayList();
            for (int anInt : ints) {
                assetList.add(anInt);
            }
            result.add(assetList);
        }
        return result;
    }

    public static List<List<Integer>> generate1(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            list.add(0, 1);
            for (int j = 1; j < list.size() - 1; j++) {
                list.set(j, list.get(j) + list.get(j + 1));
            }
            result.add(new ArrayList<>(list));
        }

        return result;
    }
}
