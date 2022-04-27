package binary_search.ch7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 입력
 * 5
 * 8 3 7 9 2
 * 3
 * 5 7 9
 * 
 * 출력
 * no yes yes 
 */
public class Ex5 {

    public static int binarySearch(int[] arr, int target, int start, int end){

        while (start <= end) {
            int mid = (start + end) / 2;

            if(arr[mid] == target)  return mid;
            else if(arr[mid] > target) end = mid - 1;
            else start = mid + 1;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        int[] arr = new int[n];
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        int m = Integer.parseInt(bf.readLine());
        int[] targets = new int[m];

        stk = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < m; i++) {
            targets[i] = Integer.parseInt(stk.nextToken());
        }

        Arrays.sort(arr);
        for (int i = 0; i < m; i++) {
            int result = binarySearch(arr, targets[i], 0, n - 1);
            if (result != -1) {
                System.out.print("yes ");
            } else {
                System.out.print("no ");
            }
        }
    }
}
