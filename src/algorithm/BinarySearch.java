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




    // key이상인 값의 처음 위치 
    // 따라서 key <= arr[mid] 의 경우 arr[mid]가 key와 같을 수도 클 수도 있으니까 hi 를 mid 자리까지만 가야한다. 
    // hi = mid - 1 가 아니라 hi = mid 왜나하면 그 자리가 가장 앞일 수 있다. 
    // key > arr[mid] 인 경우 고려할 필요없이 lo = mid + 1 >> key 이상인 첫 위치를 구해야 하므로
    private static int lowerBound(int[] arr, int key) {
        int lo = 0;
        int hi = arr.length;

        // lo가 hi랑 같아질 때 까지 반복
        while (lo < hi) {

            int mid = (lo + hi) / 2; // 중간위치를 구한다.

            /*
             *  key 값이 중간 위치의 값보다 작거나 같을 경우
             *
             *  (중복 원소에 대해 왼쪽으로 탐색하도록 상계를 내린다.)
             */
            if (key <= arr[mid]) {
                hi = mid;
            }

            else {
                lo = mid + 1;
            }

        }
        return lo;
    }

    // key 를 초과하는 처음 위치
    // arr[mid]가 key 를 초과해야 하므로 arr[mid] <= key 인 경우는 lo = mid + 1
    // key < arr[mid] 인 경우 현재 위치가 답일 수 있으니 hi = mid로 이동한다. (hi = mid - 1 아님)
    private static int upperBound(int[] arr, int key) {
        int lo = 0;
        int hi = arr.length;

        // lo가 hi랑 같아질 때 까지 반복
        while (lo < hi) {

            int mid = (lo + hi) / 2; // 중간위치를 구한다.

            // key값이 중간 위치의 값보다 작을 경우
            if (key < arr[mid]) {
                hi = mid;
            }
            // 중복원소의 경우 else에서 처리된다.
            else {
                lo = mid + 1;
            }
        }
        return lo;
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
