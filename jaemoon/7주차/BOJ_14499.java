import java.util.Scanner;

public class BOJ_14499 {
	
    static int[] dx = new int[] {0, 0, -1, 1};
    static int[] dy = new int[] {1, -1, 0, 0};
    static int[] dice = new int[6];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int sx = sc.nextInt();
        int sy = sc.nextInt();
        int commandCnt = sc.nextInt();

        int[][] board = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        int[] commands = new int[commandCnt];
        for (int i = 0; i < commandCnt; i++) {
            commands[i] = sc.nextInt();
        }

        for (int command : commands) {
            int nx = sx + dx[command - 1];
            int ny = sy + dy[command - 1];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                continue;
            }

            sx = nx;
            sy = ny;

            moveDice(command - 1);

            if (board[sx][sy] == 0) {
                board[sx][sy] = dice[5];
            } else {
                dice[5] = board[sx][sy];
                board[sx][sy] = 0;
            }

            System.out.println(dice[0]);
        }
    }
    
    static void moveDice(int direction) {
        int[] temp = dice.clone();
        if (direction == 0) {
            dice[0] = temp[3];
            dice[2] = temp[0];
            dice[3] = temp[5];
            dice[5] = temp[2];
            dice[1] = temp[1];
            dice[4] = temp[4];
        } else if (direction == 1) {
            dice[0] = temp[2];
            dice[2] = temp[5];
            dice[3] = temp[0];
            dice[5] = temp[3];
            dice[1] = temp[1];
            dice[4] = temp[4];
        } else if (direction == 2) {
            dice[0] = temp[1];
            dice[1] = temp[5];
            dice[4] = temp[0];
            dice[5] = temp[4];
            dice[2] = temp[2];
            dice[3] = temp[3];
        } else if (direction == 3) {
            dice[0] = temp[4];
            dice[1] = temp[0];
            dice[4] = temp[5];
            dice[5] = temp[1];
            dice[2] = temp[2];
            dice[3] = temp[3];
        }
    }
}

