import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_8382 {

	static final int N = 100, M = 201; // 음수 좌표 > 양수 좌표
	static int sx, sy, ex, ey;
	static int[][][] visited; // [][][0]: 가로 이동으로 시작, [][][1]: 세로 이동으로 시작

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			sx = Integer.parseInt(st.nextToken()) + N;
			sy = Integer.parseInt(st.nextToken()) + N;
			ex = Integer.parseInt(st.nextToken()) + N;
			ey = Integer.parseInt(st.nextToken()) + N;

			// 가로 이동
			visited = new int[2][M][M];
			int d1 = bfs(0);
			visited = new int[2][M][M];
			int d2 = bfs(1);
			sb.append("#").append(t).append(" ").append(Math.min(d1, d2)).append("\n");
		}
		System.out.println(sb);
	}

	static int[][] moveLR = { { 0, 1 }, { 0, -1 } }, moveUD = { { 1, 0 }, { -1, 0 } };

	static int bfs(int sd) {
		Queue<Pos> queue = new ArrayDeque<>();
		queue.offer(new Pos(sx, sy, sd));
		visited[sd][sx][sy] = 1;
		while (!queue.isEmpty()) {
			Pos now = queue.poll();
			int x = now.x, y = now.y, d = now.d;
			if (x == ex && y == ey)
				return visited[d][x][y] - 1;

			// 전에 가로로 이동(d = 0)했다면 세로로, 세로로 이동(d = 1)했다면 가로로 이동하기
			int nd = (d == 0? 1 : 0);
			int[][] move = (d == 0? moveUD : moveLR);
			for (int i = 0; i < 2; i++) {
				int nx = x + move[i][0];
				int ny = y + move[i][1];
				if (nx < 0 || ny < 0 || nx >= M || ny >= M || visited[nd][nx][ny] != 0)
					continue;
				
				queue.offer(new Pos(nx, ny, nd));
				visited[nd][nx][ny] = visited[d][x][y] + 1;
			}
		}
		return Integer.MAX_VALUE;
	}

	static class Pos {
		int x, y, d;

		public Pos(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
}
