package sort.ch14;

import java.util.*;
import java.io.*;

/*
입력
3
10
20
40

출력
100
 */

/**
 * https://www.acmicpc.net/problem/1715
 * 
 * 카드 정렬하기
 */
public class Ex4 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            pq.offer(Integer.parseInt(bf.readLine()));
        }

        int result = 0;

        while (pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();

            int sum = first + second;
            result += sum;
            pq.offer(sum);
        }

        System.out.println(result);
    }
}
