package interviewQuestions;

import java.util.ArrayList;
import java.util.List;

/* **Hard**   1569. Number of Ways to Reorder Array to Get Same BST
 * 
 * Given an array nums that represents a permutation of integers from 1 to n. We are going to construct a 
 * binary search tree (BST) by inserting the elements of nums in order into an initially empty BST. 
 * Find the number of different ways to reorder nums so that the constructed BST is identical to that formed
 *  from the original array nums.

   For example, given nums = [2,1,3], we will have 2 as the root, 1 as a left child, and 3 as a right child.
    The array [2,3,1] also yields the same BST but [3,2,1] yields a different BST.

   Return the number of ways to reorder nums such that the BST formed is identical to the original BST 
   formed from nums.

   Since the answer may be very large, return it modulo 10^9 + 7.
   
   Example 1:
					2
				   / \
				  1   3

		Input: nums = [2,1,3]
		Output: 1
		Explanation: We can reorder nums to be [2,3,1] which will yield the same BST. 
		             There are no other ways to reorder nums which will yield the same BST.
		             
 	Example 2:
			       3
			      / \
			     1   4
			      \   \
			       2   5
			       
		Input: nums = [3,4,5,1,2]
		Output: 5
		Explanation: The following 5 arrays will yield the same BST: 
						[3,1,2,4,5]
						[3,1,4,2,5]
						[3,1,4,5,2]
						[3,4,1,2,5]
						[3,4,1,5,2]
						
	Example 3:
					 1
					  \
					   2
					    \
					     3

		Input: nums = [1,2,3]
		Output: 0
		Explanation: There are no other orderings of nums that will yield the same BST.
		
	Example 4:
					 3
				   /   \ 
                  1     5
                   \   / \ 
                    2 4   6

		Input: nums = [3,1,2,5,4,6]
		Output: 19
		
	Example 5:
		Input: nums = [9,4,2,1,3,6,5,7,8,14,11,10,12,13,16,15,17,18]
		Output: 216212978
		Explanation: The number of ways to reorder nums to get the same BST is 3216212999. 
					 Taking this number modulo 10^9 + 7 gives 216212978.

	Constraints:
		1 <= nums.length <= 1000
		1 <= nums[i] <= nums.length
		All integers in nums are distinct.
 */
public class NoOfWaysToReorderArrayToGetSameBST {
	
	long mod = 1000000007;
    public int numOfWays(int[] nums) {
    	long[][] dp = getYang(nums.length);
        List<Integer> list = new ArrayList<>();      
        for (int n : nums) list.add(n);
        return (int) (dfs(list, dp) - 1);
    }
    
    private long dfs(List<Integer> list, long[][] dp) {
        if (list.size() < 3) return 1;
        int root = list.get(0);
        List<Integer> left = new ArrayList<>(), right = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < root) 
            	left.add(list.get(i));
            else 
            	right.add(list.get(i));
        }
        return dp[left.size() + right.size()][left.size()] % mod
        		* dfs(left,dp) % mod * dfs(right,dp) % mod;
    }
    
    private long[][] getYang(int n) {
    	// Yang Hui (Pascle) triangle
        // 4C2 = triangle[4][2] = 6
        long[][] res = new long[n + 1][n + 1];
        for (int i = 1; i <= n; i++) res[i][0] = res[i][i] = 1;
        for (int i = 2; i <= n; i++) 
            for (int j = 1; j < i; j++) 
                res[i][j] = (res[i - 1][j] + res[i - 1][j - 1]) % mod; 
        return res;
    }

	public static void main(String[] args) {
		NoOfWaysToReorderArrayToGetSameBST reorderArrayBST = new NoOfWaysToReorderArrayToGetSameBST();
		int[] nums1 = {2,1,3};
		System.out.println("No of ways nums1 :"+reorderArrayBST.numOfWays(nums1));
		int[] nums2 = {3,4,5,1,2};
		System.out.println("No of ways nums2 :"+reorderArrayBST.numOfWays(nums2));
		int[] nums3 = {10,23,12,18,4,29,2,8,41,31,25,21,14,35,26,5,19,43,22,37,9,20,44,28,1,39,30,38,36,6,13,16,27,17,34,7,15,3,11,24,42,33,40,32};
		System.out.println("No of ways nums3 :"+reorderArrayBST.numOfWays(nums3));
		int[] nums4 = {3,1,2,5,4,6};
		System.out.println("No of ways nums4 :"+reorderArrayBST.numOfWays(nums4));
		int[] nums5 = {9,4,2,1,3,6,5,7,8,14,11,10,12,13,16,15,17,18};
		System.out.println("No of ways nums5 :"+reorderArrayBST.numOfWays(nums5));
	}

}
