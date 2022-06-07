package binary_search.ch15;

import java.util.*;
import java.io.*;

/*
입력
5 3
1
2
8
4
9

출력
3
 */

/**
 * https://www.acmicpc.net/problem/2110
 *
 * 공유기 설치
 */
public class Ex3 {


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");
        int n = Integer.parseInt(stk.nextToken());
        int c = Integer.parseInt(stk.nextToken());

        ArrayList<Integer> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(bf.readLine()));
        }

        Collections.sort(arr);

        int start = 1; // 가능한 최소 거리(min gap)
        int end = arr.get(n - 1) - arr.get(0); // 가능한 최대 거리(max gap)
        int result = 0;

        while (start <= end) {
            // mid는 가장 인접한 두 공유기 사이의 거리(gap)을 의미
            int mid = (start + end) / 2;
            // 첫째 집에는 무조건 공유기를 설치한다고 가정
            int value = arr.get(0);
            int cnt = 1;
            // 현재의 mid 값을 이용해 공유기를 설치하기
            for (int i = 1; i < n; i++) { // 앞에서부터 차근차근 설치
                if (arr.get(i) >= value + mid) {
                    value = arr.get(i);
                    cnt += 1;
                }
            }
            // C개 이상의 공유기를 설치할 수 있는 경우, 거리를 증가시키기
            // C개 이상 공유기를 설치 할 수 있다면 gap을 키울 여유가 있는것
            if (cnt >= c) {
                start = mid + 1;
                //마지막에 저장된 result가 최적의 gap
                result = mid; // 최적의 결과를 저장
            }
            // C개 이상의 공유기를 설치할 수 없는 경우, 거리를 감소시키기
            // gap 줄이기
            else {
                end = mid - 1;
            }
        }
        System.out.println(result);
    }
}
