package normal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvmy42/"/>
 * <p>
 * 矩阵置零
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 * <p>
 * 示例 1：
 * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：[[1,0,1],[0,0,0],[1,0,1]]
 * <p>
 * <p>
 * 示例 2：
 * 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 *  
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -231 <= matrix[i][j] <= 231 - 1
 *  
 * <p>
 * 进阶：
 * <p>
 * 一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个仅使用常量空间的解决方案吗？
 */
public class Question02 {

    public static void main(String[] args) {
        int[][] nums = new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        setZeroes(nums);
        for (int[] num : nums) {
            System.out.println(Arrays.toString(num));
        }
    }

    public static void setZeroes(int[][] matrix) {
        Set<Integer> zeroRow = new HashSet<>();
        Set<Integer> zeroColumn = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    zeroRow.add(i);
                    zeroColumn.add(j);
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(zeroRow.contains(i) || zeroColumn.contains(j)){
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
