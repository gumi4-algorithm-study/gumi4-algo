import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14940 {

	static int N, M;
	static int[][] map, dist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dist = new int[N][M];
		int sx = -1, sy = -1;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					sx = i;
					sy = j;
				}
			}
		}
		bfs(sx, sy);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 원래 갈 수 없는 땅은 0, 갈 수 있는 땅인데 도달할 수 없으면 -1
				if (map[i][j] == -1 || map[i][j] == 0) {
					// 시작 위치 or 원래 방문할 수 없는 땅
					sb.append(dist[i][j]).append(" ");
				} else if (map[i][j] != 0 && dist[i][j] == 0) {
					// 방문할 수 있는 땅인데 도달하지 못한 경우
					sb.append(-1).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static int[] dx = { 0, 1, 0, -1 }, dy = { 1, 0, -1, 0 };

	static void bfs(int x, int y) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { x, y });
		map[x][y] = -1; // 방문 체크
		dist[x][y] = 0; // 거리 기록

		while (!queue.isEmpty()) {
			int[] now = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;

				// 아직 방문하지 않은 지점인 경우
				if (map[nx][ny] == 1) {
					map[nx][ny] = -1;
					queue.offer(new int[] { nx, ny });
					dist[nx][ny] = dist[now[0]][now[1]] + 1;
				}
			}
		}
	}
}
