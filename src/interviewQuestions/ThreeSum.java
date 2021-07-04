package interviewQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* **Medium**     15. 3Sum
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and 
 * j != k, and nums[i] + nums[j] + nums[k] == 0.

 Notice that the solution set must not contain duplicate triplets.

	Example 1:
			Input: nums = [-1,0,1,2,-1,-4]
			Output: [[-1,-1,2],[-1,0,1]]
			
	Example 2:
			Input: nums = []
			Output: []
			
	Example 3:
			Input: nums = [0]
			Output: []
			
	Constraints:
		0 <= nums.length <= 3000
		-105 <= nums[i] <= 105
 */
public class ThreeSum {

	public List<List<Integer>> threeSum(int[] nums) {
		Set<List<Integer>> res = new HashSet<>();
		Set<Integer> dups = new HashSet<>();
		Map<Integer, Integer> seen = new HashMap<>();
		for (int i = 0; i < nums.length; ++i)
			if (dups.add(nums[i])) { // to avoid duplicate element processing to avoid duplicate  results
				for (int j = i + 1; j < nums.length; ++j) {
					int complement = - (nums[i] + nums[j]);
					if (seen.containsKey(complement) && seen.get(complement) == i) {
						List<Integer> triplet = Arrays.asList(nums[i], nums[j], complement);
						Collections.sort(triplet);
						res.add(triplet);  // sort and adding so that set removes duplicates
					}
					seen.put(nums[j], i);
				}
			}
		return new ArrayList(res);
	}

	public static void main(String[] args) {
		ThreeSum threeSum = new ThreeSum();
		int[] nums1 = {-1,0,-1,2,-1,-4};
		List<List<Integer>> result1 = threeSum.threeSum(nums1);
	}

}
