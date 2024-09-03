import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static int row, col;
    static int[][] map;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/input.txt"));
        Scanner sc = new Scanner(System.in);

        col = sc.nextInt();
        row = sc.nextInt();
        int n = sc.nextInt();

        map = new int[n + 1][2]; // 상점들과 동근이의 위치를 저장

        for (int i = 0; i <= n; i++) {
            map[i][0] = sc.nextInt(); // 방향
            map[i][1] = sc.nextInt(); // 위치
        }

        int distance = 0;
        int[] myPos = map[n]; // 동근이 위치

        for (int i = 0; i < n; i++) {
            distance += getDistance(myPos, map[i]);
        }

        System.out.println(distance);
    }

    // 서쪽방향부터 오른쪽방향으로 직사각형을 선형으로 펼친다.
    static int getDistance(int[] pos1, int[] pos2) {
        int dist1 = Math.abs(getLinearPosition(pos1) - getLinearPosition(pos2));
        int dist2 = 2 * (row + col) - dist1;
        System.out.println(dist1 + " " + dist2);
        return Math.min(dist1, dist2);
    }

    // 선형 좌표 확인
    static int getLinearPosition(int[] pos) {
        int direction = pos[0], location = pos[1];
        switch (direction) {
            case 1:
                return location; // 북
            case 2:
                return col + row + col - location; // 남
            case 3:
                return col + row + col + row - location; // 서
            case 4:
                return col + location; // 동
            default:
                return -1;
        }
    }
}