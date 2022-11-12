package study.swexpertacademy.d3;

import java.io.*;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AW8Wj7cqbY0DFAXN&categoryId=AW8Wj7cqbY0DFAXN&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=5
 *
 * 한빈이와 Spot Mart
 */
public class Swea9229 {
    static int N, M;
    static int[] arr;
    static boolean[] visited;
    static int max;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new int[N];
            visited = new boolean[N];
            max = -1;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0, 0);

            sb.append("#" + tc + " " + max + "\n");
        }

        System.out.println(sb);
        br.close();
    }

    public static void dfs(int depth, int start, int sum) {
        if (sum > M) {
            return;
        }
        if (depth == 2) {
            if (sum > max) {
                max = sum;
            }
            return;
        }

        for (int i = start; i < arr.length; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                dfs(depth + 1, i + 1, sum + arr[i]);
                visited[i] = false;
            }
        }
    }

    /*
    // 이진탐색
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();

        for (int i = 1; i <= count; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int max = Integer.parseInt(st.nextToken());

            int[] arr = new int[size];

            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<size; j++){
                arr[j] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            int s = 0;
            int e = arr.length-1;

            int sum = arr[s] + arr[e];
            int maxValue = -1;

            while(s != e){
               if (sum > max){
                    sum -= arr[e];
                    e--;
                    sum += arr[e];
                } else if (sum <= max) {
                    maxValue = Math.max(maxValue, sum);
                    sum -= arr[s];
                    s++;
                    sum += arr[s];
                }
            }
            sb.append("#").append(i).append(" ").append(maxValue).append("\n");
        }
        System.out.println(sb.toString());
    }
     */
}
