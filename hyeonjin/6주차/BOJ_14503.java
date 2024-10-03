import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503 {

	static int N, M;
	static int[][] room;
	static int[] dx = { -1, 0, 1, 0 }, dy = { 0, 1, 0, -1 }; // 북동남서

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		room = new int[N][M];
		st = new StringTokenizer(br.readLine());
		int cx = Integer.parseInt(st.nextToken()); // 청소기의 시작 위치
		int cy = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken()); // 청소기가 바라보는 방향

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) { // 0: 청소되지 않은 빈 칸, 1: 벽 (+ 2: 청소된 칸)
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cleanRoom(cx, cy, d);
	}

	static void cleanRoom(int cx, int cy, int d) {
		// "작동을 멈출 때까지 청소하는 칸의 개수" 구하기
		int x = cx, y = cy, cleanCnt = 0;
		while (true) {
			// 1. 현재 칸 청소 X => 청소하기
			if (room[x][y] == 0) {
				room[x][y] = 2;
				cleanCnt++;
			}

			// 네 방향 확인 => 청소되지 않은 빈 칸이 있는지 확인하기
			boolean allClean = true;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;

				// 청소해야 하는 칸이 존재하는 경우
				if (room[nx][ny] == 0) {
					allClean = false;
					break;
				}
			}

			// 2. 인접한 칸 중 청소되지 않은 빈 칸이 없음(= 모두 청소됨)
			if (allClean) {
				// a. 방향 유지 & 후진하기
				int nx = x - dx[d];
				int ny = y - dy[d];
				// b. 후진할 수 없다(=벽)면 작동 멈추기
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || room[nx][ny] == 1) {
					break;
				} else { // 1번으로 돌아가기(후진한 좌표로 갱신)
					x = nx;
					y = ny;
				}
			}
			// 3. 청소되지 않은 빈 칸이 있음
			else {
				// a. 반시계 방향으로 90도 회전
				d--;
				if (d < 0)
					d = 3;
				// b. 바라보는 방향을 기준으로 앞 쪽 칸이 청소되지 않은 빈칸이면 한 칸 전진
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (room[nx][ny] == 0) { // 한 칸 전진 & 1번으로 돌아가기
					x = nx;
					y = ny;
				}
			}
		}
		System.out.println(cleanCnt);
	}
}
