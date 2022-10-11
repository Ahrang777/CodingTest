package study.swexpertacademy.d2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV5PR4DKAG0DFAUq&categoryId=AV5PR4DKAG0DFAUq&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=2&pageSize=10&pageIndex=1
 *
 * SW Expert Academy
 * Base64 Decoder
 * 
 * 다시풀기
 */
public class SWEA1928 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            String encode = br.readLine();
            String decode = new String(Base64.getDecoder().decode(encode));
            System.out.printf("#%d %s\n", tc, decode);
        }
    }

    /**
     * 문제 이해
     * 24bit >> 8bit x 3 >> 6bit씩 잘라서 표에 있는 문자로 변경
     * 인코딩 : 8bit 3글자 -> 6bit 4글자
     * 디코딩 : 6bit 4글자 -> 8bit 3글자
     */
}
