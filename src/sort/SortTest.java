package sort;

public class SortTest {

    public static void main(String[] args) {
        int[] arr = {8, 4, 6, 2, 9, 7, 1, 3, 5};
        int[] arr1 = {5, 8, 9, 10, 3, 6, 7};

        printArray(arr);
        System.out.println("==============================================");
        mergeSort(arr, 0, arr.length - 1);
        printArray(arr);

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
