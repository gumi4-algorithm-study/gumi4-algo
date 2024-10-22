import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map, answer;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        answer = new int[N][M];
        int sx = 0, sy = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int state = Integer.parseInt(st.nextToken());
                map[i][j] = state == 1 ? -1 : state;
                answer[i][j] = state == 1 ? -1 : state;

                if (map[i][j] == 2) {
                    sx = i;
                    sy = j;
                }
            }
        }

        visited = new boolean[N][M];
        answer[sx][sy] = 0;
        bfs(sx, sy);

        StringBuilder sb = new StringBuilder();
        for (int[] rows : answer) {
            for (int col : rows) {
                sb.append(col).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void bfs(int sx, int sy) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx, sy, 0});
        visited[sx][sy] = true;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];
            int distance = pos[2];
            answer[cx][cy] = distance;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) {
                    continue;
                }

                if (!visited[nx][ny] && map[nx][ny] == -1) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, distance + 1});
                }
            }
        }
    }
}

// 2번째 방법
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.*;
//
//public class Main {
//
//    static int N, M;
//    static int[][] map, answer;
//    static boolean[][] visited;
//    static int[] dx = {0, 0, -1, 1};
//    static int[] dy = {-1, 1, 0, 0};
//
//    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//
//        map = new int[N][M];
//        answer = new int[N][M];
//        int sx = 0, sy = 0;
//        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < M; j++) {
//                map[i][j] = Integer.parseInt(st.nextToken());
//                if (map[i][j] == 2) {
//                    sx = i;
//                    sy = j;
//                }  // 출발지 좌표
//            }
//        }
//
//        visited = new boolean[N][M];
//        answer[sx][sy] = 0;
//        bfs(sx, sy);
//
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                if (map[i][j] == 1 && answer[i][j] == 0) {
//                    answer[i][j] = -1;
//                }  // bfs 실행 후에도 answer 값이 0이고 기존 map값이 1이면 -1로 갱신
//            }
//        }
//
//        StringBuilder sb = new StringBuilder();
//        for (int[] rows : answer) {
//            for (int col : rows) {
//                sb.append(col).append(" ");
//            }
//            sb.append("\n");
//        }
//        System.out.println(sb);
//    }
//
//    static void bfs(int sx, int sy) {
//        Queue<int[]> queue = new ArrayDeque<>();
//        queue.offer(new int[]{sx, sy, 0});
//        visited[sx][sy] = true;
//
//        while (!queue.isEmpty()) {
//            int[] pos = queue.poll();
//            int cx = pos[0];
//            int cy = pos[1];
//            int distance = pos[2];
//            answer[cx][cy] = distance;  // 각 좌표별 최단 거리 갱신
//
//            for (int i = 0; i < 4; i++) {
//                int nx = cx + dx[i];
//                int ny = cy + dy[i];
//
//                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) {
//                    continue;
//                }
//
//                if (map[nx][ny] == 1) {
//                    visited[nx][ny] = true;
//                    queue.offer(new int[]{nx, ny, distance + 1});
//                }
//            }
//        }
//    }
//}
