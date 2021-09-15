package interviewQuestions;

/* **Medium**   31. Next Permutation
 * 
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

   If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending 
   order).

   The replacement must be in place and use only constant extra memory.

	Example 1:
		Input: nums = [1,2,3]
		Output: [1,3,2]
		
	Example 2:
		Input: nums = [3,2,1]
		Output: [1,2,3]
		
	Example 3:
		Input: nums = [1,1,5]
		Output: [1,5,1]
		
	Example 4:
		Input: nums = [1]
		Output: [1]

	Constraints:
		1 <= nums.length <= 100
		0 <= nums[i] <= 100
 */
public class NextPermutation {
	
	/*
	 * first used Arrays.sort : 1ms
	 * Then replaced that with custom reverse func : 0ms
	 */
	public void nextPermutation(int[] nums) {
		int size = nums.length;
		int i=size-1;
		for(;i>0;i--) {
			if(nums[i]>nums[i-1]) {
				break;
			}
		}
		if(i==0) { // no next greater permuation possible so soring it to achieve lowest possible order
			reverse(nums, 0, size-1);
			// Arrays.sort(nums);
		} else {
			int swapIndex = i-1;
			int minIndex = i;
			for(int j=i+1;j<size;j++) {
				if(nums[minIndex]>=nums[j] && nums[swapIndex]< nums[j]) {
					minIndex = j;
				}
			}
			swap(nums, swapIndex, minIndex);
			reverse(nums, swapIndex+1, size-1);
			// Arrays.sort(nums, swapIndex+1, size);
		}
	}
	
	private void swap(int[] nums, int srcIndex, int targetIndex) {
		int temp = nums[srcIndex];
		nums[srcIndex] = nums[targetIndex];
		nums[targetIndex] = temp;
	}
	
	private void reverse(int[] nums, int startIndex, int endIndex) {
		while(startIndex<endIndex) {
			swap(nums, startIndex, endIndex);
			startIndex++;
			endIndex--;
		}
	}
	
	private void print(int[] nums) {
		System.out.println();
		for(int num:nums) {
			System.out.print(num+",");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		NextPermutation nextPermutation = new NextPermutation();
		int[] nums1 = {1,2,3};
		nextPermutation.nextPermutation(nums1);
		nextPermutation.print(nums1);
		int[] nums2 = {1,3,4,2};
		nextPermutation.nextPermutation(nums2);
		nextPermutation.print(nums2);

	}

}
