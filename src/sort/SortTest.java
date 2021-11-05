package sort;

import java.util.Arrays;

public class SortTest {

    public static void main(String[] args) {
        int[] arr = {8, 4, 6, 2, 9, 7, 1, 3, 5};
        int[] arr1 = {5, 8, 9, 10, 3, 6, 7};
        int[] arr2 = {5, 8};

        printArray(arr1);
        System.out.println("==============================================");
        quickSort(arr1, 0, arr1.length - 1);
        printArray(arr1);

    }


    /**
     * 快速排序  O(n * log n)
     * @param a
     * @param start
     * @param end
     */
    public static void quickSort(int[] a, int start, int end) {
        if (start >= end) return;
        int pivot = a[end];
        int left = start;
        int right = end - 1;

        while (left <= right) {
            //从左往右找第一个比轴大的数
            while (left <= right && a[left] <= pivot) left++;
            //从右往左找第一个比轴小的数
            while (left <= right && a[right] > pivot) right--;

            //交换位置
            if (left < right) swap(a, left, right);
        }
        //将轴移动到中间
        swap(a, left, end);
        quickSort(a, start, left-1);
        quickSort(a, left + 1, end);
    }




    /**
     * 归并排序
     *
     * @param a
     * @param start
     * @param end
     */
    public static void mergeSort(int[] a, int start, int end) {
        if (start == end) return;
        //分两半
        int mid = start + (end - start) / 2;
        //左边排序
        mergeSort(a, start, mid);
        //右边排序
        mergeSort(a, mid + 1, end);
        //合并
        merge(a, start, mid + 1, end);
    }

    public static void merge(int[] a, int left, int right, int bound) {
        int[] temp = new int[bound - left + 1];
        int i = left;
        int j = right;
        int k = 0;
        while (i < right && j <= bound) {
            temp[k++] = a[i] < a[j] ? a[i++] : a[j++];
        }
        while (i < right) temp[k++] = a[i++];
        while (j <= bound) temp[k++] = a[j++];

        for (int i1 = 0; i1 < temp.length; i1++) {
            a[i1 + left] = temp[i1];
        }
    }

    /**
     * 希尔排序
     *
     * @param a
     */
    public static void shellSort(int[] a) {
        int h = 1;
        while (h <= a.length / 3) {
            h = h * 3 + 1;
        }
        for (int gap = h; gap > 0; gap = (gap - 1) / 3) {
            for (int i = gap; i < a.length; i++) {
                for (int j = i; j > gap - 1; j = j - gap) {
                    if (a[j] < a[j - gap]) swap(a, j, j - gap);
                }
            }
            printArray(a);
        }
    }

    /**
     * 优化的插入排序
     *
     * @param a
     */
    public static void insertSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];

            int j = i - 1;
            for (; j >= 0; j--) {
                if (temp < a[j]) a[j + 1] = a[j];
                else break;
            }
            a[j + 1] = temp;
            printArray(a);
        }
    }


    public static void swap(int[] a, int i, int j) {
        if(i == j ) return;
        a[i] = a[i] ^ a[j];
        a[j] = a[i] ^ a[j];
        a[i] = a[i] ^ a[j];
    }

    public static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("\n");
    }
}
