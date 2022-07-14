package twilio_may2022_interview;

import java.util.ArrayDeque;
import java.util.Deque;

/*
You are given an integer array bloomDay, an integer m and an integer k.
You want to make m bouquets. To make a bouquet, you need to use k adjacent flowers from the
garden.

The garden consists of n flowers, the ith flower will bloom in the bloomDay[i] and then can
be used in exactly one bouquet.

Return the minimum number of days you need to wait to be able to make m bouquets from the
garden. If it is impossible to make m bouquets return -1.

Example:
bloomDay = [1,10,3,10,2], m = 3, k = 1, d=3 => true
Answer:After day 1: [x, _, _, _, _]   // we can only make one bouquet.
After day 2: [x, _, _, _, x]   // we can only make two bouquets.
After day 3: [x, _, x, _, x]   // we can make 3 bouquets. The answer is 3.

Result would be 3


Example: 2
bloomDay = [1,10,3,10,2,5, 6], m = 3, k = 2, d=10 ---> true

m1 = [1,10] -> 10
m2 = [3,10] -> 10
m3 = [2,5] -> 5

[10,4,3,10,2,5,6] m=3 k=2, d=5-->true
[4,3]
[2,5]

1,10 | 3,10 | 2,5 | 6

1,10 [3,10] [2,5] [5,6]
 10,3
     3,10


[X,_,X,_,X,X,X]
Result would be 10



n=7 noOfWds = n-k+1;
Bl : 1,10,3,10,2,5,6
idx: 0, 1,2, 3,4,5,6
6-2
[idx,val]
[1,10]-> [3,10]
10  10
k=3
1,10,3 10,3,10 3,10,2 10,2,5 2,5,6
 10.      10.    10.    10.    6

k=2
1,10 10,3 3,10 10,2 2,5 5,6
10    10.  10.  10.  5.  6

k=1
1 10 3 10 2
1 10 3 10 2

*/
public class MinNumberWaitDays {

    public int findMinDays(int[] bloomDays, int m, int k) {
        int left = 0;
        int right = 20;
        while(left<right) {
            int mid = left + (right-left)/2;
            if(isPossible(bloomDays, m, k, mid)){
                right = mid;
            } else {
                left = mid+1;
            }
        }
        return left;
    }

    public boolean isPossible(int[] bloomDays, int m, int k, int d) {
        int numDays = 0;
        Deque<int[]> deque = new ArrayDeque<>();
        int max=0;
        for(int i=0;i<bloomDays.length;i++) {
            int bloomDay = bloomDays[i];
            if(bloomDay>d){
                while (!deque.isEmpty()){
                    deque.remove();
                }
                max=0;
                continue;
            }
            max = Math.max(max, bloomDay);
            /*while(!deque.isEmpty() && deque.peekLast()[1]<bloomDay){
                deque.removeLast();
            }
            while(!deque.isEmpty() && deque.peekFirst()[0]<=i-k){
                deque.removeFirst();
            }*/
            deque.add(new int[]{i, bloomDays[i]});
            if(deque.size()==k) {
                if(max<=d){
                    numDays++;
                     while (!deque.isEmpty()){
                        deque.remove();
                    }
                     max=0;
                }
                if(numDays==m){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        MinNumberWaitDays minNumberWaitDays = new MinNumberWaitDays();
        int[] bloomDays1 = {1,10,3,10,2};
        int m1=3;
        int k1=1;
        int d1=10;
        System.out.println(minNumberWaitDays.isPossible(bloomDays1, m1, k1, d1));
        System.out.println(minNumberWaitDays.findMinDays(bloomDays1, m1, k1));
        int[] bloomDays2 = {1,10,3,10,2,5,6};
        int m2=1;
        int k2=2;
        int d2=5;
        System.out.println(minNumberWaitDays.isPossible(bloomDays2, m2, k2, d2));
        System.out.println(minNumberWaitDays.findMinDays(bloomDays2, m2, k2));
        int[] bloomDays3 = {1,10,3,10,2,5,6};
        int m3=2;
        int k3=3;
        int d3=10;
        System.out.println(minNumberWaitDays.isPossible(bloomDays3, m3, k3, d3));
        System.out.println(minNumberWaitDays.findMinDays(bloomDays3, m3, k3));
    }
}
