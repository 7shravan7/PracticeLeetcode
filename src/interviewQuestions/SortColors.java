package interviewQuestions;

/* **Medium**   75. Sort Colors
 * 
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent,
 *  with the colors in the order red, white, and blue.

	We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

	You must solve this problem without using the library's sort function.

	Example 1:
		Input: nums = [2,0,2,1,1,0]
		Output: [0,0,1,1,2,2]
		
	Example 2:
		Input: nums = [2,0,1]
		Output: [0,1,2]
		
	Example 3:
		Input: nums = [0]
		Output: [0]
		
	Example 4:
		Input: nums = [1]
		Output: [1]

	Constraints:
		n == nums.length
		1 <= n <= 300
		nums[i] is 0, 1, or 2.

	Follow up: Could you come up with a one-pass algorithm using only constant extra space?
 */
public class SortColors {
	
	private void swap(int[] nums, int index1, int index2) {
		int temp = nums[index1];
		nums[index1] = nums[index2];
		nums[index2] = temp;
	}
	
	/*
	 * Time Complexity : O(n)
	 * Space Complexity: O(1)
	 */
	public void sortColors(int[] nums) {
		int len = nums.length;
		if(len==1) { // base case
			return;
		}
		int zeroIndex=0;
		int oneIndex=0;
		int twoIndex = len-1;
		while(oneIndex<=twoIndex) {
			switch(nums[oneIndex]) {
				case 0 :
					if(zeroIndex!=oneIndex) {
						swap(nums, zeroIndex, oneIndex);
					}
					zeroIndex++;
					oneIndex++;
					break;
				case 1 :
					oneIndex++;
					break;
				case 2 :
					if(twoIndex!=oneIndex) {
						swap(nums, twoIndex, oneIndex);
					}
					twoIndex--;
					break;
			}
		}
	}
	
	public static void printResult(int[] nums) {
		for(int num:nums) {
			System.out.print(num+" ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		SortColors sort = new SortColors();
		int[] nums1 = {2,0,2,1,1,0};
		sort.sortColors(nums1);
		printResult(nums1);
		int[] nums2 = {2,0,1};
		sort.sortColors(nums2);
		printResult(nums2);
		int[] nums3 = {0};
		sort.sortColors(nums3);
		printResult(nums3);
		int[] nums4 = {1};
		sort.sortColors(nums4);
		printResult(nums4);
		int[] nums5 = {2};
		sort.sortColors(nums5);
		printResult(nums5);
	}

}
