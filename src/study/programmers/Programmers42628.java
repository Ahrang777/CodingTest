package study.programmers;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42628
 *
 * 이중우선순위큐
 */
public class Programmers42628 {

    public static int[] solution(String[] operations) {
        int[] answer = {};



        return answer;
    }

    public static void main(String[] args) {
        String[][] operations = {
                {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"},
                {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"}
        };

        for (int i = 0; i < operations.length; i++) {
            for (int s : solution(operations[i])) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
