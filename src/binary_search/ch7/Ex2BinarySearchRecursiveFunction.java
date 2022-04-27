package binary_search.ch7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 입력
 * 10 7
 * 1 3 5 7 9 11 13 15 17 19
 *
 * 출력
 * 4
 */
public class Ex2BinarySearchRecursiveFunction {

    public static int binarySearch(int[] arr, int target, int start, int end){
        if(start > end) return -1;  //중간점의 값이랑 다르면 시작점,끝점 계속 이동한다. start==end 인 상황(1개 남은 상황)까지 값 비교를 해야한다.
        int mid = (start+end)/2;

        if(arr[mid] == target)  return mid;
        else if (arr[mid] > target) return binarySearch(arr, target, start, mid - 1);
        else return binarySearch(arr, target, mid + 1, end);
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
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        int result = binarySearch(arr, target, 0, n - 1);
        if (result == -1) {
            System.out.println("원소가 존재하지 않습니다.");
        } else{
            System.out.println(result + 1);
        }
    }
}
