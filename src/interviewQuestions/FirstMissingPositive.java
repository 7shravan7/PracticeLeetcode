package interviewQuestions;

import java.util.HashSet;
import java.util.Set;

/* **Hard**    41. First Missing Positive
 * 
 * Given an unsorted integer array nums, find the smallest missing positive integer.

   You must implement an algorithm that runs in O(n) time and uses constant extra space.

	Example 1:
		Input: nums = [1,2,0]
		Output: 3
		
	Example 2:
		Input: nums = [3,4,-1,1]
		Output: 2
		
	Example 3:
		Input: nums = [7,8,9,11,12]
		Output: 1

	Constraints:
		1 <= nums.length <= 5 * 105
		-231 <= nums[i] <= 231 - 1
 */
public class FirstMissingPositive {
	
	/*
	 * There can be positive numbers upto 1 to n+1 range only for n numbers and using set to store elements of num
	 * Time Complexity  : O(2n)=> O(n)
	 * Space Complexity : O(n)
	 */
	public int firstMissingPositiveWithExtraSpace(int[] nums) {
		Set<Integer> set = new HashSet<>();
		int n = nums.length;
		for(int num:nums) {
			if(num>=0 && num<=n) { // we can remove this condition too
				set.add(num);
			}
		}
		for(int i=1;i<n+1;i++) { // this is main case since we have to check only the range from 1 to n+1
			if(!set.contains(i)) {
				return i;
			}
		}
		return n+1;
	}
	
	/*
	 * There can be positive numbers upto 1 to n+1 range only for n numbers
	 * A bit tricky since we should not use any extra space so it leaves us the option to modify input nums
	 * Time Complexity  : O(3n)=> O(n)
	 * Space Complexity : O(1)
	 */
	public int firstMissingPositiveWithConstantSpace(int[] nums) {
		boolean isContains1 = false;
		int n = nums.length;
		// first step is to replace num[i] which are less than 0 or greater than n
		for(int i=0;i<n;i++) {
			if(nums[i] == 1) {
				isContains1 = true;
			} else if(nums[i]<=0 || nums[i]>n){
				nums[i] = 1;
			}
		}
		if(!isContains1) {  // if there is no 1 in input list return 1
			return 1;
		}
		// second step is to negate the numbers which are the indexes of nums since all elements of num would be between
		for(int i=0;i<n;i++) {
			int index = Math.abs(nums[i])-1;
			if(nums[index]>0) {
				nums[index] = -nums[index];
			}
		}
		// third step is find index of first positive number and return index+1
		for(int i=0;i<n;i++) {
			if(nums[i]>0) {
				return i+1;
			}
		}
		return n+1;
	}

	public static void main(String[] args) {
		FirstMissingPositive firstMissingPositive = new FirstMissingPositive();
		int[] nums1 = {1,2,0};
		System.out.println("First Missing positive number in nums1 : "
				+firstMissingPositive.firstMissingPositiveWithExtraSpace(nums1));
		System.out.println("First Missing positive number in nums1 : "
				+firstMissingPositive.firstMissingPositiveWithConstantSpace(nums1));
		int[] nums2 = {3,4,-1,1};
		System.out.println("First Missing positive number in nums2 : "
				+firstMissingPositive.firstMissingPositiveWithExtraSpace(nums2));
		System.out.println("First Missing positive number in nums2 : "
				+firstMissingPositive.firstMissingPositiveWithConstantSpace(nums2));
		int[] nums3 = {7,8,9,11,12};
		System.out.println("First Missing positive number in nums3 : "
				+firstMissingPositive.firstMissingPositiveWithExtraSpace(nums3));
		System.out.println("First Missing positive number in nums3 : "
				+firstMissingPositive.firstMissingPositiveWithConstantSpace(nums3));
		int[] nums4 = {1,2,3,4,5};
		System.out.println("First Missing positive number in nums4 : "
				+firstMissingPositive.firstMissingPositiveWithExtraSpace(nums4));
		System.out.println("First Missing positive number in nums4 : "
				+firstMissingPositive.firstMissingPositiveWithConstantSpace(nums4));
	}

}
