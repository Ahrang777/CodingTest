package grid.ch3;

import java.io.IOException;
import java.util.Scanner;

/**
 * 3 3
 * 3 1 2
 * 4 1 4
 * 2 2 2
 */

public class Ex3_3 {
    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);

        // N, M을 공백을 기준으로 구분하여 입력 받기
        int n = sc.nextInt();
        int m = sc.nextInt();
        int result = 0;

        // 한 줄씩 입력 받아 확인하기
        for (int i = 0; i < n; i++) {
            // 현재 줄에서 '가장 작은 수' 찾기
            int min_value = 10001;
            for (int j = 0; j < m; j++) {
                int x = sc.nextInt();
                min_value = Math.min(min_value, x);
            }
            // '가장 작은 수'들 중에서 가장 큰 수 찾기
            result = Math.max(result, min_value);
        }

        System.out.println(result); // 최종 답안 출력
        long end = System.currentTimeMillis();
        System.out.println(end - start);

//        long start = System.currentTimeMillis();

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
//
//        int n = Integer.parseInt(stk.nextToken());
//        int m = Integer.parseInt(stk.nextToken());
//
//        int[][] arr = new int[n][m];
//
//        for(int i=0;i<n;i++){
//            stk = new StringTokenizer(br.readLine(), " ");
//            for(int j=0;j<m;j++){
//                arr[i][j] = Integer.parseInt(stk.nextToken());
//            }
//        }
    }
}
