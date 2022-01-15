package easy;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnzlu6/"/>
 * <p>
 * 计数质数
 * 统计所有小于非负整数 n 的质数的数量。
 * <p>
 *  
 * <p>
 * 示例 1：
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * <p>
 * 示例 2：
 * 输入：n = 0
 * 输出：0
 * <p>
 * 示例 3：
 * 输入：n = 1
 * 输出：0
 *  
 * <p>
 * 提示：
 * 0 <= n <= 5 * 106
 */
public class Question41 {

    public static void main(String[] args) {
        System.out.println(countPrimes1(1));

    }


    /**
     * 埃拉托斯特尼筛法（埃氏筛）
     *
     * @param n
     * @return
     */
    public static int countPrimes(int n) {
        boolean[] res = new boolean[n];
        int count = 0;

        for (int i = 2; i < n; i++) {
            if (res[i]) continue;
            count++;
            for (int j = i; j < n; j += i) {
                res[j] = true;
            }
        }
        return count;
    }

    /**
     * 欧式筛选法
     *
     * @param n
     * @return
     */
    public static int countPrimes1(int n) {
        boolean[] visited = new boolean[n];
        int[] primes = new int[n];
        int count = 0;

        for (int i = 2; i < n; i++) {

            if (!visited[i]) {
                primes[count++] = i; //记录素数
            }

            for (int j = 0; j < count && i * primes[j] < n; j++) {
                visited[i * primes[j]] = true;
                if (i % primes[j] == 0) break;  //用最小倍数来标记素数，减少标记次数
                //例如 18 是素数 3 的倍数，但是我们并不会用 3 的倍数来标记掉 18 , 因为假如我们用 3 来标记 18 ，
                //那么 i 势必要跑到 6 这样 i * 3 才会标记掉18， 但是在这之前因为primes[]中存放的是一个递增的素数集合，
                //所以i 就已经被 3 之前的一个素数 2 弄死了，退出循环了。
                //那么为什么要这么做？因为 6 既然能被 2 整除， 那么 18 迟早会被 2 标记，所以这里就不要再用 3 来重复标记 18。
                //你问我怎么知道 18 迟早会被 2 标记的？ 6 能被 2 整除，那么 3 乘 6 就可以分解为 3 乘 2 乘 3， 也就是 2 乘 9 ， 2 乘 9 就标记掉了18.
                //所以只需要等到i=9时，与primes[0]=2相乘就能标记掉18
            }
        }
        return count;
    }

}
