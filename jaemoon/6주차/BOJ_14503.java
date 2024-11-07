import java.util.Scanner;

public class BOJ_14503 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int sx = sc.nextInt();
		int sy = sc.nextInt();
		int d = sc.nextInt();
		
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		int[] dx = new int[] {-1, 0, 1, 0};
		int[] dy = new int[] {0, 1, 0, -1};
		
		int order = 1;
		int result = 0;
		while (true) {
			
			if (order == 1) {
				if (map[sx][sy] == 0) {
					map[sx][sy] = 2;
					result++;
				}
				order = 2;
			} else if (order == 2) {
				int emptyRoom = 0;
				for (int i = 0; i < 4; i++) {
					int nx = sx + dx[i];
					int ny = sy + dy[i];
					
					if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 0) {
						emptyRoom++;
					}
				}
				
				if (emptyRoom == 0) {
					if (map[sx - dx[d]][sy - dy[d]] == 1) {
						break;
					} else {
						sx -= dx[d];
						sy -= dy[d];
						order = 1;
					}
				} else {
					order = 3;
				}
			} else if (order == 3) {
				if (d == 0) {
					d = 3;
				} else {
					d--;
				}
				
				if (map[sx + dx[d]][sy + dy[d]] == 0) {
					sx += dx[d];
					sy += dy[d];
					order = 1;
				}
			}
		}
		System.out.println(result);
	}
}

