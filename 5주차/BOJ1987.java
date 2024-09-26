import java.util.*;
import java.io.*;

// 2 4
// CAAB
// ADCB

public class BOJ1987 {
  static int n, m;
  static char[][] arr;
  static boolean[][] visited;
  static int[] dx = new int[] { 0, 0, 1, -1 };
  static int[] dy = new int[] { 1, -1, 0, 0 };
  static int res;
  static Map<Character, Integer> map;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    st = new StringTokenizer(br.readLine(), " ");
    map = new HashMap<>();

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    arr = new char[n][m];
    visited = new boolean[n][m];
    for (int i = 0; i < n; i++) {
      String line = br.readLine();
      // arr[i] = br.readLine().toCharArray();
      for (int j = 0; j < m; j++) {
        char alphabet = line.charAt(j);
        if (!map.containsKey(alphabet)) {
          map.put(alphabet, 0);
        }
        arr[i][j] = alphabet;
      }
    }

    res = 0;
    dfs(0, 0, 1);
    System.out.println(res);
  }

  static void dfs(int x, int y, int count) {
    // alphabet[arr[x][y] - 'A'] = true;
    visited[x][y] = true;
    map.put(arr[x][y], map.get(arr[x][y]) + 1);

    if (count > res)
      res = count;

    for (int d = 0; d < 4; d++) {
      int nx = x + dx[d];
      int ny = y + dy[d];

      if (nx < 0 || nx >= n || ny < 0 || ny >= m)
        continue;
      if (map.get(arr[nx][ny]) > 0)
        continue;
      if (visited[nx][ny])
        continue;

      dfs(nx, ny, count + 1);
    }
    visited[x][y] = false;
    map.put(arr[x][y], map.get(arr[x][y]) - 1);
  }
}
