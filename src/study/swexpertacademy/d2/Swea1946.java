package study.swexpertacademy.d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV5PmkDKAOMDFAUq&categoryId=AV5PmkDKAOMDFAUq&categoryType=CODE&problemTitle=&orderBy=PASS_RATE&selectCodeLang=JAVA&select-1=2&pageSize=10&pageIndex=3
 *
 * 간단한 압축 풀기
 */
public class Swea1946 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            String[][] arr = new String[N][2];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i][0] = st.nextToken();
                arr[i][1] = st.nextToken();
            }

            int cnt = 0;
            System.out.println("#" + tc);
            for (String[] s : arr) {
                int n = Integer.parseInt(s[1]);
                for (int i = 0; i < n; i++) {
                    if (cnt >= 10) {
                        System.out.println();
                        cnt = 0;
                    }
                    System.out.print(s[0]);
                    cnt++;
                }
            }

            System.out.println();
        }
    }
}
