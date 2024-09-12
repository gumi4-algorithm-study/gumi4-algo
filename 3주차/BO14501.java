import java.util.*;
import java.io.*;

public class BO14501 {
    static int n;
    static int[][] arr;
    static int res;

    // dp
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1][2];
        dp = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[i][0] = a;
            arr[i][1] = b;
            dp[i] = b;
        }
        dp[n + 1] = 0;

        res = 0;
        // dfs(1, 0);
        useDp();
        System.out.println(dp[1]);

    }

    static void useDp() {
        for (int i = n; i > 0; i--) {
            if (i + arr[i][0] <= n + 1) {
                // 선택, 비선택
                dp[i] = Math.max(dp[i + arr[i][0]] + arr[i][1], dp[i + 1]);
            } else {
                // 비선택
                dp[i] = dp[i + 1];
            }
        }
    }

    /**
     * dfs를 사용한 풀이
     * 
     * @param idx
     * @param sum
     */
    static void dfs(int idx, int sum) {
        // 기저 조건
        if (idx > n) {
            if (sum > res) {
                res = sum;
            }
            return;
        }

        if (idx + arr[idx][0] <= n + 1)
            // 선택
            dfs(idx + arr[idx][0], sum + arr[idx][1]);

        // 비선택
        dfs(idx + 1, sum);
    }

}
