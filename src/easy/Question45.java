package easy;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnyode/"/>
 * 汉明距离
 * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
 *
 * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：x = 1, y = 4
 * 输出：2
 * 解释：
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 * 上面的箭头指出了对应二进制位不同的位置。
 * 示例 2：
 *
 * 输入：x = 3, y = 1
 * 输出：1
 *  
 *
 * 提示：
 *
 * 0 <= x, y <= 231 - 1
 * 相关标签
 *
 *
 */
public class Question45 {

    public static void main(String[] args){
        int distance = hammingDistance(4, 1);
        System.out.println(distance);
    }


    /**
     * 异或一次 然后求1的个数
     * @return
     */
    public static int hammingDistance(int x, int y) {
        int num = x ^ y;

        int count = 0;
        while (num != 0) {  //位运算优化，当n=0了就没必要再计算了
            count += num & 1;
            num = num >>> 1;
        }
        return count;
    }
}
