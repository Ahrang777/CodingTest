package binary_search.ch7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
입력
4 6
19 15 10 17

출력
15
*/
public class Ex8 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        int n = Integer.parseInt(stk.nextToken());  //떡 개수
        int m = Integer.parseInt(stk.nextToken());  //요청한 떡 길이

        int[] arr = new int[n];
        stk = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        int start = 0;
        int end = (int)1e9;
        int result = 0;

        while (start <= end) {
            long total = 0;
            int mid = (start + end) / 2;
            for (int i = 0; i < n; i++) {
                if(arr[i] > mid) total += arr[i] - mid;
            }

            if (total < m) {
                end = mid - 1;
            } else{
                result = mid;
                start = mid + 1;
            }
        }

        System.out.println(result);
    }
}
