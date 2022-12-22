package implementation.ch12;

/*
"aabbaccc"	7
"ababcdcdababcdcd"	9
"abcabcdede"	8
"abcabcabcabcdededededede"	14
"xababcdcdababcdcd"  17
 */

/**
 * https://programmers.co.kr/learn/courses/30/lessons/60057
 * 
 * 문자열 압축
 * TODO 다시풀기: 2
 */
public class Ex3 {

    /*
    //내 풀이
    public static int solution(String s) {

        int answer = s.length();

        //step: 압축 문자 개수
        for(int step = 1; step < s.length() / 2 + 1; step++){

            String result = "";
            String prev = s.substring(0, step);
            int cnt = 1;

            for(int i = step; i < s.length(); i += step){


                String now = "";

                *//*for (int k = i; k < i + step; k++) {
                    if(k < s.length()) now += s.charAt(k);
                }*//*

                //남은 글자가 step 개수 보다 적은경우 구분
                int j = i+step > s.length() ? s.length() : i+step;
                now += s.substring(i, j);

                if(prev.equals(now)){
                    cnt++;
                }else{
                    //중복 없어서 숫자가 1인 경우 1 생략 >> aab >> 2ab
                    result += (cnt < 2 ? prev : (cnt + prev));

                    *//*now = "";
                    for (int k = i; k < i + step; k++) {
                        if(k < s.length()) now += s.charAt(k);
                    }*//*

                    cnt = 1;
                    prev = now;
                }
            }

            result += (cnt < 2 ? prev : (cnt + prev));
            answer = Math.min(answer, result.length());
        }

        return answer;
    }

    public static void main(String[] args) {

        String[] str = {
                "aabbaccc",
                "ababcdcdababcdcd",
                "abcabcdede",
                "abcabcabcabcdededededede",
                "xababcdcdababcdcd"
        };

        for (String s : str) {
            System.out.println(solution(s));
        }
    }
    */

    //재귀를 이용한 풀이

    public static int solution(String s) {
        int answer = 0;

        //1글자인 경우도 포함되야 하니까 s.length()/2+1
        for (int i = 1; i <= (s.length() / 2) + 1; i++) {
            int result = getSplitedLength(s, i, 1).length();
            //answer 이 처음에 0이기 때문에 한번은 결과값이 들어가야지 아니면 모든 결과가 0 
            //만약 처음에 answer를 INF 처럼 큰값으로 넣으면 answer = Math.min(answer, result); 적용 가능 
            answer = i == 1 ? result : (answer > result ? result : answer);
            //answer = Math.min(answer, result);
        }

        return answer;
    }

    //문자열, 묶음 단위 개수, 반복횟수
    public static String getSplitedLength(String s, int n, int repeat) {
        //재귀 종료 조건
        if(s.length() < n) return s;
        
        String result = "";
        String preString = s.substring(0, n);
        String postString = s.substring(n, s.length());

        //중복x 압축 안되는 경우
        if (!postString.startsWith(preString)) {
            if(repeat == 1) return result += preString + getSplitedLength(postString, n, 1);
            return result += Integer.toString(repeat) + preString + getSplitedLength(postString, n, 1);
//            return result += repeat + preString + getSplitedLength(postString, n, 1);
        }

        //중복o, 압축 진행중
        return result += getSplitedLength(postString, n, repeat + 1);
    }

    /*
    public int solution(String s) {
        int minValue = Integer.MAX_VALUE;

        for (int i = 1; i <= (s.length() / 2) + 1; i++) {
            int length = splitByLength(s, i, 1).length();
            minValue = Math.min(minValue, length);
        }

        return minValue;
    }

    public String splitByLength(String str, int length, int repeat) {
        // 마지막에 묶어야 하는 길이보다 작은 경우면 그냥 이어 붙임
        if (str.length() < length) {
            return str;
        }

//        String result = "";
        String prefix = str.substring(0, length);
        String sub = str.substring(length);

        if (!sub.startsWith(prefix)) {
            if (repeat == 1) {
                // return result += prefix + splitByLength(sub, length, 1);
                return prefix + splitByLength(sub, length, 1);
            }
            //return result += repeat + prefix + splitByLength(sub, length, 1);
            return repeat + prefix + splitByLength(sub, length, 1);
        }

        //return result += splitByLength(sub, length, repeat + 1);
        return splitByLength(sub, length, repeat + 1);
    }
     */

    public static void main(String[] args) {
        String[] str = {
                "aabbaccc",
                "ababcdcdababcdcd",
                "abcabcdede",
                "abcabcabcabcdededededede",
                "xababcdcdababcdcd"
        };

        for (String s : str) {
            System.out.println(solution(s));
        }
    }
}
