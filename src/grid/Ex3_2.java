package grid;

import java.util.Arrays;
import java.util.Scanner;

public class Ex3_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();   //배열 크기
        int m = sc.nextInt();   //더해지는 횟수
        int k = sc.nextInt();   //최대 반복

        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }

        long start = System.currentTimeMillis();

        Arrays.sort(arr);
        //가장 큰수 k번 더하고 두번째 큰수 1번 더하고 반복

        int first = arr[n-1];
        int second = arr[n-2];
        int cnt = m / (k+1);    //2번째 수 더해지는 횟수

        int result = first * (m-cnt) + second * cnt;

        // m=8, k=2   11/2/11/2/11

        /*for(int i=0;i<m;i++){
            if(i % k == 0){
                result += first;
            } else
                result += second;
        }*/
        long end = System.currentTimeMillis();
        System.out.println(result);
        System.out.println(end-start);
    }
}
