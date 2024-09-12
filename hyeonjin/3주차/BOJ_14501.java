import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14501 {

	// 완탐(부분집합)
	static int N, ans;
	static int[] time, pay;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		time = new int[N + 2];
		pay = new int[N + 2];
		StringTokenizer st = null;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			pay[i] = Integer.parseInt(st.nextToken());
		}
		time[N + 1] = 1; // N+2일로 넘어가기 위해
		dfs(1, 0);
		System.out.println(ans);
	}

	static void dfs(int day, int totalPay) {
		// 회사에 없는데 상담을 하려는 경우 종료
		if (day > N + 1)
			return;

		ans = Math.max(ans, totalPay);
		if (day == N + 1) // 마지막 날에 도달하면 종료
			return;

		// 현재 날짜 선택 O
		dfs(day + time[day], totalPay + pay[day]);

		// 현재 날짜 선택 X
		dfs(day + 1, totalPay);
	}
}
