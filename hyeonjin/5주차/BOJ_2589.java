import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2589 {

	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };
	static ArrayList<int[]> areaList = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'L') {
					areaList.add(new int[] { i, j });
				}
			}
		}

		int ans = 0;
		for (int[] area : areaList) {
			// 가장 멀리 연결된 육지 찾기
			visited = new boolean[N][M];
			ans = Math.max(ans, bfs(area[0], area[1]));
		}
		System.out.println(ans);
	}

	static int bfs(int x, int y) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { x, y });
		visited[x][y] = true;

		// 현재 육지에서 연결된 모든 육지까지 갈 수 있는 최단 거리
		int dist = 0;
		while (!queue.isEmpty()) {
			dist++;
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] now = queue.poll();

				for (int j = 0; j < 4; j++) {
					int nx = now[0] + dx[j];
					int ny = now[1] + dy[j];
					if (nx < 0 || ny < 0 || nx >= N || ny >= M)
						continue;

					// 방문하지 않은 육지면 큐에 넣기
					if (!visited[nx][ny] && map[nx][ny] == 'L') {
						visited[nx][ny] = true;
						queue.offer(new int[] { nx, ny });
					}
				}
			}
		}
		return dist - 1; // 시작점은 세지 않음
	}
}
