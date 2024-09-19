import java.util.Scanner;

public class BOJ_14500 {
	/**
	 * map : 지도
	 * N, M : 가로, 세로
	 * dx, dy : 4방탐색용
	 * v : 방문체크용
	 * result : 결과
	 */
    static int[][] map;
    static int N, M;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] v;
    static int result = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        v = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                v[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                v[i][j] = false;
            }
        }

        // 정답 출력
        System.out.println(result);
    }

    // 시작점부터 무작위로 4개씩 뻗어나가 그 4개의 합을 구하는 DFS
    static void dfs(int x, int y, int depth, int sum) { // sum은 map[r][c]가 초깃값, depth는 초깃값 1
        if (depth == 4) {
            if (result < sum) result = sum;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue; // index 유효하지 않으면 다음 i
            if (v[nx][ny]) continue;

            if (depth == 2) { // ㅗ 만들어줘야 할 경우 (두번째 칸에서 엇나가야지 그래서 depth 2)
                v[nx][ny] = true;
                dfs(x, y, depth + 1, sum + map[nx][ny]);
                v[nx][ny] = false;
            }

            v[nx][ny] = true;
            dfs(nx, ny, depth + 1, sum + map[nx][ny]);
            v[nx][ny] = false;
        }
    }
}

