package interviewQuestions;

import java.util.HashMap;
import java.util.Map;

/* **Easy**   1. Two Sum
 * 
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they 
 * add up to target.
   You may assume that each input would have exactly one solution, and you may not use the same element twice.
   You can return the answer in any order.

	Example 1:
		Input: nums = [2,7,11,15], target = 9
		Output: [0,1]
		Output: Because nums[0] + nums[1] == 9, we return [0, 1].
		
	Example 2:
		Input: nums = [3,2,4], target = 6
		Output: [1,2]
		
	Example 3:
		Input: nums = [3,3], target = 6
		Output: [0,1]
		
	Constraints:
	2 <= nums.length <= 103
	-109 <= nums[i] <= 109
	-109 <= target <= 109
	Only one valid answer exists.
 */
public class TwoSum {

	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		int[] result = new int[2];
		for(int i=0;i<nums.length;i++) {
			if(map.containsKey(target-nums[i])) {
				result[0] = i;
				result[1] = map.get(target-nums[i]);
				break;
			} else {
				map.put(nums[i], i);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		TwoSum twoSum = new TwoSum();
		int[] nums1 = {2,7,11,15};
		int target1 = 9;
		int[] result1 = twoSum.twoSum(nums1, target1);
		System.out.println("Result 1 : ["+result1[0]+","+result1[1]+"]");
		int[] nums2 = {3,2,4};
		int target2 = 6;
		int[] result2 = twoSum.twoSum(nums2, target2);
		System.out.println("Result 2 : ["+result2[0]+","+result2[1]+"]");
		int[] nums3 = {3,3};
		int target3 = 6;
		int[] result3 = twoSum.twoSum(nums3, target3);
		System.out.println("Result 3 : ["+result3[0]+","+result3[1]+"]");
	}

}
