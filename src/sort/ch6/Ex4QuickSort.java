package sort.ch6;

public class Ex4QuickSort {

    public static void quickSort(int[] arr, int start, int end){
        if(start>=end)  return;

        int pivot = start;
        int left = start + 1;
        int right = end;

        while(left<=right){
            while (left <= end && arr[left] <= arr[pivot]) left++;

            while (right > start && arr[right] >= arr[pivot]) right--;

            if (left > right) {
                int tmp = arr[pivot];
                arr[pivot] = arr[right];
                arr[right] = tmp;
            } else{
                int tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
            }
        }

        quickSort(arr, start, right-1);
        quickSort(arr, right + 1, end);
    }

    public static void main(String[] args) {
        int n = 12;
        int[] arr = {5, 7, 5, 9, 0, 3, 1, 6, 2, 4, 8, 5};

        quickSort(arr, 0, n-1);

        for(int x : arr){
            System.out.print(x + " ");
        }
    }
}
