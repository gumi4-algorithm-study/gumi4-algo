import java.util.Scanner;

public class Main {
    
    static int r, c, result;
    static String[][] map;
    static boolean[] visited = new boolean[26];
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        r = sc.nextInt();
        c = sc.nextInt();
        
        map = new String[r][c];
        for (int i = 0; i < r; i++) {
            String input = sc.next();
            for (int j = 0; j < c; j++) {
                map[i][j] = input.charAt(j) + "";
            }
        }
        
        visited[map[0][0].charAt(0) - 'A'] = true;
        result = 0;
        dfs(0, 0, 1);
        System.out.println(result);
    }
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    static void dfs(int x, int y, int d) {
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
                continue;
            }
            
            int charIndex = map[nx][ny].charAt(0) - 'A';
            if (!visited[charIndex]) {
                visited[charIndex] = true;
                dfs(nx, ny, d + 1);
                visited[charIndex] = false;
            }
        }
        
        result = Math.max(result, d);
    }
}
