package study.programmers;

public class Programmers42842 {

    public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        // 노랑, 가로 + 세로
        int add = (brown - 4)/2;

        // 노랑, 가로 * 세로
        int mul = yellow;

        int x = 0, y = 0;

        // 노랑 가로 + 2, 노랑 세로 + 2 >> max, min 이 전체 가로, 세로
        // x, y 둘중 큰게 가로, 작은게 세로
        // x를 add/2 까지 돌리면서 해도 괜찮다. x가 작은쪽이라 생각했을때 최대는 x == y 인 경우
        // for (x = 1; x <= add; x++) {
        for (x = 1; x <= add/2; x++) {
            y = add - x;
            if (x * y == mul) {
                x += 2;
                y += 2;
                break;
            }
        }

        answer[0] = Math.max(x, y);
        answer[1] = Math.min(x, y);

        return answer;

        /*int a = (brown+4)/2;
        int b = yellow+2*a-4;
        int[] answer = {(int)(a+Math.sqrt(a*a-4*b))/2,(int)(a-Math.sqrt(a*a-4*b))/2};
        return answer;*/
    }

    public static void main(String[] args) {
        int[] brown = {10, 8, 24};
        int[] yellow = {2, 1, 24};

        for (int i = 0; i < 3; i++) {
            int[] solution = solution(brown[i], yellow[i]);
            for (int s : solution) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
