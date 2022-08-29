package study.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/43163
 *
 * 단어 변환
 */
public class Programmers43163 {

    public static int solution(String begin, String target, String[] words) {
        int answer = 0;



        return answer;
    }

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
