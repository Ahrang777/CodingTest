package implementation.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ex2 {

    public static boolean check(int h, int m, int s){
        if(h%10 == 3 || m/10==3 || m%10 == 3 || s/10==3 || s%10==3)
            return true;
        return false;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        int n = Integer.parseInt(stk.nextToken());
        int cnt = 0;

        //00시 00분 00초 ~ N시 59분 59초
        for (int h = 0; h <= n; h++) {
            for (int m = 0; m < 60; m++) {
                for (int s = 0; s < 60; s++) {
                    if(check(h, m, s)){
                        cnt++;
                    }
                }
            }
        }

        System.out.println("cnt = " + cnt);
    }
}
