package study.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon11728 {
	/*
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] tmp1 = new int[N];
		int[] tmp2 = new int[M];
		int[] arr = new int[N+M];

		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			tmp1[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++)
			tmp2[i] = Integer.parseInt(st.nextToken());
		int cnt=0;int n=0,m=0;
		while(true) {

			if(tmp1[n]<tmp2[m])
				arr[cnt++] =tmp1[n++];
			else if(tmp1[n] == tmp2[m]) {
				arr[cnt++] = tmp1[n++];
				arr[cnt++] = tmp2[m++];
			}
			else
				arr[cnt++] = tmp2[m++];

		if(n == N || m == M) break;

		}

		for(int i=n;i<N;i++) {
			arr[cnt++] = tmp1[i];
		}
		for(int i=m;i<M;i++) {
			arr[cnt++] = tmp2[i];
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<arr.length;i++)
			sb.append(arr[i]).append(" ");
		System.out.println(sb);
	}
	 */

	static int N, M;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N + M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			// A[i] = Integer.parseInt(st.nextToken());
			arr[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = N; i < N + M; i++) {
			// B[i] = Integer.parseInt(st.nextToken());
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		for (int n : arr) {
			sb.append(n).append(" ");
		}

		System.out.println(sb);
	}
}
