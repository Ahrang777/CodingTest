package study.swexpertacademy.d3;

import java.io.*;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV14QpAaAAwCFAYi&categoryId=AV14QpAaAAwCFAYi&categoryType=CODE&problemTitle=&orderBy=RECOMMEND_COUNT&selectCodeLang=JAVA&select-1=3&pageSize=10&pageIndex=1
 *
 * [S/W 문제해결 기본] 3일차 - 회문1
 */
public class SWEA1215 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= 10; tc++) {
            int N = Integer.parseInt(br.readLine());

            char[][] arr = new char[8][8];
            for (int i = 0; i < 8; i++) {
                String str = br.readLine();
                for (int j = 0; j < 8; j++) {
                    arr[i][j] = str.charAt(j);
                }
            }

            int cnt = 0;
            // 가로
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j <= 8 - N; j++) {
                    String row = "";
                    String col = "";
                    for (int k = 0; k < N; k++) {
                        row += arr[i][j+k];
                        col += arr[j+k][i];
                    }
                    if (isPalindrome(row))	cnt++;
                    if (isPalindrome(col))	cnt++;
                }
            }

            System.out.printf("#%d %d\n", tc, cnt);

        }

    }

    public static boolean isPalindrome(String str) {
        int len = str.length();
        for (int i = 0; i < len/2; i++) {
            if (str.charAt(i) != str.charAt(len - 1 - i))	return false;
        }

        return true;

    }

    /*
    static Character[][] arr; // 글자판 배열
	static int sum; // 회문의 갯수
	static int l; // 회문의 길이

	public static void find(int i, int j, String type) {
		String tmp = "";
		int count = 0;

		// 가로 찾기
		if(type.equals("x")) {
			while(count < l) {
				tmp += arr[i][j+count];
				count++;
			}
		}
		// 세로 찾기
		else {
			while(count < l) {
				tmp += arr[j+count][i];
				count++;
			}
		}

		// 문자열 뒤집기
		StringBuffer bf = new StringBuffer(tmp);
		String reverse = bf.reverse().toString();

		// 회문 여부 검사
		if(tmp.equals(reverse)) {
			sum++;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int t =0; t<10; t++) {
			l = Integer.parseInt(br.readLine());
			sum = 0;
			arr = new Character[8][8];

			// 글자판 입력
			for(int i=0; i<8; i++) {
				String str = br.readLine();
				for(int j=0; j<8; j++) {
					arr[i][j] = str.charAt(j);
				}
			}

			for(int i=0; i<8; i++) {
				for(int j =0; j <= (8-l); j++) {
					find(i,j,"x"); // 가로찾기
					find(i,j,"y"); // 세로찾기
				}
			}
			System.out.println("#"+ (t+1) + " " + sum);
		}
	}
     */
}
