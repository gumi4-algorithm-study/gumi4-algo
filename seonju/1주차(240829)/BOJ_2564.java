import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ_2564 경비원
public class BOJ_2564 {
    static int w, h;    // 가로, 세로 길이
    static int store;   // 상점 개수

    static int[] d;

    static int[] distance;    //거리

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        store = Integer.parseInt(br.readLine());
        //동근 위치
        d = new int[2];
        distance = new int[store + 1];  //상점+동근 위치 저장
        int result = 0;
        int total_distance = 2 * (w + h);

        //상점 + 동근이 위치 저장
        for (int i = 0; i <= store; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int dis = 0;
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            //위치 저장
            if (x == 1) {       //북
                dis = y;
            } else if (x == 2) {    //남
                dis = 2 * w + h - y;
            } else if (x == 3) {    //서
                dis = 2 * (w + h) - y;
            } else {    //동
                dis = w + y;
            }
            distance[i] = dis;
        }

        int dong = distance[store]; //동근이 위치

        for (int i = 0; i < store; i++) {
            int c = Math.abs(dong - distance[i]);   //동근이 위치 - 가게 위치(시계방향)
            int cc = Math.abs(total_distance - c);  //시계 반대방향
            result += Math.min(c, cc);  //최소값 더하기
        }
        System.out.println(result);
    }

}