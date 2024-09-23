import java.util.*;
import java.io.*;

public class BOJ14500 {
  static int n, m;
  static int[][] arr;
  static int res;
  static int dx[] = { 0, 0, 1, -1 };
  static int dy[] = { 1, -1, 0, 0 };
  static boolean[][] visited;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    st = new StringTokenizer(br.readLine(), " ");
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    arr = new int[n][m];
    visited = new boolean[n][m];

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      for (int j = 0; j < m; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    res = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        dfs(i, j, 1, arr[i][j]);
        getOther(i, j);
      }
    }

    System.out.println(res);

  }

  static void dfs(int i, int j, int cnt, int sum) {
    // 기저
    if (cnt == 4) {
      if (sum > res) {
        res = sum;
      }
      return;
    }

    visited[i][j] = true;
    for (int d = 0; d < 4; d++) {
      int ni = i + dx[d];
      int nj = j + dy[d];
      if (ni >= 0 && ni < n && nj >= 0 && nj < m && !visited[ni][nj]) {
        dfs(ni, nj, cnt + 1, sum + arr[ni][nj]);
      }
    }
    visited[i][j] = false;
  }

  static void getOther(int i, int j) {
    // ㅗ
    if (i >= 1 && j >= 1 && j < m - 1) {
      int sum = arr[i][j] + arr[i - 1][j] + arr[i][j - 1] + arr[i][j + 1];
      if (sum > res) {
        res = sum;
      }
    }

    // ㅏ
    if (i >= 1 && i < n - 1 && j < m - 1) {
      int sum = arr[i][j] + arr[i - 1][j] + arr[i + 1][j] + arr[i][j + 1];
      if (sum > res) {
        res = sum;
      }
    }

    // ㅜ
    if (i < n - 1 && j >= 1 && j < m - 1) {
      int sum = arr[i][j] + arr[i + 1][j] + arr[i][j - 1] + arr[i][j + 1];
      if (sum > res) {
        res = sum;
      }
    }

    // ㅓ
    if (i >= 1 && i < n - 1 && j >= 1) {
      int sum = arr[i][j] + arr[i - 1][j] + arr[i + 1][j] + arr[i][j - 1];
      if (sum > res) {
        res = sum;
      }
    }
  }
}
