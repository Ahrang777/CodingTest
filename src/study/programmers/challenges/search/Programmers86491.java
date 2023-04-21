package study.programmers.challenges.search;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/86491
 *
 * 최소직사각형
 */
public class Programmers86491 {

    public static int solution(int[][] sizes) {

        int answer = 0;

        int w = 0, h = 0;

        for (int[] size : sizes) {
            w = Math.max(w, Math.max(size[0], size[1]));
            h = Math.max(h, Math.min(size[0], size[1]));
        }

        answer = w * h;
        return answer;

        /*int answer = 0;

        int max_w = 0;
        int max_h = 0;

        for (int i = 0; i < sizes.length; i++) {
            max_w = Math.max(max_w, sizes[i][0]);
            max_h = Math.max(max_h, sizes[i][1]);
        }

        // w가 기준 >> h 변경, 큰거를 기준쪽으로 몰아줌
        if (max_w > max_h) {
            max_h = 0;
            for (int i = 0; i < sizes.length; i++) {
                if (sizes[i][0] < sizes[i][1]) {
                    int tmp = sizes[i][0];
                    sizes[i][0] = sizes[i][1];
                    sizes[i][1] = tmp;
                }
                max_h = Math.max(max_h, sizes[i][1]);
            }
        } 
        // h가 기준 >> w 변경, 큰거를 기준쪽으로 몰아줌
        else {
            max_w = 0;
            for (int i = 0; i < sizes.length; i++) {
                if (sizes[i][0] > sizes[i][1]) {
                    int tmp = sizes[i][0];
                    sizes[i][0] = sizes[i][1];
                    sizes[i][1] = tmp;
                }
                max_w = Math.max(max_w, sizes[i][0]);
            }
        }

        answer = max_w * max_h;

        return answer;*/
    }

    public static void main(String[] args) {
        int[][][] sizes = {
                {
                        {60, 50}, {30, 70},
                        {60, 30}, {80, 40}
                },
                {
                        {10, 7}, {12, 3}, {8, 15},
                        {14, 7}, {5, 15}
                },
                {
                        {14, 4}, {19, 6}, {6, 16},
                        {18, 7}, {7, 11}
                }
        };

        for (int i = 0; i < sizes.length; i++) {
            System.out.println(solution(sizes[i]));
        }
    }
}
