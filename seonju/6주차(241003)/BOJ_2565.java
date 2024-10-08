import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Pair implements Comparable<Pair> {
    int start;
    int end;

    Pair(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    public int compareTo(Pair o1) { //시작점 기준으로 정렬
        return Integer.compare(this.start, o1.start);
    }
}

public class BOJ_2565 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // 전선 개수
        Pair[] arr = new Pair[N]; // 위치 저장 배열
        int[] dp = new int[N + 1];  //전선 개수만큼 dp
        dp[0] = 1;
        int max = 0;
        for (int i = 0; i < N; i++) {   //위치 저장
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr[i] = new Pair(start, end);
        }
        Arrays.sort(arr);   //시작점 기준 정렬
        for (int i = 0; i < N; i++) {   //기준값
            dp[i] = 1;  //초기값
            for (int j = 0; j < i; j++) { //비교해줄 값
                if (arr[i].end > arr[j].end) {  //겹치지 않을 때
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(N - max);
    }
}
