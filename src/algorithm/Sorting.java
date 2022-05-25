package algorithm;

public class Sorting {

    //선택 정렬
    //O(N²)
    public static void selectionSort(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }

            int tmp = arr[min];
            arr[min] = arr[i];
            arr[i] = tmp;
        }

        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    //삽입 정렬
    //O(N²), 최선: O(N)
    public static void insertionSort(int[] arr, int n) {
        for (int i = 1; i < n; i++) {

            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > arr[j]) {
                    int tmp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tmp;
                } else {
                    break;
                }
            }
        }

        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    //퀵 정렬
    //O(NlogN), 최악의 경우 O(N²)
    //start: 시작 인덱스, end: 마지막 인덱스
    public static void quickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }

        int pivot = start;
        int left = start + 1;
        int right = end;

        while (left <= right) {
            while (left <= end && arr[pivot] >= arr[left]) left++;
            while (right > start && arr[pivot] <= arr[right]) right--;

            if (left > right) {
                int tmp = arr[pivot];
                arr[pivot] = arr[right];
                arr[right] = tmp;
            } else {
                int tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
            }
        }

        quickSort(arr, start, right-1);
        quickSort(arr, right + 1, end);
    }

    //계수 정렬
    //O(N+K), 데이터 개수: N, 최대값: K
    public static void countSort(int[] arr, int max) {
        int[] count = new int[max + 1];

        for (int n : arr) {
            count[n]++;
        }

        for (int i = 0; i <= max; i++) {

            for (int j = 0; j < count[i]; j++) {
                System.out.print(i + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8, 5};
        int n = arr.length;
        final int max = 9;

//        selectionSort(arr, n);

//        insertionSort(arr, n);

        /*quickSort(arr, 0, n - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }*/

        countSort(arr, max);
    }
}
