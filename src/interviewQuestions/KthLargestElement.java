package interviewQuestions;

import java.util.PriorityQueue;

/* **Medium**    215. Kth Largest Element in an Array
 * 
 * 	Given an integer array nums and an integer k, return the kth largest element in the array.

	Note that it is the kth largest element in the sorted order, not the kth distinct element.

	Example 1:
		Input: nums = [3,2,1,5,6,4], k = 2
		Output: 5

	Example 2:
		Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
		Output: 4

	Constraints:
		1 <= k <= nums.length <= 104
		-104 <= nums[i] <= 104
 */
public class KthLargestElement {

	public int findKthLargest(int[] nums, int k) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b)->a-b);
		for(int i=0;i<nums.length;i++) {
			if(k==0 && minHeap.peek()<=nums[i]) {
				minHeap.poll();
				k++;
			}
			if(k>0) {
				minHeap.add(nums[i]);
				k--;
			}
		}
		return minHeap.poll();
	}
	
	public int findKthLargestPartition(int[] nums, int k) {
		return partition(nums, 0, nums.length-1, nums.length-k);
	}
	
	private int partition(int[] nums, int startIndex, int endIndex, int kthIndex) {
		if(startIndex>=endIndex) {
			return nums[startIndex];
		}
		int pivot = nums[startIndex];
		swap(nums, startIndex, endIndex);
		int i=startIndex;
		int j=startIndex;
		while(j<=endIndex) {
			if(nums[j]<pivot) {
				swap(nums, i, j);
				i++;
				j++;
			} else {
				j++;
			}
		}
		swap(nums, i, endIndex);
		if(i == kthIndex) {
			return nums[i];
		} else if (i<kthIndex) {
			return partition(nums, i+1, endIndex, kthIndex);
		} else {
			return partition(nums, startIndex, i-1, kthIndex);
		}
		
	}
	
	private void swap(int[] nums, int srcIndex, int destIndex) {
		int temp = nums[srcIndex];
		nums[srcIndex] = nums[destIndex];
		nums[destIndex] = temp;
	}

	public static void main(String[] args) {
		KthLargestElement largeElement = new KthLargestElement();
		int[] nums1 = {3,2,1,5,6,4};
		int k1 =2;
		System.out.println(k1+" largest element in nums1 : "+largeElement.findKthLargestPartition(nums1, k1));
		int[] nums2 = {3,2,3,1,2,4,5,5,6};
		int k2 =4;
		System.out.println(k2+" largest element in nums2 : "+largeElement.findKthLargestPartition(nums2, k2));
	}

}
