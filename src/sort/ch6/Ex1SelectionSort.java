package sort.ch6;

public class Ex1SelectionSort {

    public static void main(String[] args) {
        int n = 10;
        int[] arr = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};

        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if(arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }

            int tmp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = tmp;
        }

        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
