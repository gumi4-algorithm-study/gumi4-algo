import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500 {

	static int N, M, ans;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(1, arr[i][j], i, j);
				visited[i][j] = false;

				// ㅗ 방향 고려하기
				checkExtraDir(i, j);
			}
		}
		System.out.println(ans);
	}

	static void dfs(int depth, int total, int x, int y) {
		// 네 개의 칸을 고르면 최댓값 갱신하기
		if (depth == 4) {
			ans = Math.max(ans, total);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny])
				continue;

			visited[nx][ny] = true;
			dfs(depth + 1, total + arr[nx][ny], nx, ny);
			visited[nx][ny] = false;
		}
	}

	static void checkExtraDir(int x, int y) {
		// ㅗ 모양
		if (x - 1 >= 0 && y + 2 < M) {
			ans = Math.max(ans, arr[x][y] + arr[x][y + 1] + arr[x][y + 2] + arr[x - 1][y + 1]);
		}

		// ㅜ 모양
		if (x + 1 < N && y + 2 < M) {
			ans = Math.max(ans, arr[x][y] + arr[x][y + 1] + arr[x][y + 2] + arr[x + 1][y + 1]);
		}

		// ㅏ 모양
		if (x + 2 < N && y + 1 < M) {
			ans = Math.max(ans, arr[x][y] + arr[x + 1][y] + arr[x + 2][y] + arr[x + 1][y + 1]);
		}
		// ㅓ 모양
		if (x + 2 < N && y - 1 >= 0) {
			ans = Math.max(ans, arr[x][y] + arr[x + 1][y] + arr[x + 2][y] + arr[x + 1][y - 1]);
		}
	}
}
