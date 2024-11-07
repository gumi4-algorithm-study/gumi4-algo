import java.util.*;
import java.io.*;

class Solution {
    
    static Queue<Integer> q1 = new ArrayDeque<>();
    static Queue<Integer> q2 = new ArrayDeque<>();
    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        long sum1 = 0, sum2 = 0;
        for (int q : queue1) {
            sum1 += q;
            q1.offer(q);
        }
        for (int q : queue2) {
            sum2 += q;
            q2.offer(q);
        }
        

        long sum = sum1 + sum2;
        if (sum % 2 != 0) // 원소 합이 홀수라면 불가능
            return -1;
        
        long total = sum / 2; // 각 큐의 합이 되어야 하는 값 
        answer = makeAnswer(sum1, sum2, total, q1.size() + q2.size());
        return answer;
    }
    
    static int makeAnswer(long sum1, long sum2, long total, int size) {
        int cnt = 0;
        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (cnt > (size * 2))
                break;
            
            if (sum1 == total)
                return cnt;
            
            if (sum1 > total) { // total 값보다 크면 q1에서 poll 하기
                int num = q1.poll();
                q2.offer(num);
                sum1 -= num;
            } else if (sum1 < total) { // total 값보다 작으면 q2에서 poll 하기
                int num = q2.poll();
                q1.offer(num);
                sum1 += num;
            }
            cnt++;
        }
        return -1;
    }
}
