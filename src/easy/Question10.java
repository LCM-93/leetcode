package easy;

import java.util.HashSet;
import java.util.Set;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2f9gg/"/>
 * 有效的数独
 * 请你判断一个 9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 * <p>
 * 注意：
 * <p>
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * <p>
 * 输入：board =
 * [['5','3','.','.','7','.','.','.','.']
 * ,['6','.','.','1','9','5','.','.','.']
 * ,['.','9','8','.','.','.','.','6','.']
 * ,['8','.','.','.','6','.','.','.','3']
 * ,['4','.','.','8','.','3','.','.','1']
 * ,['7','.','.','.','2','.','.','.','6']
 * ,['.','6','.','.','.','.','2','8','.']
 * ,['.','.','.','4','1','9','.','.','5']
 * ,['.','.','.','.','8','.','.','7','9']]
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：board =
 * [['8','3','.','.','7','.','.','.','.']
 * ,['6','.','.','1','9','5','.','.','.']
 * ,['.','9','8','.','.','.','.','6','.']
 * ,['8','.','.','.','6','.','.','.','3']
 * ,['4','.','.','8','.','3','.','.','1']
 * ,['7','.','.','.','2','.','.','.','6']
 * ,['.','6','.','.','.','.','2','8','.']
 * ,['.','.','.','4','1','9','.','.','5']
 * ,['.','.','.','.','8','.','.','7','9']]
 * 输出：false
 * 解释：除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
 *  
 * <p>
 * 提示：
 * <p>
 * board.length == 9
 * board[i].length == 9
 * board[i][j] 是一位数字或者 '.'
 */
public class Question10 {

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        boolean result = isValidSudoku1(board);
        System.out.println(result);
    }

    /**
     * 对位运算不熟悉
     * 0 0 0 0 0 0 1 0      2
     * 0 0 0 0 0 1 0 0      4
     * -------------------- | 按位或运算
     * 0 0 0 0 0 1 1 0      6
     *
     * 此时如果新的值为8
     * 0 0 0 0 0 1 1 0      6
     * 0 0 0 0 1 0 0 0      8
     * -------------------- & 按位与运算
     * 0 0 0 0 0 0 0 0      0
     *
     * 此时如果新的值是重复的4
     * 0 0 0 0 0 1 1 0      6
     * 0 0 0 0 0 1 0 0      4
     * -------------------- & 按位与运算
     * 0 0 0 0 0 1 0 0      4 值大于0
     */
    public static boolean isValidSudoku1(char[][] board) {
        int[] line = new int[9];
        int[] column = new int[9];
        int[] cell = new int[9];
        int shift = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //如果还没有填数字，直接跳过
                if (board[i][j] == '.')
                    continue;
                //将1按位左移相应位数 得到的数即为2的倍数 2、4、8、16的值
                shift = 1 << (board[i][j] - '0'); //使用 -'0' 实现char转int型
                int k = (i / 3) * 3 + j / 3;
                //如果对应的位置只要有一个大于0，说明有冲突，直接返回false
                //如果新的数字是不重复的，按位与后值应该都为0
                if ((column[i] & shift) > 0 || (line[j] & shift) > 0 || (cell[k] & shift) > 0)
                    return false;
                column[i] |= shift;  //按位或，同一列未重复的数字按位或
                line[j] |= shift;
                cell[k] |= shift;
            }
        }
        return true;
    }


    public boolean isValidSudoku2(char board[][]) {
        int length = board.length;
        //二维数组line表示的是对应的行中是否有对应的数字，比如line[0][3]
        //表示的是第0行（实际上是第1行，因为数组的下标是从0开始的）是否有数字3
        int line[][] = new int[length][length];
        int column[][] = new int[length][length];
        int cell[][] = new int[length][length];
        for (int i = 0; i < length; ++i)
            for (int j = 0; j < length; ++j) {
                //如果还没有填数字，直接跳过
                if (board[i][j] == '.')
                    continue;
                //num是当前格子的数字
                int num = board[i][j] - '0' - 1;
                //k是第几个单元格，9宫格数独横着和竖着都是3个单元格
                int k = i / 3 * 3 + j / 3;
                //如果当前数字对应的行和列以及单元格，只要一个由数字，说明冲突了，直接返回false。
                //举个例子，如果line[i][num]不等于0，说明第i（i从0开始）行有num这个数字。
                if (line[i][num] != 0 || column[j][num] != 0 || cell[k][num] != 0)
                    return false;
                //表示第i行有num这个数字，第j列有num这个数字，对应的单元格内也有num这个数字
                line[i][num] = column[j][num] = cell[k][num] = 1;
            }
        return true;
    }





    //自己的解法硬算
    public static boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (containsDuplicate(board[i])) return false;
            char[] temp = new char[9];
            char[] box = new char[9];
            for (int j = 0; j < board[i].length; j++) {
                temp[j] = board[j][i];
                int a = (i / 3) * 3 + j / 3;
                int b = (i % 3) * 3 + j % 3;
                box[j] = board[a][b];
            }
            if (containsDuplicate(temp)) return false;
            if (containsDuplicate(box)) return false;
        }
        return true;
    }
    private static boolean containsDuplicate(char[] chars) {
        Set<Character> set = new HashSet<>();
        for (char aChar : chars) {
            if ('.' != aChar) {
                if (!set.add(aChar)) {
                    return true;
                }
            }
        }
        return false;
    }
}
