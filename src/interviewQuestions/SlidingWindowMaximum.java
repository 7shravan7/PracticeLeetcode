package interviewQuestions;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/* **Hard**   239. Sliding Window Maximum
 * 
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the 
 * array to the very right. You can only see the k numbers in the window. 
 * Each time the sliding window moves right by one position.

	Return the max sliding window.

	Example 1:
		Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
		Output: [3,3,5,5,6,7]
		Explanation: 
			Window position                Max
			---------------               -----
			[1  3  -1] -3  5  3  6  7       3
 			 1 [3  -1  -3] 5  3  6  7       3
 			 1  3 [-1  -3  5] 3  6  7       5
 			 1  3  -1 [-3  5  3] 6  7       5
 			 1  3  -1  -3 [5  3  6] 7       6
 			 1  3  -1  -3  5 [3  6  7]      7

	Example 2:
		Input: nums = [1], k = 1
		Output: [1]

	Example 3:
		Input: nums = [1,-1], k = 1
		Output: [1,-1]

	Example 4:
		Input: nums = [9,11], k = 2
		Output: [11]

	Example 5:
		Input: nums = [4,-2], k = 2
		Output: [4]

	Constraints:
		1 <= nums.length <= 105
		-104 <= nums[i] <= 104
		1 <= k <= nums.length
 */
public class SlidingWindowMaximum {

	class Node {
		int value;
		int index;
		public Node(int value,int index){
			this.value = value;
			this.index = index;
		}
	}
	
	/*
	 * Time complexity : O(nlogn)
	 */
	public int[] maxSlidingWindowHeap(int[] nums, int k) {
		int n = nums.length;
		int[] result = new int[n-k+1];
		int j=0;
		PriorityQueue<Node> maxHeap = new PriorityQueue<>((a,b) -> b.value-a.value);
		for (int i=0; i<n; i++) {
			maxHeap.add(new Node(nums[i], i));
			// this is the astuce to not search through the heap to remove an element
			while (maxHeap.peek().index < i-k+1) {
				maxHeap.remove();
			}
			if (i>= k-1) {
				result[j++] = maxHeap.peek().value;
			}
		}
		return result;
}

	/* 
	 * Time complexity : O(n)
	 */
	public int[] maxSlidingWindow(int[] nums, int k) {
		int[] result = new int[nums.length-k+1];
		Deque<Node> deque = new ArrayDeque<>();
		int resultIndex=0;
		for(int i=0;i<nums.length;i++) {
			while(!deque.isEmpty() && deque.getFirst().index<=i-k) {
				deque.removeFirst();
			}
			while(!deque.isEmpty() && deque.getLast().value<nums[i]) {
				deque.removeLast();
			}
			deque.addLast(new Node(nums[i], i));
			if(i+1>=k) {
				result[resultIndex++] = deque.getFirst().value;
			}
		}
		return result;
	}

	public int[] maxSlidingWindowLeetcodeBest(int[] nums, int k) {
		int n = nums.length;
		int[] res = new int[n-k+1];
		int left = 0, right = k-1, maxIndex = maxIndex(nums, 0, k-1);
		while(true){
			if(maxIndex >= left){
				res[left++] = nums[maxIndex];
				right += 1; // Update sliding window
				if(right == n){ // After the array traversal is complete, exit
					break;
				}
				// If the newly added number in the sliding window is the largest, then update
				if(nums[right] >= nums[maxIndex]){
					maxIndex = right;
				}
				// If the index of the maximum value is not within the window range, you need to find the index again
			}else{
				if(nums[right] >= nums[maxIndex]-1){
					maxIndex = right;
				}else if(nums[left] >= nums[maxIndex]-1){
					maxIndex = left;
				}else{
					maxIndex = maxIndex(nums, left, right);
				}
			}
		}
		return res;
	}

	public int maxIndex(int[] nums, int start, int end){
		int index = start, val = nums[start];
		for (int i = start+1; i <= end; i++) {
			if(val <= nums[i]){
				val = nums[i];
				index = i;
			}
		}
		return index;
	}

	public static void printResult(int[] result) {
		for(int i=0;i<result.length;i++) {
			System.out.print(result[i]);
			if(i!=result.length-1) {
				System.out.print(",");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		SlidingWindowMaximum slidingWindow = new SlidingWindowMaximum();
		int[] nums0 = {1,3,-1,-3,5,3,6,7};
		int k0=3;
		printResult(slidingWindow.maxSlidingWindowHeap(nums0, k0));
		int[] nums1 = {1,3,2,1,1,3,6,7};
		int k1=3;
		printResult(slidingWindow.maxSlidingWindowHeap(nums1, k1));
		int[] nums2 = {1};
		int k2=1;
		printResult(slidingWindow.maxSlidingWindowLeetcodeBest(nums2, k2));
		int[] nums3 = {1,-1};
		int k3=1;
		printResult(slidingWindow.maxSlidingWindowLeetcodeBest(nums3, k3));
		int[] nums4 = {9,11};
		int k4=2;
		printResult(slidingWindow.maxSlidingWindowLeetcodeBest(nums4, k4));
		int[] nums5 = {4,-2};
		int k5=2;
		printResult(slidingWindow.maxSlidingWindowLeetcodeBest(nums5, k5));
	}

}
