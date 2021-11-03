package sort;

public class SortTest {

    public static void main(String[] args) {
        int[] arr = {8,4,6,2,9,7,1,3,5};

        printArray(arr);
        System.out.println("==============================================");
        insertSort(arr);

    }


    public static void insertSort(int[] a){
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];

            int j = i-1;
            for(; j>=0;j--){
                if(temp < a[j]) a[j+1] = a[j];
                else break;
            }
            a[j+1] = temp;
            printArray(a);
        }
    }


    public static void printArray(int[] a){
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println("\n");
    }
}
