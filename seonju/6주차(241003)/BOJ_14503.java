import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503 {
    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int startX, startY;
    static int dir;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        st = new StringTokenizer(br.readLine(), " ");
        startX = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken()); // 0북, 1동, 2남, 3서
        // 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        boolean flag = false;
        while (!flag) {
            if (map[startX][startY] == 0) { // 청소 안되어 있으면
                map[startX][startY] = 2;    // 청소

                count++;
            }
            if (!search()) {   // 청소 불가 -> 후진
                switch (dir) {  // 방향 유지한 채 후진
                    case 0: // 북
                        if (isPossible(startX + 1, startY) && map[startX + 1][startY] != 1) {
                            startX += 1; // 북쪽에서 후진 -> x를 증가
                        } else {
                            flag = true; // 후진 불가능
                        }
                        break;
                    case 1: // 동
                        if (isPossible(startX, startY - 1) && map[startX][startY - 1] != 1) {
                            startY -= 1; // 동쪽에서 후진 -> y를 감소
                        } else {
                            flag = true;
                        }
                        break;
                    case 2: // 남
                        if (isPossible(startX - 1, startY) && map[startX - 1][startY] != 1) {
                            startX -= 1; // 남쪽에서 후진 -> x를 감소
                        } else {
                            flag = true;
                        }
                        break;
                    case 3: // 서
                        if (isPossible(startX, startY + 1) && map[startX][startY + 1] != 1) {
                            startY += 1; // 서쪽에서 후진 -> y를 증가
                        } else {
                            flag = true;
                        }
                        break;
                }
            } else {  // 청소 가능 -> 이동
                dir = change(dir);  // 반시계 방향
                switch (dir) {
                    case 0: // 북
                        if (isPossible(startX - 1, startY) && map[startX - 1][startY] == 0) {
                            startX -= 1;
                        }
                        break;
                    case 1: // 동
                        if (isPossible(startX, startY + 1) && map[startX][startY + 1] == 0) {
                            startY += 1;
                        }
                        break;
                    case 2: // 남
                        if (isPossible(startX + 1, startY) && map[startX + 1][startY] == 0) {
                            startX += 1;
                        }
                        break;
                    case 3: // 서
                        if (isPossible(startX, startY - 1) && map[startX][startY - 1] == 0) {
                            startY -= 1;
                        }
                        break;
                }
            }
        }

        System.out.println(count);

    }

    public static boolean search() {
        int dir_count = 0;  // 4방향 모두 가능한지 확인
        for (int i = 0; i < 4; i++) {   // 동서남북 탐색
            int nx = startX + dx[i];
            int ny = startY + dy[i];

            if (isPossible(nx, ny) && map[nx][ny] == 0)   // 청소 가능
                dir_count++;
        }
        return dir_count != 0;
    }

    public static boolean isPossible(int x, int y) {
        return x >= 0 && y >= 0 && x <= N - 1 && y <= M - 1;
    }

    public static int change(int dir) {
        if (dir == 0) {
            return 3;
        } else {
            return dir - 1;
        }
    }
}

