package dynamic_programming.ch16;

import java.io.*;
import java.util.*;

public class Ex5 {

    public static int n;
    public static int[] ugly = new int[1000];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());


        // 동적 할당으로 해결
        // 2배, 3배, 5배를 위한 인덱스
        //
        int i2 = 0, i3 = 0, i5 = 0;

        // 처음에 곱셈 값을 초기화
        int next2 = 2, next3 = 3, next5 = 5;

        ugly[0] = 1; // 첫 번째 못생긴 수는 1

        // 못생긴 수 에서 2, 3, 5를 곱한 값도 못생긴 수 
        // ugly로 크기 순으로 뽑힌 각 값들에 대해 2배, 3배, 5배 한 경우를 반영하기 위해 2, 3, 5배 idx를 1씩 증가하며 따로 계산
        
        // 1부터 n까지의 못생긴 수들을 찾기
        for (int l = 1; l < n; l++) {
            // 가능한 곱셈 결과 중에서 가장 작은 수를 선택
            ugly[l] = Math.min(next2, Math.min(next3, next5));

            // 인덱스에 따라서 곱셈 결과를 증가
            if (ugly[l] == next2) {
                i2 += 1;
                next2 = ugly[i2] * 2;
            }
            if (ugly[l] == next3) {
                i3 += 1;
                next3 = ugly[i3] * 3;
            }
            if (ugly[l] == next5) {
                i5 += 1;
                next5 = ugly[i5] * 5;
            }
        }

        // n번째 못생긴 수를 출력
        System.out.println(ugly[n - 1]);




        /*
        //PriorityQueue 로 해결
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int ans = 1;
        pq.offer(1);

        for (int i = 0; i < n; i++) {
            ans = pq.poll();
            if (!pq.contains(ans * 2)) {
                pq.offer(ans * 2);
            }
            if (!pq.contains(ans * 3)) {
                pq.offer(ans * 3);
            }
            if (!pq.contains(ans * 5)) {
                pq.offer(ans * 5);
            }
        }

        System.out.println(ans);*/
    }
}
