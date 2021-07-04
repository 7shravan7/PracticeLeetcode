package interviewQuestions;

/* **Medium**     33. Search in Rotated Sorted Array
 * 
 * There is an integer array nums sorted in ascending order (with distinct values).

   Prior to being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that
   the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). 
   For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

   Given the array nums after the rotation and an integer target, return the index of target if it is in nums, or -1 
   if it is not in nums.

   You must write an algorithm with O(log n) runtime complexity.

   	Example 1:
		Input: nums = [4,5,6,7,0,1,2], target = 0
		Output: 4
		
	Example 2:
		Input: nums = [4,5,6,7,0,1,2], target = 3
		Output: -1
		
	Example 3:
		Input: nums = [1], target = 0
		Output: -1

	Constraints:
		1 <= nums.length <= 5000
		-104 <= nums[i] <= 104
		All values of nums are unique.
		nums is guaranteed to be rotated at some pivot.
		-104 <= target <= 104
 */
public class SearchInSortedRotatedArray {
	
	/*
	 * Time Complexity : O(log n)
	 */
	public int search(int[] nums, int target) {
		int len = nums.length;
		int low = 0;
		int high = len-1;
		while(low<=high) {
			int mid = low + (high-low)/2;
			if(nums[mid]==target) {
				return mid;
			}
			if(nums[low]<=nums[mid]) {
				if(nums[low]<=target && target<nums[mid]) {
					high = mid-1;
				} else {
					low = mid+1;
				}
			} else {
				if(nums[mid]<target && target<=nums[high]) {
					low = mid+1;
				} else {
					high = mid-1;
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		SearchInSortedRotatedArray searchInSortedRotatedArr = new SearchInSortedRotatedArray();
		int[] nums1 = {4,5,6,7,0,1,2};
		int target1 = 0;
		System.out.println(target1+" index in nums1 : "+searchInSortedRotatedArr.search(nums1, target1)); // 4
		int[] nums2 = {4,5,6,7,0,1,2};
		int target2 = 3;
		System.out.println(target2+" index in nums2 : "+searchInSortedRotatedArr.search(nums2, target2)); // -1
		int[] nums3 = {1};
		int target3 = 1;
		System.out.println(target3+" index in nums3 : "+searchInSortedRotatedArr.search(nums3, target3)); // -1
		int[] nums4 = {6,7,0,1,2,4,5};
		int target4 = 0;
		System.out.println(target4+" index in nums4 : "+searchInSortedRotatedArr.search(nums4, target4)); // 2
		int[] nums5 = {5,1,3};
		int target5 = 3;
		System.out.println(target5+" index in nums5 : "+searchInSortedRotatedArr.search(nums5, target5)); // 2
	}

}
