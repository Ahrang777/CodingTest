package study.swexpertacademy.d3;

import java.io.*;
import java.util.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV2b-QGqADMBBASw&categoryId=AV2b-QGqADMBBASw&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=3&pageSize=10&pageIndex=2
 *
 * 수의 새로운 연산
 * 다시풀기
 * 참고 https://zzang9ha.tistory.com/262
 */
public class Swea1493 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int res = 0;

            // 첫번째 값에 대한 x, y 좌표
            int sum1 = 0;
            int x1 = 0;
            int y1 = 0;

            for(int i=1; ; i++) {
                sum1 += i;
                if(sum1 >= p) {
                    x1 = i - (sum1-p);
                    y1 = (i+1) - x1;
                    break;
                }
            }

            /*
            (3, 2) = 9 >> 4번째 대각선에 위치
            1번째 대각선에 1개
            2번째 대각선에 2개
            3번째 대각선에 3개
            4번째 대각선에 4개
            1 + 2 + 3 = 6
            1 + 2 + 3 + 4 = 10 >> 7 ~ 10 은 4번째 대각선
            
            n번째 대각선의 
            1번째 위치의 x = 1
            2번째 위치의 x = 2
            3번째 위치의 x = 3
            ...
            n번째 위치의 x = n >> 현재 대각선의 가장 마지막 좌표
            
            x = 현재 대각선 번호 - (sum - p)
            x + y = i + 1
            y = i + 1 - x
             */

            // 두번째 값에 대한  x, y 좌표
            int sum2 = 0;
            int x2 = 0;
            int y2 = 0;
            for(int i=1; ; i++) {
                sum2 += i;
                if(sum2 >= q) {
                    x2 = i - (sum2-q);
                    y2 = (i+1) - x2;
                    break;
                }
            }

            // 최종 x, y 좌표
            /*
            (1,1) = 1
            (1,2) = 2   // 이전 숫자에서 1증가
            (1,3) = 4   // 이전 숫자에서 2증가
            (1,4) = 7   // 이전 숫자에서 3증가
            (1,5) = 11  // 이전 숫자에서 4증가

            13(3,3) >> 11(1,5) >> x + y = 6

            i = 1 ~ i < x + y - 1   까지 i 를 더해가며 좌측꼭대기 번호(sum) 찾기
            좌측 꼭대기 번호를 찾으면 해당 번호 기준 x - 1 뒤가 결과값
             */
            int x3 = x1 + x2;
            int y3 = y1 + y2;
            int sum3 = 1;
            for(int i=1; i<(x3+y3)-1; i++) {
                sum3 += i;
            }
            res = sum3 + (x3-1);
            sb.append("#" + tc + " " + res + "\n");
        }
        System.out.println(sb);
    }
}
