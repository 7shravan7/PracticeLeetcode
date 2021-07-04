package codes.LeetCode.april2020Challenges.week3_15to21;

/*
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
  (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
  You are given a target value to search. If found in the array return its index, otherwise return -1.
  You may assume no duplicate exists in the array.
  Your algorithm's runtime complexity must be in the order of O(log n).
  
  Example 1:
	Input: nums = [4,5,6,7,0,1,2], target = 0
	Output: 4
  Example 2:
	Input: nums = [4,5,6,7,0,1,2], target = 3
	Output: -1
 */
public class SearchSortedRotatedArray {
	
	public int binarySearch(int[] nums, int start, int end, int target) {
		if(start>end) {
			return -1;
		} else {
			int mid = (start+end)/2;
			if(nums[mid] == target) {
				return mid;
			} else if (nums[start]<=nums[mid]) {
				if(nums[start]<=target && target<nums[mid]) {
					return binarySearch(nums, start, mid-1, target);
				} else {
					return binarySearch(nums, mid+1, end, target);
				}
			} else {
				if(target>nums[mid] && target<=nums[end]) {
					return binarySearch(nums, mid+1, end, target);
				} else {
					return binarySearch(nums, start, mid-1, target);
				}
			}
		}
	}

	public static void main(String[] args) {
		//int[] nums = new int[] {4,5,6,7,0,1,2};
		int[] nums = new int[] {3,1};
		SearchSortedRotatedArray s =new SearchSortedRotatedArray();
		System.out.println(s.binarySearch(nums, 0, nums.length-1, 1));
	}

}
