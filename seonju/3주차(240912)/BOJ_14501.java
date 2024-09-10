import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ_14501 퇴사
public class BOJ_14501 {
    static int N;   // 퇴사까지 남은 날
    static int[] T, P;  // 상담 완료까지 걸리는 기간, 금액
    static int[] dp;    // dp 저장 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        T = new int[N + 1];
        P = new int[N + 1];
        dp = new int[N + 1];

        // 입력
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            T[i] = t;
            P[i] = p;
        }

        // dp
        for (int i = 0; i < N; i++) {
            if (i + T[i] <= N) {    // 범위 안 넘어갈 때
                dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]); // 새로 계산한 값 vs 기존 최대
            }
            dp[i + 1] = Math.max(dp[i], dp[i + 1]); // 상담 안하고 넘어가도 최대 수익 유지
        }

        System.out.println(dp[N]);
    }
}