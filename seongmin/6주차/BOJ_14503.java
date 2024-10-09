import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};  // 북 동 남 서
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int startDir = Integer.parseInt(st.nextToken());  // 시작 방향

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); // 열
            }
        }

        int answer = bfs(r, c, startDir);
        System.out.println(answer);
    }

    static int bfs(int r, int c, int startDir) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {r, c, startDir});
        int clean = 0;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];
            int cDir = pos[2];  // 현재 방향

            if (map[cx][cy] == 0) {
                map[cx][cy] = 2;  // 청소된 상태로 변경 ( + 방문 체크)
                clean++;
            }

            boolean cleanCheck = false;
            for (int i = 0; i < 4; i++) {
                int curDir = (cDir + 3 - i) % 4;  // 왼쪽 방향 회전
                int nx = cx + dx[curDir];
                int ny = cy + dy[curDir];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }  // 범위 검사

                if (map[nx][ny] == 0) {
                    queue.offer(new int[] {nx, ny, curDir});
                    cleanCheck = true;
                    break;  // 한 번에 한 칸만 전진
                }
            }

            if (!cleanCheck) {
                int backDir = (cDir + 2) % 4;  // 후진 방향
                int nx = cx + dx[backDir];
                int ny = cy + dy[backDir];

                if (0 <= nx && nx < N && 0 <= ny && ny < M && map[nx][ny] != 1) {
                    queue.offer(new int[] {nx, ny, cDir});
                }  // 큐에 후진 위치 추가
            }  // 4방향 모두 청소된 경우
        }
        return clean;
    }
}