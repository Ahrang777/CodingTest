package study.programmers;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/43164
 *
 * 여행경로
 * 다시풀기
 */
public class Programmers43164 {

    static boolean[] visited;
    static List<String> routes = new ArrayList<>();

    public static String[] solution(String[][] tickets) {
        String[] answer = {};

        visited = new boolean[tickets.length];
        dfs("ICN", "ICN", tickets, 0);
        Collections.sort(routes);

        answer = routes.get(0).split(" ");

        return answer;
    }

    // 찾는대상(시작점), 지금까지 경로(나중에 경로 나뉘는 경우 답을 찾기위해)
    public static void dfs(String start, String route, String[][] tickets, int cnt) {
        if (cnt == tickets.length) {
            routes.add(route);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && start.equals(tickets[i][0])) {
                visited[i] = true;
                // split으로 나누기 위해 " "
                dfs(tickets[i][1], route + " " + tickets[i][1], tickets, cnt + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        String[][][] tickets = {
                {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}},
                {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}}
        };

        for (int i = 0; i < tickets.length; i++) {
            for (String str : solution(tickets[i])) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
    }
}
