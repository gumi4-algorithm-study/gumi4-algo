import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;  // 전깃줄 개수
    static int[] dp;
    static ArrayList<int[]> map = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        Collections.sort(map, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]);
            }
        });  // a 기준 정렬

        for (int[] ints : map) {
            System.out.println(Arrays.toString(ints));
        }

        dp = new int[N];

        int maxLength = 0;
        for (int i = 0; i < N; i++) {
            dp[i] = 1;  // 자기 자신만 포함하는 경우
            for (int j = 0; j < i; j++) {
                if (map.get(i)[1] > map.get(j)[1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }

        System.out.println(N - maxLength);  // 전체 전깃줄 개수에서 가장 긴 증가하는 부분 수열의 길이를 뺌
    }
}