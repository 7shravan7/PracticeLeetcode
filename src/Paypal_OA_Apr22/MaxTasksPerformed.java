package Paypal_OA_Apr22;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
    You are given a straight line statring at 0 to 10^9. You start at zero and there are n tasks you can perform. ith task is located at point 'i' in the line and requries 't' time to be performed. To perform the task you need to reach the ppoint 'i' and spend 't' time at that location. e.g (5,8) lies at 5 so travel distance is 5 and work effort is 8.
Total effort is calculated as travel distance + time required to complete the work.

It takes one sec to travel one unit of path.

Now we are given total T seconds and we need to complete as many tasks as possible and reach back to starting position
Find the max number of tasks that you can finish in time T.
e.g
3 16 -> 3 tasks and 16 units of total time
2 8 -> task 1 at position 2 in line and takes 8 sec to complete
4 5 -> task 2 at position 4 in line and takes 5 sec to complete
5 1 -> task 3 at position 5 in line and takes 1 sec to complete
Output : 2

Explanation :
If we take task 1 at location 2 which requires 8 sec then getting to location 2 takes 2s and completing the task takes 8s leaving us with only 6s which is not enough for completing other task

On the other hand skipping the fist task leaves us enough time to complete the other two tasks.

Going to location and coming back costs 2x5 =10s and performing task at location 4 and 5 cost us 5+1 = 6s. Total time spent will be 10s+6s=16s.
 */
public class MaxTasksPerformed {

    static int solve(int n, int t, int[][] task){
        PriorityQueue<Integer> pQueue= new PriorityQueue<>(Collections.reverseOrder());
        Arrays.sort(task, Comparator.comparingInt(o->o[0]));
        int pQueueSum=0;
        int max=0;
          for(int i=0;i<n;i++){
            int totalTime =t;
             int distance = 2*task[i][0];
              int remainingTime=totalTime-distance;
             int currEffort=task[i][1];
               if(remainingTime<0){
                break;
            }
            while(pQueueSum>remainingTime){
                pQueueSum-=pQueue.poll();
            }
               if(pQueue.isEmpty()&&remainingTime>currEffort){
                pQueue.add(currEffort);
                pQueueSum+=currEffort;
            }
            else if(pQueueSum+currEffort<=remainingTime){
                  pQueue.add(currEffort);
                 pQueueSum+=currEffort;
            }
            else{
                Integer currMax=pQueue.peek();
                if(currMax != null && currMax>currEffort){
                    pQueue.poll();
                    pQueue.add(currEffort);
                    pQueueSum=pQueueSum-currMax+currEffort;
                }
            }
             max=Math.max(max,pQueue.size());
        }
         return max;
    }

    public static void main(String[] args) {
        int t= 50;
        int n= 5;
        int[][] task = {{1,15},{3,2},{7, 30},{10,5},{12,4}};
          System.out.println(solve(n, t, task));
      }
}
