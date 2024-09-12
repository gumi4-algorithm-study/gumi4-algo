import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2493 {
    static int n;
    static int[][] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        arr = new int[n][2];
        // Stack<int[]> stack = new Stack<>();

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = i;
        }

        for (int i = 1; i < n; i++) {
            arr[i][1] = dfs(arr[i][0], i - 1);
        }

        sb.append(0).append(" ");
        for (int i = 1; i < n; i++) {
            sb.append(arr[i][1] + 1).append(" ");
        }
        System.out.println(sb);

        /**
         * 시간 초과 코드
         */
        // sb.append("");
        // for (int i = 0; i < n; i++) {
        // int idx = 0;
        // for (int j = i - 1; j > 0; j--) {
        // if (arr[i] <= arr[j]) {
        // idx = j + 1;
        // break;
        // }
        // }
        // sb.append(idx).append(" ");
        // }

    }

    // 자기보다 큰 수가 없는 거랑 => -1
    // 0번째 idx인거랑 구분해야함 => 0
    static int dfs(int origin, int idx) {

        // 나보다 커? return
        if (arr[idx][0] >= origin) {
            return idx;
        }

        // 첫번째꺼가지 작으면
        if (arr[idx][0] < origin && idx == 0)
            return -1;

        int k = 0;
        // 나보다 작아? 더 들어가
        if (arr[idx][0] < origin && arr[idx][1] != 0) {
            k = dfs(origin, arr[idx][1]);
        }

        if (arr[idx][1] == 0) {
            return 0;
        }

        return k;
    }
}
// 78 1 2 3 1 1 1
// 0 1 1 1 4 5 6
