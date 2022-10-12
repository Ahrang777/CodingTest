package study.swexpertacademy.d2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV5P1kNKAl8DFAUq&categoryId=AV5P1kNKAl8DFAUq&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=2&pageSize=10&pageIndex=1
 *
 * 패턴 마디의 길이
 */
public class SWEA2007 {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            String str = br.readLine();
            int cnt = 1;
            cnt = check(str.substring(0, 1), str.substring(1), cnt);
            System.out.printf("#%d %d\n", tc, cnt);
        }
    }

    public static int check(String pre, String post, int cnt) {
        if (post.startsWith(pre))	return cnt;
        return check(pre + post.substring(0, 1), post.substring(1), cnt+1);
    }
}
