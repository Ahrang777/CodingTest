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
    //이진탐색 재귀로 해결
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

    //이진탐색 반복문으로 해결
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

    //target의 범위의 첫번째 위치
    public static int lowerBound(int[] arr, int target, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;

            // target 과 같은 것 중 가장 왼쪽 값 찾기 >> 따라서 target이랑 같을때 왼쪽으로 이동하기 위해 end = mid
            // 만약 target과 같고 현재 mid 위치가 값이 target인 값들 중 가장 왼쪽인데 end = mid - 1; 이 되버리면 문제?
            if(arr[mid] >= target) end = mid;
            else start = mid + 1;
        }
        return end;
    }

    //target의 범위의 마지막 위치 + 1
    public static int upperBound(int[] arr, int target, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;

            // target 과 같은 것 중 가장 오른쪽 값 찾기 >> 따라서 target이랑 같을때 오른쪽으로 이동하기 위해 start = mid + 1
            // target의 범위 마지막 + 1 이기때문에 end = mid - 1 안하고 딱 그위치까지?
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
//         BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");
//
//         int n = Integer.parseInt(stk.nextToken());  //원소 개수
//         int target = Integer.parseInt(stk.nextToken()); //찾고자 하는 값
//
//         int[] arr = new int[n];
//         stk = new StringTokenizer(bf.readLine(), " ");
//         for (int i = 0; i < n; i++) {
//             arr[i] = Integer.parseInt(stk.nextToken());
//         }
//
//         Arrays.sort(arr);
//         /*for (int i : arr) {
//             System.out.print(i + " ");
//         }
//         System.out.println();*/
//
// //        int result = binarySearch(arr, target, 0, n - 1);
//         int result = binarySearch1(arr, target, 0, n - 1);
//         if (result == -1) {
//             System.out.println("원소가 존재하지 않습니다.");
//         } else{
//             System.out.println(result + 1);
//         }

        int[] numbers = {1, 5, 2, 2, 4, 7, 7, 6, 6, 6, 6, 9, 9, 8};
        Arrays.sort(numbers);
        for (int number : numbers) {
            System.out.print(number + " ");
        }
        System.out.println();
        System.out.println(countByRange(numbers, 6, 9));
    }
}
