package _2021.June_Challenges.week_2_8to14;

import java.util.Deque;
import java.util.LinkedList;

/* **Medium**       1696. Jump Game VI
 * 
 * You are given a 0-indexed integer array nums and an integer k.

   You are initially standing at index 0. In one move, you can jump at most k steps forward without going 
   outside the boundaries of the array. 
   That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.

   You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for 
   each index j you visited in the array.

   Return the maximum score you can get.

	Example 1:
		Input: nums = [1,-1,-2,4,-7,3], k = 2
		Output: 7
		Explanation: You can choose your jumps forming the subsequence [1,-1,4,3] (underlined above). 
					 The sum is 7.
					 
	Example 2:
		Input: nums = [10,-5,-2,4,0,3], k = 3
		Output: 17
		Explanation: You can choose your jumps forming the subsequence [10,4,3] (underlined above). 
					 The sum is 17.
					 
	Example 3:
		Input: nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
		Output: 0

	Constraints:
		1 <= nums.length, k <= 105
		-104 <= nums[i] <= 104
 */
public class JumpGameVI {
	
	/* sliding window with deque
	 * Time Complexity : O(n)
	 * Space Complexity: O(n)
	 */
	public int maxResultSlidingWindow(int[] nums, int k) {
		int len = nums.length;
		Deque<Integer> deque = new LinkedList<>();
		int[] score = new int[len];
		score[0] = nums[0];
		deque.offerLast(0);
		for(int i=1;i<len;i++) {
			while(!deque.isEmpty() && deque.peekFirst()<i-k) {
				deque.pollFirst();
			}
			score[i] = score[deque.peekFirst()]+nums[i];
			while(!deque.isEmpty() && score[deque.peekLast()]<score[i]) {
				deque.pollLast();
			}
			deque.offerLast(i);
		}
		return score[len-1];
	}

	
	/* sliding window optimizied space
	 * Time Complexity : O(n)
	 * Space Complexity: O(1)
	 */
	public int maxResult(int[] nums, int k) {
		int len = nums.length;
		Deque<int[]> deque = new LinkedList<>();
		int score = nums[0];
		deque.offerLast(new int[] {0,score});
		for(int i=1;i<len;i++) {
			while(!deque.isEmpty() && deque.peekFirst()[0]<i-k) {
				deque.pollFirst();
			}
			score = deque.peekFirst()[1]+nums[i];
			while(!deque.isEmpty() && deque.peekLast()[1]<score) {
				deque.pollLast();
			}
			deque.offerLast(new int[] {i, score});
		}
		return score;
	}

	public static void main(String[] args) {
		JumpGameVI jumpGame = new JumpGameVI();
		int[] nums1 = {1,-1,-2,4,-7,3};
		int k1=3;
		System.out.println("Max Result of nums1 SW: "+jumpGame.maxResultSlidingWindow(nums1, k1));
		System.out.println("Max Result of nums1 SW optimized: "+jumpGame.maxResult(nums1, k1));
		int[] nums2 = {10,-5,-2,4,0,3};
		int k2=2;
		System.out.println("Max Result of nums2 SW: "+jumpGame.maxResultSlidingWindow(nums2, k2));
		System.out.println("Max Result of nums2 SW optimized: "+jumpGame.maxResult(nums2, k2));
		int[] nums3 = {1,-5,-20,4,-1,3,-6,-3};
		int k3=2;
		System.out.println("Max Result of nums3 SW: "+jumpGame.maxResultSlidingWindow(nums3, k3));
		System.out.println("Max Result of nums3 SW optimized: "+jumpGame.maxResult(nums3, k3));
	}

}
