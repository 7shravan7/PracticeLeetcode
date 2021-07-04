package _2021.June_Challenges.week_1_1to7;

import java.util.Arrays;

/*
 * Given a rectangular cake with height h and width w, and two arrays of integers horizontalCuts and verticalCuts 
 * where horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, 
 * verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.

   Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the 
   arrays horizontalCuts and verticalCuts. 
   
   Since the answer can be a huge number, return this modulo 10^9 + 7.
   
   Example 1:
   	Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
	Output: 4 
	Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts.
	 			 After you cut the cake, the green piece of cake has the maximum area.
	 			 
   Example 2:
	Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
	Output: 6
	Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. 
				 After you cut the cake, the green and yellow pieces of cake have the maximum area.
				 
   Example 3:
	Input: h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
	Output: 9
	
 Constraints:
	2 <= h, w <= 10^9
	1 <= horizontalCuts.length < min(h, 10^5)
	1 <= verticalCuts.length < min(w, 10^5)
	1 <= horizontalCuts[i] < h
	1 <= verticalCuts[i] < w
	It is guaranteed that all elements in horizontalCuts are distinct.
	It is guaranteed that all elements in verticalCuts are distinct.
 */
public class MaxAreaOfCakeAfterCuts {

	public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
		Arrays.sort(horizontalCuts);
		Arrays.sort(verticalCuts);
		int horizontalCutsSize = horizontalCuts.length;
		int verticalCutsSize = verticalCuts.length;
		int horizontalMax = Math.max(horizontalCuts[0]-0, h-horizontalCuts[horizontalCutsSize-1]);
		for(int i=1;i<horizontalCutsSize;i++) {
			horizontalMax = Math.max(horizontalMax, (horizontalCuts[i]-horizontalCuts[i-1]));
		}
		int verticalMax = Math.max(verticalCuts[0]-0, w-verticalCuts[verticalCutsSize-1]);
		for(int i=1;i<verticalCutsSize;i++) {
			verticalMax = Math.max(verticalMax, (verticalCuts[i]-verticalCuts[i-1]));
		}
		long ans = 1L * horizontalMax * verticalMax;
		return  (int) (ans % 1000000007);
	}

	public static void main(String[] args) {
		MaxAreaOfCakeAfterCuts maxAreaOfCuts = new MaxAreaOfCakeAfterCuts();
		int h1=5;
		int w1=4;
		int[] horizontalCuts1 = {1,2,4};
		int[] verticalCuts1 = {1,3};
		System.out.println("Max Area after cuts 1 : "+maxAreaOfCuts.maxArea(h1, w1, horizontalCuts1, verticalCuts1));
		int h2=5;
		int w2=4;
		int[] horizontalCuts2 = {3,1};
		int[] verticalCuts2 = {1};
		System.out.println("Max Area after cuts 2 : "+maxAreaOfCuts.maxArea(h2, w2, horizontalCuts2, verticalCuts2));
		int h3=5;
		int w3=4;
		int[] horizontalCuts3 = {3};
		int[] verticalCuts3 = {3};
		System.out.println("Max Area after cuts 3 : "+maxAreaOfCuts.maxArea(h3, w3, horizontalCuts3, verticalCuts3));
		int h4=50;
		int w4=15;
		int[] horizontalCuts4 = {37,40,41,30,27,10,31};
		int[] verticalCuts4 = {2,1,9,5,4,12,6,13,11};
		System.out.println("Max Area after cuts 3 : "+maxAreaOfCuts.maxArea(h4, w4, horizontalCuts4, verticalCuts4));
	}

}
