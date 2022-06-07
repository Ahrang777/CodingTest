package binary_search.ch15;

import algorithm.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
입력
7 2
1 1 2 2 2 2 3

출력
4
===========
입력
7 4
1 1 2 2 2 2 3

출력
-1
 */
public class Ex1 {

    //target의 범위의 첫번째 위치
    public static int lowerBound(int[] arr, int target, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;
            if(arr[mid] >= target) end = mid;
            else start = mid + 1;
        }
        return end;
    }

    //target의 범위의 마지막 위치 + 1
    public static int upperBound(int[] arr, int target, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] > target) end = mid;
            else start = mid + 1;
        }
        return end;
    }

    // 값이 [left_value, right_value]인 데이터의 개수를 반환하는 함수
    public static int countByRange(int[] arr, int leftValue, int rightValue) {
        // 유의: lowerBound와 upperBound는 end 변수의 값을 배열의 길이로 설정
        int rightIndex = upperBound(arr, rightValue, 0, arr.length);
        int leftIndex = lowerBound(arr, leftValue, 0, arr.length);
        return rightIndex - leftIndex;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");
        int n = Integer.parseInt(stk.nextToken());
        int x = Integer.parseInt(stk.nextToken());

        int[] arr = new int[n];
        stk = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        int cnt = countByRange(arr, x, x);

        if (cnt == 0) {
            System.out.println(-1);
        } else {
            System.out.println(cnt);
        }
    }
}
