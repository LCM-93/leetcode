package easy;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnx13t/"/>
 * <p>
 * 整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * <p>
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * <p>
 * 输入：x = 123
 * 输出：321
 * <p>
 * 输入：x = -123
 * 输出：-321
 * <p>
 * 输入：x = 120
 * 输出：21
 * <p>
 * 输入：x = 0
 * 输出：0
 *  
 * <p>
 * 提示：
 * <p>
 * -231 <= x <= 231 - 1
 */
public class Question13 {

    public static void main(String[] args) {
        int x = 123;
        int result = reverse1(x);
        System.out.println(result);
    }

    //解法没错，不够精致，无法解决数字溢出
    public static int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int a = 0;
            if (x >= 10 || x <= -10) {
                a = x % 10;
                x = x / 10;
            } else {
                a = x;
                x = 0;
            }
            result = result * 10 + a;
        }
        return result;
    }

    public static int reverse1(int x) {
        int result = 0;
        while (x != 0) {
            int a = x % 10;
            int newResult = result * 10 + a;
            if ((newResult - a) / 10 != result)  //如果新值溢出了，则回退一步后值不会等于旧值
                return 0;
            result = newResult;
            x = x / 10;
        }
        return result;
    }

    public static int reverse2(int x) {
        long result = 0;
        while (x != 0) {
            result = result * 10 + x % 10;
            x /= 10;
        }
        return (int) result == result ? (int) result : 0;
    }

}
