package study.swexpertacademy.d3;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AWu1JmN6Js4DFASy&categoryId=AWu1JmN6Js4DFASy&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=5
 *
 * Rooted Binary Tree 재구성
 */
public class Swea7985 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int K = Integer.parseInt(br.readLine());
            int len = (int) Math.pow(2, K) - 1;
            List<Integer> list = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < len; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            List<Integer>[] binary = new ArrayList[K];
            for (int i = 0; i < K; i++) {
                binary[i] = new ArrayList<>();
            }

            binaryAdd(binary, list, 0);

            sb.append("#" + tc + " ");
            for (List<Integer> binList : binary) {
                for (Integer n : binList) {
                    sb.append(n + " ");
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);
        br.close();
    }

    public static void binaryAdd(List<Integer>[] binary, List<Integer> list, int depth) {
        if (list.size() == 1) {
            binary[depth].add(list.get(0));
            return;
        }

        int mid = list.size() / 2;
        binary[depth].add(list.get(mid));

        binaryAdd(binary, list.subList(0, mid), depth + 1); // left
        binaryAdd(binary, list.subList(mid + 1, list.size()), depth + 1);   //right
    }

    /*
    public static void com(int start, int end, int index, int[] num, int[] tmp) {
        int root = (start + end) /2;
        tmp[index] = num[root];

        if(start == end) {
            return ;
        }

        com(start, root-1, index*2, num, tmp);
        com(root+1, end, index*2+1, num, tmp);
        return ;
    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            ArrayList<ArrayList<Integer>> floor = new ArrayList<ArrayList<Integer>>();

            int k = Integer.parseInt(br.readLine());
            int n = (int)Math.pow(2, k)-1;
            int[] num = new int[n];
            int[] tmp = new int[n+1];

            String[] str = br.readLine().split(" ");
            for(int i=0; i<n; i++) {
                num[i] = Integer.parseInt(str[i]);
            }

            com(0, n-1, 1, num, tmp);

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(test_case).append(" ");
            int a=1;
            for(int i=0; i<k; i++) {

                for(int j=1; j<=(int)Math.pow(2, i); j++) {
                    sb.append(tmp[a++]).append(" ");
                }
                sb.append("\n");
            }
            System.out.print(sb);

        }
    }
     */
}
