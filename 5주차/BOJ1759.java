import java.io.*;
import java.util.*;

public class BOJ1759 {
  static int l, c;
  static char[] arr;
  static char[] selected;
  static int res;

  public static void main(String[] args) throws Exception {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    st = new StringTokenizer(br.readLine(), " ");
    l = Integer.parseInt(st.nextToken());
    c = Integer.parseInt(st.nextToken());

    arr = new char[c];
    st = new StringTokenizer(br.readLine(), " ");
    for (int i = 0; i < c; i++) {
      arr[i] = st.nextToken().charAt(0);
    }

    // 모음 1개 이상, 자음 2개 이상
    // 증가
    selected = new char[l];
    Arrays.sort(arr);
    dfs(0, 0);

  }

  static void dfs(int idx, int cnt) {
    // 기저 조건
    if (cnt == l) {
      // 모음 1개 이상, 자음 2개 이상
      int mo = 0;
      int ja = 0;
      for (int i = 0; i < l; i++) {
        if (selected[i] == 'a' || selected[i] == 'e' || selected[i] == 'i' || selected[i] == 'o'
            || selected[i] == 'u') {
          mo++;
        } else {
          ja++;
        }
      }

      if (mo >= 1 && ja >= 2) {
        for (int j = 0; j < l; j++) {
          System.out.print(selected[j]);
        }
        System.out.println();
      }
      return;
    }

    for (int i = idx; i < c; i++) {
      selected[cnt] = arr[i];
      dfs(i + 1, cnt + 1);
    }
  }
}
