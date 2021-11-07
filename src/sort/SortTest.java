package sort;

import java.util.Arrays;

public class SortTest {

    public static void main(String[] args) {
        int[] arr = {8, 4, 6, 2, 9, 7, 1, 3, 5};
        int[] arr1 = {5, 8, 9, 10, 3, 6, 7};
        int[] arr2 = {5, 8};

        printArray(arr1);
        System.out.println("==============================================");
//        dualPivotQuickSort(arr1, 0, arr1.length - 1);
        heapSort(arr1);
        printArray(arr1);

    }


    /**
     * 堆排序
     *
     * @param a
     */
    public static void heapSort(int[] a) {
        for (int end = a.length - 1; end > 0; end--) {
            //最后一个带有叶子节点的父节点坐标 （start+end）/2 向上取整 减一
            int lastFarther = end % 2 != 0 ? end / 2 : end / 2 - 1;

            for (int father = lastFarther; father >= 0; father--) {
                //取左节点：2*n +1
                int left = father * 2 + 1;
                //取右节点：2*n +2
                int right = father * 2 + 2;
                if (right <= end && a[right] > a[father]) {
                    swap(a, right, father);
                }
                if (a[left] > a[father]) {
                    swap(a, left, father);
                }
            }
            swap(a, 0, end);
        }
    }


    /**
     * 双轴快速排序
     *
     * @param a
     * @param start
     * @param end
     */
    public static void dualPivotQuickSort(int[] a, int start, int end) {
        if (start > end) return;
        if (a[start] > a[end]) {
            swap(a, start, end);
        }
        int pivot1 = a[start];
        int pivot2 = a[end];

        int left = start;
        int right = end;
        int k = start + 1;

        OUT_LOOP:
        //定义外层循环的名称，可方便直接跳出外层循环
        while (k < right) {
            if (a[k] < pivot1) {
                //轴的位置不变，扩大左轴区域大小
                ++left;
                swap(a, left, k);
                k++;
            } else if (a[k] < pivot2) {
                //位置处于两个轴中间
                k++;
            } else {
                //右轴位置不变，扩大右轴区域大小，所以要一直找到比右轴小的数
                while (a[--right] > pivot2) {
                    if (right <= k) break OUT_LOOP;
                }
                swap(a, right, k);

                //第二种处理，如果a[right]比pivot1小，就多交换一次 省去一次循环
//                if (a[right] >= pivot1 && a[right] <= pivot2) {
//                    swap(a, right, k);
//                    k++;
//                } else {
//                    ++left;
//                    swap(a, right, k);
//                    swap(a, left, k);
//                    k++;
//                }
            }
        }
        swap(a, start, left);  //分别移动两个轴的位置
        swap(a, right, end);
        printArray(a);

        dualPivotQuickSort(a, start, left - 1);
        dualPivotQuickSort(a, left + 1, right - 1);
        dualPivotQuickSort(a, right + 1, end);
    }

    /**
     * 快速排序  O(n * log n)
     *
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
        quickSort(a, start, left - 1);
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
        if (i == j) return;
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
