// 3 3
// 1 1 0
// 1 1 1
// 1 0 1
// 1 1 1

import java.io.*;
import java.util.*;

public class BOJ14503 {
  static int n, m;
  static int[][] arr;
  static int r, c, d;
  static int[] dx = new int[] { -1, 0, 1, 0 };
  static int[] dy = new int[] { 0, 1, 0, -1 };
  static int res;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    arr = new int[n][m];

    st = new StringTokenizer(br.readLine(), " ");
    r = Integer.parseInt(st.nextToken());
    c = Integer.parseInt(st.nextToken());
    d = Integer.parseInt(st.nextToken());

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      for (int j = 0; j < m; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    res = 0;
    dfs(r, c, d);

    System.out.println(res);

  }

  static void dfs(int x, int y, int d) {
    // 청소가 안됐다면, 청소한다.
    if (arr[x][y] == 0) {
      arr[x][y] = 2;
      res++;
    }

    // 4방향 중에 청소가 안된 곳이 있다면
    if ((x + 1 < n && arr[x + 1][y] == 0) || (y + 1 < m && arr[x][y + 1] == 0) || (x - 1 >= 0 && arr[x - 1][y] == 0)
        || (y - 1 >= 0 && arr[x][y - 1] == 0)) {
      // 왼쪽 방향으로 회전
      int nd = (d + 3) % 4;
      int nx = x + dx[nd];
      int ny = y + dy[nd];
      if (nx >= 0 && nx < n && ny >= 0 && ny < m && arr[nx][ny] == 0) {
        // 회전한 방향으로 전진
        dfs(nx, ny, nd);
        return;
      } else {
        // 회전만 하고 다시 dfs
        dfs(x, y, nd);
        return;
      }
    }
    // 네 방향 모두 청소가 되어있거나 벽인 경우
    else {
      // 후진
      int nd = (d + 2) % 4;
      int nx = x + dx[nd];
      int ny = y + dy[nd];
      if (nx >= 0 && nx < n && ny >= 0 && ny < m && arr[nx][ny] != 1) {
        // 방향은 유지한 채로 후진
        dfs(nx, ny, d);
        return;
      } else {
        return;
      }
    }
  }
}
