import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.LinkedBlockingDeque;

// BOJ_14940 쉬운 최단거리
public class BOJ_14940 {
    static int N, M;
    static int[][] map, result;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    static int startX, startY;
    static Queue<int[]> queue;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        queue = new LinkedList<>();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        result = new int[N][M];

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    startX = i;
                    startY = j;

                }
            }
        }

        queue.add(new int[] { startX, startY });
        visited[startX][startY] = true;
        result[startX][startY] = 0;
        bfs();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 갈 수 있는 땅인 부분 중에서 도달할 수 없는 위치
                if (map[i][j] != 0 && result[i][j] == 0 && map[i][j] != 2) {
                    result[i][j] = -1;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void bfs() {
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int val = result[cur[0]][cur[1]];
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1 || map[nx][ny] == 0 || visited[nx][ny])
                    continue;
                queue.add(new int[] { nx, ny });
                visited[nx][ny] = true;
                result[nx][ny] = val + 1;
            }
        }
    }
}
