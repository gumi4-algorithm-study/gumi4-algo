import java.util.Scanner;

public class BOJ_2564 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 블록의 가로 길이(w)와 세로 길이(h) 입력
        int w = sc.nextInt();
        int h = sc.nextInt();

        // 상점의 수(n) 입력
        int n = sc.nextInt();
        int[] stores = new int[n];

        // 상점들의 위치를 입력 받아서 배열에 저장
        for (int i = 0; i < n; i++) {
            int dir = sc.nextInt();
            int dis = sc.nextInt();
            stores[i] = getPosition(w, h, dir, dis);
        }


        // 경비원의 위치 입력
        int gDir = sc.nextInt();
        int gDis = sc.nextInt();
        int gPosition = getPosition(w, h, gDir, gDis);

        // 총 둘레 길이 계산
        int tLen = 2 * (w + h);

        // 최단 거리 계산
        int tDis = 0;
        for (int store : stores) {
            int dis1 = Math.abs(store - gPosition);
            int dis2 = tLen - dis1;
            tDis += Math.min(dis1, dis2);
        }

        // 결과 출력
        System.out.println(tDis);
    }

    // 위치를 둘레를 따라 일렬로 펼쳐진 직선상의 좌표로 변환하는 함수
    public static int getPosition(int w, int h, int direction, int distance) {
        if (direction == 1) { // 북쪽
            return distance;
        } else if (direction == 2) { // 남쪽
            return 2 * w + h - distance;
        } else if (direction == 3) { // 서쪽
            return 2 * (w + h) - distance;
        } else if (direction == 4) { // 동쪽
            return w + distance;
        }
        return 0; // 올바른 방향이 아닌 경우, 기본값으로 0을 반환
    }
}
