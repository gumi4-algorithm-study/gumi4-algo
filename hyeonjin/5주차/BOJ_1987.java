import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1987 {

	static int R, C, ans;
	static char[][] map;
	static boolean[][] visited;
	static boolean[] used = new boolean[26]; // HashSet 시간 많이 걸림..
	static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 좌측 상단에서 시작해서 최대한 몇 칸 지날 수 있는지 (시작점도 포함)
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		// (0, 0)에서 시작
		// 시작점이 정해져있음 => 그냥 DFS 돌리기?
		visited[0][0] = true;
		used[map[0][0] - 'A'] = true;
		dfs(0, 0, 1); // 시작점도 포함
		System.out.println(ans);
	}

	static void dfs(int x, int y, int depth) {
		// 지나갈 수 있는 최대의 칸 수 세기
		if (ans < depth)
			ans = depth;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= R || ny >= C)
				continue;

			int cIdx = map[nx][ny] - 'A';

			// 이미 방문한 지점이거나 해당 알파벳을 이미 사용한 경우
			if (visited[nx][ny] || used[cIdx])
				continue;

			visited[nx][ny] = true;
			used[cIdx] = true;
			dfs(nx, ny, depth + 1);
			used[cIdx] = false;
			visited[nx][ny] = false;
		}
	}
}
