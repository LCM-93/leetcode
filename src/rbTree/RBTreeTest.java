package rbTree;

import java.util.Scanner;

public class RBTreeTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        RBTree<Integer, Object> rbt = new RBTree();

        while (true) {
            System.out.println("请输入key: ");
            int key = scanner.nextInt();
            System.out.println();
            rbt.insert(Integer.valueOf(key), null);

            // 调用 红黑树控制台打印类
            TreeOperation.show(rbt.getRoot());
        }
    }
}

