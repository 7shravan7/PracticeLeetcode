package interviewQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* **Medium**        300. Longest Increasing Subsequence
 * 
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.

   A subsequence is a sequence that can be derived from an array by deleting some or no elements without 
   changing the order of the remaining elements. 
   For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].

	Example 1:
		Input: nums = [10,9,2,5,3,7,101,18]
		Output: 4
		Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
		
	Example 2:
		Input: nums = [0,1,0,3,2,3]
		Output: 4
		
	Example 3:
		Input: nums = [7,7,7,7,7,7,7]
		Output: 1

	Constraints:
		1 <= nums.length <= 2500
		-104 <= nums[i] <= 104
 
	Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */
public class LongestIncreasingSubsequence {
	
	/*
	 * Time Complexity : O(n^2)
	 * Space Complexity: O(n)
	 * 
	 * i/p    : 0,1,0,3,2,3
	 * initial: 1,1,1,1,1,1
	 *          j i
	 *          1,2,0,3,2,3
	 *          j   i
	 *          ......
	 * compare: 1,2,1,3,3,4  => max of this arr will give LIS
	 */
	public int lengthOfLISDp(int[] nums) {
		int longestLength = 1;
		int len = nums.length;
		int[] dp = new int[len];
		Arrays.fill(dp, 1);
		for(int i=1;i<len;i++) {
			for(int j=0;j<i;j++) {
				if(nums[i]>nums[j]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
					longestLength = Math.max(longestLength, dp[i]);
				}
			}
		}
		return longestLength;
	}
	
	/*
	 * Time Complexity : O(n^2)
	 * Space Complexity: O(n)
	 * 
	 * i/p    : 10,9,2,5,3,7,101,18
	 * subseq : 10
	 *          9
	 *          2
	 *          2,5
	 *          2,3
	 *          2,3,7
	 *          2,3,7,101
	 *          2,4,7,18
	 * length of subseq is LIS
	 */
	public int lengthOfLIS(int[] nums) {
		int len = nums.length;
		List<Integer> subSeq = new ArrayList<>();
		subSeq.add(nums[0]);
		for(int i=1;i<len;i++) {
			if(subSeq.get(subSeq.size()-1)<nums[i]) {
				subSeq.add(nums[i]);
			} else {
				/*int j=0;
				while(subSeq.get(j)<nums[i]) {
					j++;
				}*/
				// optimized this search using binary search since subseq is already sorted
				// find val which is the smallest element that is greater than or equal to num
				int j = binarySearch(subSeq, nums[i]);  
				subSeq.set(j, nums[i]);
			}
		}
		return subSeq.size();
	}
	
	private int binarySearch(List<Integer> subSeq, int val) {
		int low = 0;
		int high = subSeq.size()-1;
		while(low<high) {
			int mid = low+((high-low)/2);
			if(subSeq.get(mid)>=val) {
				high = mid;
			} else {
				low = mid+1;
			}
		}
		return low;
	}

	public static void main(String[] args) {
		LongestIncreasingSubsequence LIS = new LongestIncreasingSubsequence();
		int[] nums1 = {10,9,2,5,3,7,101,18};
		System.out.println("LIS of nums1 : "+LIS.lengthOfLISDp(nums1));
		System.out.println("LIS of nums1 : "+LIS.lengthOfLIS(nums1));
		int[] nums2 = {0,1,0,3,2,3};
		System.out.println("LIS of nums2 : "+LIS.lengthOfLISDp(nums2));
		System.out.println("LIS of nums2 : "+LIS.lengthOfLIS(nums2));
		int[] nums3 = {7,7,7,7,7,7,7};
		System.out.println("LIS of nums3 : "+LIS.lengthOfLISDp(nums3));
		System.out.println("LIS of nums3 : "+LIS.lengthOfLIS(nums3));
		
	}

}
