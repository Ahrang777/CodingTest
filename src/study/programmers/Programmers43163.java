package study.programmers;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/43163
 *
 * 단어 변환
 * 다시풀기
 */
public class Programmers43163 {

    static class Word {
        String word;
        int step;

        public Word(String word, int step) {
            this.word = word;
            this.step = step;
        }
    }

    public static int solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        int answer = bfs(begin, target, words, visited);
        return answer;
    }

    public static int bfs(String begin, String target, String[] words, boolean[] visited) {
        Queue<Word> q = new LinkedList<>();
        q.offer(new Word(begin, 0));
        int res = 0;

        while (!q.isEmpty()) {
            Word now = q.poll();

            if (now.word.equals(target)) {
                res = now.step;
                break;
            }

            for (int i = 0; i < words.length; i++) {
                if (!visited[i] && check(now.word, words[i])) {
                    visited[i] = true;
                    q.offer(new Word(words[i], now.step + 1));
                }
            }
        }

        return res;
    }

    public static boolean check(String begin, String target) {
        int cnt = 0;

        for (int i = 0; i < begin.length(); i++) {
            if (begin.charAt(i) == target.charAt(i)) {
                cnt++;
            }
        }

        return ((cnt == begin.length() - 1) ? true : false);
    }

    /*
    static boolean[] visited;
    static int answer = 0;

    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];

        dfs(begin, target, words, 0);
        return answer;
    }

    public static void dfs(String begin, String target, String[] words, int cnt) {
        if (begin.equals(target)) {
            answer = cnt;
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (visited[i]) {
                continue;
            }

            int k = 0;    // 같은 스펠링 몇개인지 세기
            for (int j = 0; j < begin.length(); j++) {
                if (begin.charAt(j) == words[i].charAt(j)) {
                    k++;
                }
            }

            if (k == begin.length() - 1) {  // 한글자 빼고 모두 같은 경우
                visited[i] = true;
                dfs(words[i], target, words, cnt + 1);
                visited[i] = false;
            }
        }
    }
     */

    public static void main(String[] args) {
        String[] begin = {"hit", "hit"};
        String[] target = {"cog", "cog"};
        String[][] words = {
                {"hot", "dot", "dog", "lot", "log", "cog"},
                {"hot", "dot", "dog", "lot", "log"}
        };

        for (int i = 0; i < 2; i++) {
            System.out.println(solution(begin[i], target[i], words[i]));
        }
    }
}
