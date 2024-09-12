import java.util.Scanner;

public class BOJ_2458 {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        // 학생 수 입력
        int n = sc.nextInt();
        // 키 비교 횟수 입력
        int m = sc.nextInt();

        // 키 비교 결과를 저장할 2차원 배열
        boolean[][] check = new boolean[n][n];

        // 키 비교 데이터를 입력받아 배열에 저장
        for(int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            check[a][b] = true; // a번 학생이 b번 학생보다 키가 작음을 표시
        }

        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    // i번 학생이 k번 학생보다 키가 작고, k번 학생이 j번 학생보다 키가 작은 경우
                    if(check[i][k] && check[k][j]) {
                        check[i][j] = true;
                    }
                }
            }
        }

        // 각 학생마다 몇 개의 비교 관계를 알고 있는지 저장할 배열
        int[] cnt = new int[n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                // i번 학생이 j번 학생보다 작거나, j번 학생이 i번 학생보다 작은 경우
                if(check[i][j] || check[j][i]) {
                    cnt[i]++;
                }
            }
        }

        // 정확한 순위를 알 수 있는 학생의 수를 계산
        int result = 0;
        for(int num : cnt) {
            // 만약 한 학생에 대해 n-1개의 비교 관계를 알고 있는 경우
            if(num == n - 1) result++;
        }

        // 정답 출력
        System.out.println(result);
    }
}
