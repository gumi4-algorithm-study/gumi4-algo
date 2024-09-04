import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2564 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    st = new StringTokenizer(br.readLine(), " ");

    int m = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(st.nextToken());

    int k = Integer.parseInt(br.readLine());
    int pos[] = new int[k + 1];

    for (int i = 0; i < k + 1; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      // 북쪽 1
      // 남쪽 2
      // 서쪽 3
      // 동쪽 4

      switch (a) {
        case 1:
          pos[i] = b;
          break;
        case 2:
          pos[i] = 2 * m + n - b;
          break;
        case 3:
          pos[i] = 2 * m + 2 * n - b;
          break;
        case 4:
          pos[i] = m + b;
          break;
      }
    }

    int sum = 0;
    for (int i = 0; i < k; i++) {
      int min = Math.abs(pos[k] - pos[i]);
      sum += Math.min(min, 2 * m + 2 * n - min);
    }

    System.out.println(sum);

  }
}