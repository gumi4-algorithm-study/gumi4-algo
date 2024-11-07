import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BOJ_14940 {

	static int n, m, map[][];
	static int[] dx = new int[] {-1, 1, 0, 0};
	static int[] dy = new int[] {0, 0, -1, 1};
	static boolean v[][];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		
		int sx = 0, sy = 0;
		map = new int[n][m];
		v = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 2) {
					sx = i;
					sy = j;
				}
			}
		}
		
		v[sx][sy] = true;
		map[sx][sy] = 0;
		bfs(new int[] {sx, sy});
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1 && v[i][j] == false) {
					map[i][j] = -1;
				}
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		
	}

	static void bfs(int[] startPoint) {
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(startPoint);
		
		while (!q.isEmpty()) {
			int[] poll = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = poll[0] + dx[i];
				int ny = poll[1] + dy[i];
				
				if (nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] != 1 || v[nx][ny] == true) {
					continue;
				}
				
				v[nx][ny] = true;
				map[nx][ny] = map[poll[0]][poll[1]] + 1;
				q.offer(new int[] {nx, ny});
			}
		}
		
	}
}

