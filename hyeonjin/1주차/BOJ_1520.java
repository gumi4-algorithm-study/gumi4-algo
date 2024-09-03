import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1520 {

	static int M, N;
	static int[][] map, dp;
	static int[] dx = { 1, 0, -1, 0 }, dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		dp = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1; // 방문 처리도 동시에 할 것이므로 -1로 초기화
			}
		}
		System.out.println(dfs(0, 0));
	}

	static int dfs(int x, int y) {
		// 경로가 있는 경우
		if (x == M - 1 && y == N - 1)
			return 1; // 여기까지 경로가 하나 있다는 뜻

		// 아직 해당 칸을 탐색하지 않은 경우
		if (dp[x][y] == -1) {
			dp[x][y] = 0; // 방문처리

			// 네 방향 탐색
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || ny < 0 || nx >= M || ny >= N)
					continue;

				// 내리막길인 경우
				if (map[nx][ny] < map[x][y]) {
					dp[x][y] += dfs(nx, ny); // (nx, ny)에서 가능한 경로 개수 더하기
				}
			}
		}
		return dp[x][y];
	}
}
