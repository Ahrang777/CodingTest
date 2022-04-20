package grid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex3_4 {
    public static void main(String[] args) throws IOException {

//        long start = System.currentTimeMillis();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());
        int cnt = 0;

        long start = System.currentTimeMillis();

//        while(n!=1){
//            if(n%k==0){
//                n/=k;
//            } else{
//                n-=1;
//            }
//            cnt++;
//        }

        while(true){
            int target = (n/k)*k;
            cnt = n - target;
            n=target;

            if(n<k) break;
            cnt+=1;
            n/=k;
        }

        cnt += (n - 1);

        System.out.println(cnt);
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}
