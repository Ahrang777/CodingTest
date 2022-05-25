package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
입력
10 7
1 3 5 7 9 11 13 15 17 19

출력
4
 */
public class BinarySearch {

    //target의 인덱스 찾기
    //start: 시작 인덱스, end: 끝 인덱스, target: 찾는 값
    public static int binarySearch(int[] arr, int target, int start, int end) {
        if (start > end) {
            return -1;
        }
        
        int mid = (start + end) / 2;

        if (target == arr[mid]) {
            return mid;
        } else if (target > arr[mid]) {
            return binarySearch(arr, target, mid + 1, end);
        } else {
            return binarySearch(arr, target, start, mid - 1);
        }
    }

    public static int binarySearch1(int[] arr, int target, int start, int end) {
        while (start <= end) {
            int mid = (start + end) / 2;

            if (target == arr[mid]) {
                return mid;
            } else if (target > arr[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        int n = Integer.parseInt(stk.nextToken());  //원소 개수
        int target = Integer.parseInt(stk.nextToken()); //찾고자 하는 값

        int[] arr = new int[n];
        stk = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        Arrays.sort(arr);
        /*for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();*/

//        int result = binarySearch(arr, target, 0, n - 1);
        int result = binarySearch1(arr, target, 0, n - 1);
        if (result == -1) {
            System.out.println("원소가 존재하지 않습니다.");
        } else{
            System.out.println(result + 1);
        }
    }
}
