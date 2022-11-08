package study.swexpertacademy.d3;

import java.util.*;
import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AWgv9va6HnkDFAW0&categoryId=AWgv9va6HnkDFAW0&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=3
 *
 * 규영이와 인영이의 카드게임
 */
public class Swea6808 {
    static List<Integer> gList;
    static List<Integer> aList;
    static int win;
    static int lose;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            gList = new ArrayList<>();
            aList = new ArrayList<>();
            boolean[] visited = new boolean[9];
            int total = 1;
            win = 0;
            lose = 0;
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < 9; i++) {
                gList.add(Integer.parseInt(st.nextToken()));
            }

            for (int i = 1; i <= 18; i++) {
                if (gList.contains(i)) continue;
                aList.add(i);
            }

            dfs(0, 0, visited);

            for (int i = 1; i <= 9; i++) {
                total *= i;
            }

            sb.append("#" + tc + " " + (total - lose) + " " + lose + "\n");
        }

        System.out.println(sb);
        br.close();
    }

    public static void dfs(int result, int depth, boolean[] visited) {
        // 1 ~ 18 까지 합은 171로 만약 규영이가 모든 라운드를 이겼다면 171점을 획득한다. >> 85, 86 이 절반인데 86점만 규영이가 먼저 획득하면 승리는 확정
        if (result >= 86) {
            win++;
            return;
        }
        // lose만 9개 숫자 모두 확인한 값이니까 최종적으로 승수도 전체경우의 수 - lose 가 된다.
        // if (result >= 86)은 끝까지 확인 안해도 승리조건 만족하면 넘기고 빠르게 lose 횟수를 구할수 있도록 한것, 때문에 모든 승리 횟수를 카운트 하지 못했다.
        if (depth == 9) {
            lose++;
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                if (gList.get(depth) > aList.get(i)) {
                    dfs(result + gList.get(depth) + aList.get(i), depth + 1, visited);
                } else if (gList.get(depth) < aList.get(i)) {
                    dfs(result, depth + 1, visited);
                }
                visited[i] = false;
            }
        }
    }

    /*static int[] aArr;
    static boolean[] visited;
    static int win, lose;
    static List<Integer> gList;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            aArr = new int[9];
            gList = new ArrayList<>();
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < 9; i++) {
                gList.add(Integer.parseInt(st.nextToken()));
            }

            int idx = 0;
            for (int i = 1; i <= 18; i++) {
                if (gList.contains(i))	continue;
                aArr[idx++] = i;
            }
            visited = new boolean[9];
            int[] output = new int[9];
            win = 0;
            lose = 0;

            dfs(0, 0, 0);
            sb.append("#" + tc + " " + win + " " + lose + "\n");
        }

        System.out.println(sb);
        br.close();
    }

    public static void dfs(int depth, int gScore, int aScore) {
        if (depth == 9) {
            if (gScore > aScore) win++;
            else if (gScore < aScore) lose++;
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                if (gList.get(depth) > aArr[i]) {
                    dfs(depth + 1, gScore + (gList.get(depth) + aArr[i]), aScore);
                } else if (gList.get(depth) < aArr[i]) {
                    dfs(depth + 1, gScore, aScore + (gList.get(depth) + aArr[i]));
                } else {
                    dfs(depth + 1, gScore, aScore);
                }
                visited[i] = false;
            }
        }
    }*/
}
