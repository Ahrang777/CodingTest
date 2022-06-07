package binary_search.ch15;

import java.util.*;
import java.io.*;

/*
입력
5
-15 -6 1 3 7
출력
3
================
입력
7
-15 -4 2 8 9 13 15
출력
2
================
입력
7
-15 -4 3 8 9 13 15
출력
-1
 */
public class Ex2 {

    // 이진 탐색 소스코드 구현(재귀 함수)
    public static int binarySearch(int[] arr, int start, int end) {
        if (start > end) return -1;
        int mid = (start + end) / 2;
        // 고정점을 찾은 경우 중간점 인덱스 반환
        if (arr[mid] == mid) return mid;
            // 중간점의 값보다 중간점이 작은 경우 왼쪽 확인
        else if (arr[mid] > mid) return binarySearch(arr, start, mid - 1);
            // 중간점의 값보다 중간점이 큰 경우 오른쪽 확인
        else return binarySearch(arr, mid + 1, end);
    }
    
    // 반복문으로 구현
    public static int fixSearch(int[] arr, int start, int end) {
        int result = -1;
        while (start <= end) {
            int mid = (start + end) / 2;

            if (arr[mid] == mid) {
                result = mid;
                break;
            }
            // 중간점의 값보다 중간점이 작은 경우 왼쪽 확인
            // 오른쪽 값은 중간점의 값 보다 큰데 인덱스는 1씩 증가, 절대 값아질 수 없고 값이 중간점보다 무조건 크다
            // 때문에 왼쪽에서 탐색
            else if (arr[mid] > mid) {
                end = mid - 1;
            }
            // 중간점의 값보다 중간점이 큰 경우 오른쪽 확인
            // 왼쪽 값은 중간점의 값보다 작은데 인덱스는 1씩 감소, 무조건 중간점의 값이 중간점보다 작다
            else {
                start = mid + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        int[] arr = new int[n];
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        System.out.println(fixSearch(arr, 0, n - 1));
    }
}
