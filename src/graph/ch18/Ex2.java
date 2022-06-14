package graph.ch18;

import java.io.*;
import java.util.*;

/*
입력
4
3
4
1
1

출력
2
====
입력
4
6
2
2
3
3
4
4

출력
3
 */
public class Ex2 {

    public static int g, p;
    public static int[] parent = new int[100001]; // 부모 테이블 초기화

    public static int findRoot(int x) {
        if (x == parent[x]) return x;
        return parent[x] = findRoot(parent[x]);
    }

    public static void union(int a, int b) {
        a = findRoot(a);
        b = findRoot(b);

        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        g = Integer.parseInt(bf.readLine());
        p = Integer.parseInt(bf.readLine());

        for (int i = 1; i <= g; i++) {
            parent[i] = i;
        }

        int result = 0;
        for (int i = 0; i < p; i++) {
            int x = Integer.parseInt(bf.readLine());
            int root = findRoot(x); // 현재 비행기의 탑승구의 루트 확인
            if (root == 0) break;   // 현재 루트가 0이라면, 종료
            union(root, root - 1);  // 그렇지 않다면 바로 왼쪽의 집합과 합치기
            result += 1;
        }

        System.out.println(result);
    }
}
