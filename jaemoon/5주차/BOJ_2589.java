import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BOJ_2589 {

	static int h, w, result, v[][];
	static String map[][];
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		h = sc.nextInt();
		w = sc.nextInt();
		
		map = new String[h][w];
		for (int i = 0; i < h; i++) {
			String input = sc.next();
			for (int j = 0; j < w; j++) {
				map[i][j] = input.charAt(j) + "";
			}
		}
		
		result = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (map[i][j].equals("L")) {
					bfs(i, j);
				}
			}
		}
		System.out.println(result);
	}
	
	static int[] dx = new int[] {-1, 1, 0, 0};
	static int[] dy = new int[] {0, 0, -1, 1};
	
	static void bfs(int x, int y) {
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y, 0});
		v = new int[h][w];
		v[x][y] = 1;
		
		int d = 0;
		while (!q.isEmpty()) {
			int[] poll = q.poll();
			x = poll[0];
			y = poll[1];
			d = poll[2];
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx >= 0 && nx < h && ny >= 0 && ny < w && map[nx][ny].equals("L") && v[nx][ny] == 0) {
					v[nx][ny] = 1;
					q.offer(new int[] {nx, ny, d + 1});
				}
			}
		}
		
		if (d > result) {
			result = d;
		}
	}
}

