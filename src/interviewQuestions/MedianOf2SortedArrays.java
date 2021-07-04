package interviewQuestions;

/* **Hard**   4.Median Of Two Sorted Arrays
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

	Example 1:
		Input: nums1 = [1,3], nums2 = [2]
		Output: 2.00000
		Explanation: merged array = [1,2,3] and median is 2.
		
	Example 2:
		Input: nums1 = [1,2], nums2 = [3,4]
		Output: 2.50000
		Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
		
	Example 3:
		Input: nums1 = [0,0], nums2 = [0,0]
		Output: 0.00000
		
	Example 4:
		Input: nums1 = [], nums2 = [1]
		Output: 1.00000
		
	Example 5:
		Input: nums1 = [2], nums2 = []
		Output: 2.00000

	Constraints:
		nums1.length == m
		nums2.length == n
		0 <= m <= 1000
		0 <= n <= 1000
		1 <= m + n <= 2000
		-106 <= nums1[i], nums2[i] <= 106

	Follow up: The overall run time complexity should be O(log (m+n))
 */
public class MedianOf2SortedArrays {

	/*
	 * Time complexity : O((n+m)/2) ~ O(n+m)
	 */
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m= nums1.length;
		int n = nums2.length;
		int medianIndex = (m+n)/2;
		int medianIndex1 = (m+n)%2==0 ? medianIndex-1 : -1;
		int[] temp = {};
		int tempSize = 0;
		if(m<n) {
			temp = nums1;
			nums1 = nums2;
			nums2 = temp;
			tempSize = m;
			m = n;
			n = tempSize;
		}
		int i=0;
		int j=0;
		int count=-1;
		int median1Val = -1;
		int median2Val = -1;
		for(;i<m;) {
			count++;
			if(j<n && nums1[i]>nums2[j]) {
				if(medianIndex1!=-1 && count == medianIndex1) {
					median2Val = nums2[j];
				} else if (count == medianIndex) {
					median1Val = nums2[j];
					break;
				}
				j++;
			} else {
				if(medianIndex1!=-1 && count == medianIndex1) {
					median2Val = nums1[i];
				} else if (count == medianIndex) {
					median1Val = nums1[i];
					break;
				}
				i++;
			}
		}
		if(j<n && i>=m) {
			median1Val = nums2[j];
		}
		return median2Val == -1 ? median1Val/1.0 : median1Val/2.0 + median2Val/2.0;
	}
	
	/*
	 * Time complexity : O(log(min(n,m)))
	 */
	public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
		if(nums1.length>nums2.length) {
			int[] temp = nums1;
			nums1 = nums2;
			nums2 = temp;
		}
		int n = nums1.length;
		int m = nums2.length;
		int low = 0;
		int high = n;
		while(low<=high) {
			int partitionIndexNums1 = (low+high)/2;
			int partitionIndexNums2 = ((n+m+1)/2) - partitionIndexNums1;
			int leftMaxNums1 = partitionIndexNums1==0 ? Integer.MIN_VALUE : nums1[partitionIndexNums1-1];
			int rightMinNums1 = partitionIndexNums1 == n ? Integer.MAX_VALUE : nums1[partitionIndexNums1];
			int leftMaxNums2 = partitionIndexNums2==0 ? Integer.MIN_VALUE : nums2[partitionIndexNums2-1];
			int rightMinNums2 = partitionIndexNums2 == m ? Integer.MAX_VALUE : nums2[partitionIndexNums2];
			if(leftMaxNums1<=rightMinNums2 && leftMaxNums2<=rightMinNums1) {
				if((n+m)%2==0) {
					return Math.max(leftMaxNums1, leftMaxNums2)/2.0 + Math.min(rightMinNums1, rightMinNums2)/2.0;
				}
				return Math.max(leftMaxNums1, leftMaxNums2)/1.0;
			} else if(leftMaxNums1>rightMinNums2) {
				high = partitionIndexNums1-1;
			}else {
				low = partitionIndexNums1+1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		MedianOf2SortedArrays medianOf2SortedArrays = new MedianOf2SortedArrays();
		int[] nums1 = {1,3};
		int[] nums2 = {2};
		System.out.println("Median of nums1 & nums2 : "+medianOf2SortedArrays.findMedianSortedArrays1(nums1, nums2));
		int[] nums3 = {1,2};
		int[] nums4 = {3,4};
		System.out.println("Median of nums3 & nums4 : "+medianOf2SortedArrays.findMedianSortedArrays1(nums3, nums4));
		int[] nums5 = {0,0};
		int[] nums6 = {0};
		System.out.println("Median of nums5 & nums6 : "+medianOf2SortedArrays.findMedianSortedArrays1(nums5, nums6));
		int[] nums7 = {};
		int[] nums8 = {1};
		System.out.println("Median of nums7 & nums8 : "+medianOf2SortedArrays.findMedianSortedArrays1(nums7, nums8));
		int[] nums9 = {2};
		int[] nums10 = {};
		System.out.println("Median of nums9 & nums10 : "+medianOf2SortedArrays.findMedianSortedArrays1(nums9, nums10));
		int[] nums11 = {1,3,5,7};
		int[] nums12 = {2,4,6,8};
		System.out.println("Median of nums11 & nums12 : "+medianOf2SortedArrays.findMedianSortedArrays1(nums11, nums12));
		int[] nums13 = {1,3,5,7};
		int[] nums14 = {3,7,8,9};
		System.out.println("Median of nums13 & nums14 : "+medianOf2SortedArrays.findMedianSortedArrays1(nums13, nums14));
	}

}
