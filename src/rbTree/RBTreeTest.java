package rbTree;

import java.util.Scanner;

public class RBTreeTest {
    public static void main(String[] args) {
        delete();

    }


    public static void insert() {
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


    public static void delete() {
        RBTree<String, Object> rbt = new RBTree();
        for (int i = 0; i < 9; i++) {
            rbt.insert("0" + (i + 1), null);
        }
        TreeOperation.show(rbt.getRoot());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("请输入删除key: ");
            String key = scanner.next();
            System.out.println();
            rbt.delete("0" + key);
            // 调用 红黑树控制台打印类
            TreeOperation.show(rbt.getRoot());
        }
    }
}

