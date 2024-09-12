import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ_2493 탑
public class BOJ_2493 {
    static int N;
    static Stack<Integer> stack;
    static int[] arr;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        stack = new Stack<>();
        arr = new int[N];
        result = new int[N];

        // 입력
        for (int i = 0; i < N; i++) {
            int t = Integer.parseInt(st.nextToken());
            arr[i] = t;
        }

        for (int i = 0; i < N; i++) { // 탑의 개수만큼
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }

            if (!stack.isEmpty()) { // 큰 것만 남음
                result[i] = stack.peek() + 1;
            }

            stack.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int val : result) {
            sb.append(val).append(" ");
        }
        System.out.println(sb);

    }
}
