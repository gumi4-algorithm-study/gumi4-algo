import java.io.*;
import java.util.*;

// 5 7
// WLLWWWL
// LLLWLLL
// LWLWLWW
// LWLWLLL
// WLLWLWW

public class BOJ2589 {
  static int n, m;
  static char[][] arr;
  static int[] dx = new int[] { 0, 0, 1, -1 };
  static int[] dy = new int[] { 1, -1, 0, 0 };
  static int[][] visited;
  static int res;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    st = new StringTokenizer(br.readLine(), " ");
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    arr = new char[n][m];
    for (int i = 0; i < n; i++) {
      String line = br.readLine();
      for (int j = 0; j < m; j++) {
        arr[i][j] = line.charAt(j);
      }
    }

    res = Integer.MIN_VALUE;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (arr[i][j] == 'L') {
          visited = new int[n][m];
          bfs(i, j);
          int temp = getMax() - 1;
          if (temp > res) {
            res = temp;
          }
        }
      }
    }
    System.out.println(res);
  }

  static void bfs(int i, int j) {
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[] { i, j });
    visited[i][j] = 1;

    while (!q.isEmpty()) {
      int[] cur = q.poll();
      int x = cur[0];
      int y = cur[1];
      int now = visited[x][y];

      for (int d = 0; d < 4; d++) {
        int nx = x + dx[d];
        int ny = y + dy[d];
        int next = now + 1;

        if (nx < 0 || nx >= n || ny < 0 || ny >= m)
          continue;
        if (visited[nx][ny] > 0)
          continue;

        if (arr[nx][ny] == 'L') {
          q.add(new int[] { nx, ny });
          visited[nx][ny] = next;
        }
      }
    }
  }

  static int getMax() {
    int max = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (visited[i][j] > max) {
          max = visited[i][j];
        }
      }
    }
    return max;
  }
}
