import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2565 {
	static final int INF = 1_000_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Line[] lines = new Line[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			lines[i] = new Line(a, b);
		}

		// a 기준 정렬하기
		Arrays.sort(lines);

		// a는 이미 정렬되어 있으니 b만 확인하면 됨
		int[] dp = new int[N];
		Arrays.fill(dp, INF);
		dp[0] = lines[0].b;
		for (int i = 1; i < N; i++) {
			// 최대 길이만 필요하므로 이분 탐색 사용하기!
			int idx = Arrays.binarySearch(dp, lines[i].b);
			if (idx < 0)
				idx = -1 * idx - 1;
			dp[idx] = lines[i].b;
		}
		int len = 0;
		for (int d : dp) {
			if (d == INF)
				break;

			len++;
		}
		System.out.println(N - len);
	}

	static class Line implements Comparable<Line> {
		int a, b;

		public Line(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Line o) {
			// a를 기준으로 오름차순 정렬
			if (this.a == o.a)
				return this.b - o.b;
			return this.a - o.a;
		}
	}
}
