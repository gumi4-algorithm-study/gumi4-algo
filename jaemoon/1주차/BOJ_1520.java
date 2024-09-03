import java.util.Scanner;

public class BOJ_1520 {
    /**
     * m : 세로
     * n : 가로
     * map : 내리막길
     * dp : 경로개수
     * dx, dy : 4방 탐색
     */
    static int m;
    static int n;
    static int[][] map;
    static int[][] dp;
    static int[] dx = new int[] {-1, 1, 0, 0};
    static int[] dy = new int[] {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        m = sc.nextInt();
        n = sc.nextInt();

        map = new int[m][n];
        dp = new int[m][n];

        // 맵 입력
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                dp[i][j] = -1; // 아직 경로 계산을 하지 않음을 의미
            }
        }
        
        // 결과 출력
        System.out.println(dfs(0, 0));
    }

    static int dfs(int x, int y) {

        // 목적지에 도달한 경우 1개의 경로를 반환
        if (x == m - 1 && y == n - 1) {
            return 1;
        }

        // 이미 계산된 경로 수가 있으면 그 값을 반환
        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        dp[x][y] = 0; // 경로 수를 계산 중임을 나타내기 위해 초기화

        // 상하좌우로 이동하며 가능한 경로를 찾음
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < m && ny >= 0 && ny < n && map[x][y] > map[nx][ny]) {
                dp[x][y] += dfs(nx, ny); // 다음 지점에서 가능한 경로의 수를 더함
            }
        }

        return dp[x][y];
    }
}
