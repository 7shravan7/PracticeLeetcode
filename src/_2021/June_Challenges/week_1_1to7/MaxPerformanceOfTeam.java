package _2021.June_Challenges.week_1_1to7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/* 
 * You are given two integers n and k and two integer arrays speed and efficiency both of length n. 
 * There are n engineers numbered from 1 to n. speed[i] and efficiency[i] represent the speed and 
 * efficiency of the ith engineer respectively.

   Choose at most k different engineers out of the n engineers to form a team with the maximum performance.

   The performance of a team is the sum of their engineers' speeds multiplied by the minimum efficiency 
   among their engineers.

   Return the maximum performance of this team. Since the answer can be a huge number, 
   return it modulo 109 + 7.

   Example 1:
	Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
	Output: 60
	Explanation: 
		We have the maximum performance of the team by selecting engineer 
		2 (with speed=10 and efficiency=4) and engineer 5 (with speed=5 and efficiency=7). 
		That is, performance = (10 + 5) * min(4, 7) = 60.
		
  Example 2:
	Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
	Output: 68
	Explanation:
	This is the same example as the first but k = 3. We can select engineer 1, engineer 2 and engineer 5 
	to get the maximum performance of the team. That is, performance = (2 + 10 + 5) * min(5, 4, 7) = 68.
	
  Example 3:
	Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
	Output: 72
	
	Constraints:
		1 <= <= k <= n <= 105
		speed.length == n
		efficiency.length == n
		1 <= speed[i] <= 105
		1 <= efficiency[i] <= 108
 */
public class MaxPerformanceOfTeam {
	
	class Engineer {
		int speed;
		int efficiency;
		Engineer(int speed, int efficiency){
			this.speed = speed;
			this.efficiency= efficiency;
		}
	}
	
	/*
	 * Time Complexity : O(nlogn + nlogk) n-no.of.engineers (nlogn- to sort, nlogk to build heap
	 * 												(push and pop) n times)
	 * Space Complexity: O(n+k)
	 */
	public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int modulo = (int) Math.pow(10, 9) + 7;
        List<Engineer> engineerList = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
        	engineerList.add(new Engineer(speed[i],efficiency[i]));
        }
        // sort the members by their efficiencies
        Collections.sort(engineerList, Comparator.comparing(o -> -o.efficiency));

        // create a heap to keep the top (k-1) speeds
        PriorityQueue<Integer> speedHeap = new PriorityQueue<>((o1, o2) -> o1 - o2);

        long speedSum = 0, perf = 0;
        for (Engineer e : engineerList) {
            Integer currEfficiency = e.efficiency;
            Integer currSpeed = e.speed;
            // maintain a heap for the fastest (k-1) speeds
            if (speedHeap.size() > k - 1) {
                speedSum -= speedHeap.remove();
            }
            speedHeap.add(currSpeed);

            // calculate the maximum performance with
            // the current member as the least efficient one in the team
            speedSum += currSpeed;
            perf = Math.max(perf, speedSum * currEfficiency);
        }
        return (int) (perf % modulo);
    }

	public static void main(String[] args) {
		MaxPerformanceOfTeam maxPerformanceTeam = new MaxPerformanceOfTeam();
		int n1 = 6;
		int[] speed1= {2,10,3,1,5,8};
		int[] efficiency1= {5,4,3,9,7,2};
		int k1 = 2;
		System.out.println("Max Performance of test case 1 :"
		                        +maxPerformanceTeam.maxPerformance(n1, speed1, efficiency1, k1));
		int n2 = 6;
		int[] speed2= {2,10,3,1,5,8};
		int[] efficiency2= {5,4,3,9,7,2};
		int k2 = 3;
		System.out.println("Max Performance of test case 2 :"
		                        +maxPerformanceTeam.maxPerformance(n2, speed2, efficiency2, k2));
		int n3 = 6;
		int[] speed3= {2,10,3,1,5,8};
		int[] efficiency3= {5,4,3,9,7,2};
		int k3 = 4;
		System.out.println("Max Performance of test case 3 :"
		                        +maxPerformanceTeam.maxPerformance(n3, speed3, efficiency3, k3));
	}
	
}
