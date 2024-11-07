import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_10966 {

	static int N, M;
	static char[][] map;
	static int[][] visited;
	static Queue<int[]> queue = new ArrayDeque<>();
	static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			visited = new int[N][M];
			queue.clear();

			// 모든 땅에서 어느 물까지의 최소 이동 횟수들의 합
			// == 물에서 모든 땅까지의 최단 거리
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = line.charAt(j);
					if (map[i][j] == 'W') { // 물의 위치 큐에 저장하기
						queue.offer(new int[] { i, j });
						visited[i][j] = 1;
					}
				}
			}
			bfs();
			int totalDist = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					totalDist += visited[i][j] - 1;
				}
			}
			sb.append("#").append(t).append(" ").append(totalDist).append("\n");
		}
		System.out.println(sb);
	}

	static void bfs() {
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0], y = now[1];

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;

				if (visited[nx][ny] != 0 || map[nx][ny] == 'W')
					continue;

				queue.offer(new int[] { nx, ny });
				visited[nx][ny] = visited[x][y] + 1;
			}
		}
	}
}
