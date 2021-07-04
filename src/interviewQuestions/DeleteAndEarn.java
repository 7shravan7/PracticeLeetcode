package interviewQuestions;

/* **Medium**  740. Delete and Earn
 * 
 * Given an array nums of integers, you can perform operations on the array.

   In each operation, you pick any nums[i] and delete it to earn nums[i] points. 
   After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.

   You start with 0 points. Return the maximum number of points you can earn by applying such operations.

	Example 1:
		Input: nums = [3,4,2]
		Output: 6
		Explanation: Delete 4 to earn 4 points, consequently 3 is also deleted.
					 Then, delete 2 to earn 2 points.
					 6 total points are earned.
					 
	Example 2:
		Input: nums = [2,2,3,3,3,4]
		Output: 9
		Explanation: Delete 3 to earn 3 points, deleting both 2's and the 4.
					 Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
					 9 total points are earned.

	Constraints:
		1 <= nums.length <= 2 * 10^4
		1 <= nums[i] <= 10^4
 */
public class DeleteAndEarn {
	
	/*
	 * Time : 2ms
	 * Time Complexity : We performed a radix sort instead, so our complexity is O(N+W) where 
	 * 						W is the range of allowable values for nums[i].
	 * Space Complexity: O(W+W) size of count Arr and dpArr
	 */
	public int deleteAndEarn(int[] nums) {
		int n = 10001;
		int[] countArr = new int[n];
		for(int i=0;i<nums.length;i++) {
			countArr[nums[i]] += 1;
		}
		// after building count arr it will be like for nums ={3,4,2} countArr = {0,0,1,1,1} ;; 
		// eg for nums ={2,2,3,3,3,4} countArr = {0,0,2,3,1}
		// then it is a House robber kind of problem where if u delete i, i-1 and i+1 are deleted automatically
		// so i -> Math.max(i*count[i]+result[i-2], result[i-1]) ..
		// since if we delete i-1 , i will be deleted automatically and wont exist in nums
		// only if i-2 is deleted and earned, i will be there 
		int[] dp = new int[n];
		dp[0] = countArr[0];
		dp[1] = countArr[1];
		for(int i=2;i<n;i++) {
			if(countArr[i]>0) {
				dp[i] = Math.max(dp[i-1], dp[i-2]+countArr[i]*i);
			} else { // if i is not in nums then we can't delete so retaining prev val
				dp[i] = dp[i-1];
			}
		}
		return dp[n-1];
	}
	
	/*
	 * Time : 4ms
	 * Time Complexity : We performed a radix sort instead, so our complexity is O(N+W) where 
	 * 						W is the range of allowable values for nums[i].
	 * Space Complexity: O(W) by using 2 variables instead of dp
	 */
	public int deleteAndEarnOptimized(int[] nums) {
		int[] count = new int[10001];
		for (int x: nums) {
			count[x]++;
		}
		int avoid = 0, using = 0, prev = -1;
		for (int i = 0; i <= 10000; i++) { 
			if (count[i] > 0) {
				int m = Math.max(avoid, using);
				if (i - 1 != prev) {
					using = i * count[i] + m;
					avoid = m;
				} else {
					using = i * count[i] + avoid;
					avoid = m;
				}
				prev = i;
			}
		}
		return Math.max(avoid, using);
	}

	public static void main(String[] args) {
		DeleteAndEarn deleteAndEarn = new DeleteAndEarn();
		int[] nums1 = {3,4,2};
		System.out.println("Max points earned for nums1 :"+deleteAndEarn.deleteAndEarnOptimized(nums1));
		int[] nums2 = {2,2,3,3,3,4};
		System.out.println("Max points earned for nums2 :"+deleteAndEarn.deleteAndEarnOptimized(nums2));
	}

}
