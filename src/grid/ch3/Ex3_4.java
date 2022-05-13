package grid.ch3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
입력
25 5

출력
2
 */
public class Ex3_4 {
    public static void main(String[] args) throws IOException {

//        long start = System.currentTimeMillis();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());
        int cnt = 0;

//        long start = System.currentTimeMillis();

        //시간이 오래걸린다. 최대한 아래처럼 식으로 한번에 횟수만 계산해서 더하는 식으로 시간을 단출가하자
//        while(n!=1){
//            if(n%k==0){
//                n/=k;
//            } else{
//                n-=1;
//            }
//            cnt++;
//        }

        while (true) {
            // N이 K로 나누어 떨어지는 수가 될 때까지만 1씩 빼기
            int target = (n / k) * k;
            cnt += (n - target);
            n = target;
            // N이 K보다 작을 때 (더 이상 나눌 수 없을 때) 반복문 탈출
            if (n < k) break;
            // K로 나누기
            cnt += 1;
            n /= k;
        }

        cnt += (n - 1);

        System.out.println(cnt);
        /*long end = System.currentTimeMillis();

        System.out.println(end - start);*/
    }
}
