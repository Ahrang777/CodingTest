package sort.ch6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 입력
 * 5 3
 * 1 2 5 4 3 
 * 5 5 6 6 5
 * 
 * 출력
 * 26
 */
public class Ex12 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        int n = Integer.parseInt(stk.nextToken());  //배열의 원소 개수
        int k = Integer.parseInt(stk.nextToken());  //최대 교환 횟수

        //A 배열의 작은 값, B 배열의 큰 값 비교해서 A가 크면 교환X, B가 크면 교환
        Integer[] a = new Integer[n];
        stk = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.valueOf(stk.nextToken());
        }

        Integer[] b = new Integer[n];
        stk = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < n; i++) {
            b[i] = Integer.valueOf(stk.nextToken());
        }

        Arrays.sort(a);
        Arrays.sort(b, Collections.reverseOrder());

        for (int i = 0; i < k; i++) {
            if(a[i] < b[i]){
                int tmp = a[i];
                a[i] = b[i];
                b[i] = tmp;
            } else break;
        }

        long result = 0;
        for (int i = 0; i < n; i++) {
            result += a[i];
        }
        System.out.println(result);
    }
}
