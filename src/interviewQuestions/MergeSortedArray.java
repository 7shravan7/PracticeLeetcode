package interviewQuestions;

/* **Easy**   88. Merge Sorted Array
 * 
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, 
 * representing the number of elements in nums1 and nums2 respectively.

   Merge nums1 and nums2 into a single array sorted in non-decreasing order.

   The final sorted array should not be returned by the function, but instead be stored inside the array nums1. 
   To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged,
   and the last n elements are set to 0 and should be ignored. nums2 has a length of n.

	Example 1:
		Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
		Output: [1,2,2,3,5,6]
		Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
					 The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
					 
	Example 2:
		Input: nums1 = [1], m = 1, nums2 = [], n = 0
		Output: [1]
		Explanation: The arrays we are merging are [1] and [].
					 The result of the merge is [1].
					 
	Example 3:
		Input: nums1 = [0], m = 0, nums2 = [1], n = 1
		Output: [1]
		Explanation: The arrays we are merging are [] and [1].
					 The result of the merge is [1].
					 Note that because m = 0, there are no elements in nums1. 
					 The 0 is only there to ensure the merge result can fit in nums1.

	Constraints:
		nums1.length == m + n
		nums2.length == n
		0 <= m, n <= 200
		1 <= m + n <= 200
		-109 <= nums1[i], nums2[j] <= 109

	Follow up: Can you come up with an algorithm that runs in O(m + n) time?
 */
public class MergeSortedArray {

	public void merge(int[] nums1, int m, int[] nums2, int n) {
		if(n == 0) {
			return;
		}
		if(m == 0) {
			for(int i=0;i<n;i++) {
				nums1[i] = nums2[i];
			}
			return;
		}
		int end1 = m-1;
		int end2 = n-1;
		int nums1Index = nums1.length-1;
		while(end1>=0 && end2>=0) {
			if(nums1[end1]<=nums2[end2]) {
				nums1[nums1Index--] = nums2[end2];
				end2--;
			} else {
				nums1[nums1Index--] = nums1[end1];
				//nums1[end1] = 110; // max value because of constraint
				end1--;
			}
		}
		while(end2>=0) {
			nums1[nums1Index--] = nums2[end2];
			end2--;
		}
	}
	
	public static void printResult(int[] nums) {
		for(int i=0;i<nums.length;i++) {
			System.out.print(nums[i]);
			if(i!=nums.length-1) {
				System.out.print(",");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		MergeSortedArray mergeSortedArray = new MergeSortedArray();
		int[] nums1 = {1,2,3,0,0,0};
		int m1 = 3;
		int[] nums2 = {2,5,6};
		int n1=3;
		mergeSortedArray.merge(nums1, m1, nums2, n1);
		printResult(nums1);
		int[] nums3 = {1};
		int m3 = 1;
		int[] nums4 = {};
		int n3=0;
		mergeSortedArray.merge(nums3, m3, nums4, n3);
		printResult(nums3);
		int[] nums5 = {0};
		int m5 = 0;
		int[] nums6 = {1};
		int n5=1;
		mergeSortedArray.merge(nums5, m5, nums6, n5);
		printResult(nums5);
		int[] nums7 = {-30,0,5,0,0};
		int m7 = 3;
		int[] nums8 = {1,2};
		int n7=2;
		mergeSortedArray.merge(nums7, m7, nums8, n7);
		printResult(nums7);
		int[] nums9 = {-11,-2,0,0,0,0};
		int m9 = 2;
		int[] nums10 = {-1,-1,-1,-1};
		int n9=4;
		mergeSortedArray.merge(nums9, m9, nums10, n9);
		printResult(nums9);
	}

}
