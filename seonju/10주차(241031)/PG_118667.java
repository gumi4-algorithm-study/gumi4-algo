import java.util.*;
class PG_118667 {
    static long sum1, sum2;
    public int solution(int[] queue1, int[] queue2) {
        int answer = -1;    // 초기결과

        // 큐 선언
        Queue<Integer> q1 = new LinkedList<Integer>();
        Queue<Integer> q2 = new LinkedList<Integer>();

        sum1 = 0;
        sum2 = 0;

        // q1 저장
        for(int n : queue1){
            q1.add(n);
            sum1 += n;
        }

        // q2 저장
        for(int n : queue2){
            q2.add(n);
            sum2 += n;
        }

        int round = 0;

        while(round <= queue1.length * 4){
            if(sum1 > sum2){    // q2가 q1보다 작을 때 q1에서 빼줌
                int poll = q1.poll();
                sum1 -= poll;
                sum2 += poll;
                q2.add(poll);
            }else if(sum1 < sum2){  // q1이 q2보다 작을 때 q2에서 빼줌
                int poll = q2.poll();
                sum2 -= poll;
                sum1 += poll;
                q1.add(poll);
            }else if(sum1 == sum2){ // 같으면 끝
                answer = round;
                break;
            }
            round++;
        }

        return answer;
    }

}