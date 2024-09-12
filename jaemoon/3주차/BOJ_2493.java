import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2493 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 남은 일수
        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        // 입력 배열 초기화
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            // 스택이 빌 때까지
            while (!stack.isEmpty()) {
                // 스택 맨위 값이 현재 값보다 큰 경우
                if (stack.peek()[1] >= arr[i]) {
                    // 수신하는 탑 정보 추가
                    sb.append(stack.peek()[0] + 1).append(" ");
                    break;
                }
                // 맨앞 제거
                stack.pop();
            }

            // 스택이 빈 경우
            if (stack.isEmpty()) {
                // 0 추가
                sb.append(0).append(" ");
            }

            // 탑정보 삽입
            stack.push(new int[] {i, arr[i]});
        }

        // 정답 출력
        System.out.println(sb);
    }
}

