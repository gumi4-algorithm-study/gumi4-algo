import java.util.*;
import java.io.*;

public class BOJ2493 {
    static int n;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        Stack<int[]> stack = new Stack<>();

        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(st.nextToken());
            if (i == 0) {
                stack.push(new int[] { 0, value });
                sb.append(0).append(" ");
            } else {
                int p = stack.peek()[1];
                if (p >= value) {
                    sb.append(stack.peek()[0] + 1).append(" ");
                    stack.push(new int[] { i, value });
                }
                // 작아? 빼
                else {
                    while (!stack.isEmpty() && (stack.peek()[1] < value)) {
                        stack.pop();
                    }
                    if (stack.isEmpty()) {
                        sb.append(0).append(" ");
                    } else {
                        sb.append(stack.peek()[0] + 1).append(" ");
                    }
                    stack.push(new int[] { i, value });
                }
            }
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

}
