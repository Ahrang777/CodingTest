package study.swexpertacademy.d3;

import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AXWXH_h695kDFAST&categoryId=AXWXH_h695kDFAST&categoryType=CODE&problemTitle=&orderBy=PASS_RATE&selectCodeLang=JAVA&select-1=3&pageSize=10&pageIndex=16
 *
 * 제곱수 만들기
 */
public class SWEA10965 {

    public static final int MAX = 10000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int[] arr = new int[MAX];
        int pos = 0;

        // false가 소수
        boolean[] prime = new boolean[MAX + 1];
        prime[0] = prime[1] = true;
        for (int i = 2; i * i <= MAX; i++) {
            if (!prime[i]) {
                arr[pos++] = i;
                for (int j = i * i; j <= MAX; j += i) {
                    prime[j] = true;
                }
            }
        }

        for (int tc = 1; tc <= T; tc++) {
            int A = Integer.parseInt(br.readLine());

            if (!prime[A]) {
                System.out.printf("#%d %d\n", tc, A);
                continue;
            }

            int index = 0;
            int B = 1;
            int cnt = 0;
            while (A > 1) {
                if (A % arr[index] == 0) {
                    cnt++;
                    A /= arr[index];
                }

                if (A % arr[index] != 0) {
                    if (cnt % 2 != 0) {
                        B *= arr[index];
                    }
                    if (!prime[A]) {
                        B *= A;
                        break;
                    }
                    index++;
                    if (index == pos) {
                        B *= A;
                        break;
                    }

                    cnt = 0;
                }
            }

            System.out.printf("#%d %d\n", tc, B);
        }
    }
}
