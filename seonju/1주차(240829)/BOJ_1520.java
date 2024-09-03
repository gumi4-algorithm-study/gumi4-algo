import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1520 {
    static int M, N;
    static int[][] map;
    static int[][] memo;
    static int[] dx = {1, 0, -1, 0};  // 3방향 이동 (아래, 오른쪽, 왼쪽)
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        memo = new int[M][N]; // 메모이제이션 테이블

        // 메모이제이션 테이블을 -1로 초기화
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // 입력 데이터
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = dfs(0, 0);
        System.out.println(result);
    }

    static int dfs(int x, int y) {
        // 도착점에 도달하면 1을 반환 (경로 하나)
        if (x == M - 1 && y == N - 1) {
            return 1;
        }

        // 이미 계산된 결과가 있으면 반환
        if (memo[x][y] != -1) {
            return memo[x][y];
        }

        int count = 0;
        // 4방향으로 이동
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위 내에 있고, 현재 위치보다 낮은 곳으로만 이동
            if (nx >= 0 && nx < M && ny >= 0 && ny < N && map[nx][ny] < map[x][y]) {
                count += dfs(nx, ny);
            }
        }

        // 계산 결과를 메모이제이션 테이블에 저장
        memo[x][y] = count;
        return count;
    }
}
