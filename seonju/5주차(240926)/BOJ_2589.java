import java.util.*;
import java.io.*;

//BOJ_2589 보물섬
public class BOJ_2589 {
    static int N, M;    // 가로, 세로
    static char[][] map;
    static int[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        max = 0;

        // 입력
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'L') { // 땅이면 확인
                    visited = new int[N][M];
                    bfs(i, j);
                }
            }
        }
        System.out.println(max);
    }

    public static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = 1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int c_x = cur[0];
            int c_y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = c_x + dx[i];
                int ny = c_y + dy[i];

                if (nx < 0 || nx > N - 1 || ny < 0 || ny > M - 1 || visited[nx][ny] != 0 || map[nx][ny] == 'W')
                    continue;
                queue.offer(new int[]{nx, ny});
                visited[nx][ny] = visited[c_x][c_y] + 1;
                // 1부터 시작했기 때문에 1 빼줘야 함
                if (visited[nx][ny] - 1 > max)
                    max = visited[nx][ny] - 1;
            }
        }
    }
}
