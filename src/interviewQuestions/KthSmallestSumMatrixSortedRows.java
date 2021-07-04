package interviewQuestions;

import java.util.PriorityQueue;

/* **Hard**   1439. Find the Kth Smallest Sum of a Matrix With Sorted Rows
 * 
 * You are given an m * n matrix, mat, and an integer k, which has its rows sorted in non-decreasing order.

   You are allowed to choose exactly 1 element from each row to form an array. Return the Kth smallest array
    sum among all possible arrays.

	Example 1:
		Input: mat = [[1,3,11],[2,4,6]], k = 5
		Output: 7
		Explanation: Choosing one element from each row, the first k smallest sum are:
					 [1,2], [1,4], [3,2], [3,4], [1,6]. Where the 5th sum is 7.
					 
	Example 2:
		Input: mat = [[1,3,11],[2,4,6]], k = 9
		Output: 17
		
	Example 3:
		Input: mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
		Output: 9
		Explanation: Choosing one element from each row, the first k smallest sum are:
					 [1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]. Where the 7th sum is 9.
					 
	Example 4:
		Input: mat = [[1,1,10],[2,2,9]], k = 7
		Output: 12
 
	Constraints:
		m == mat.length
		n == mat.length[i]
		1 <= m, n <= 40
		1 <= k <= min(200, n ^ m)
		1 <= mat[i][j] <= 5000
		mat[i] is a non decreasing array.
 */
public class KthSmallestSumMatrixSortedRows {
	
	/*
	 * 2 priority queues
	 * handling row wise.. add elements of max heap with curr row to local max heap
	 * consider this local max heap as max heap for next row
	 */
	public int kthSmallest(int[][] mat, int k) {
		int colVal = Math.min(k, mat[0].length);
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((a,b)->b-a);
		maxHeap.add(0);
		for(int[] row : mat) {
			PriorityQueue<Integer> tempHeap = new PriorityQueue<Integer>((a,b)->b-a);
			for(int val: maxHeap) {
				for(int i=0;i<colVal;i++) {
					tempHeap.add(val+row[i]);
					if(tempHeap.size()>k) {
						tempHeap.poll();
					}
				}
			}
			maxHeap = tempHeap;
		}
		return maxHeap.poll();
	}
	
	public static void main(String[] args) {
		KthSmallestSumMatrixSortedRows kthSmallSum = new KthSmallestSumMatrixSortedRows();
		int[][] mat1 = {{ 1, 3, 11},
						{ 2, 4,  6}};
		int k1 = 2;
		System.out.println(k1+"th smallest sum of mat1 :"+kthSmallSum.kthSmallest(mat1, k1));
		int k2 = 9;
		System.out.println(k2+"th smallest sum of mat1 :"+kthSmallSum.kthSmallest(mat1, k2));
		int[][] mat2 = {{1,10,10},
						{1, 4, 5},
						{2, 3, 6}};
		int k3 = 7;
		System.out.println(k3+"th smallest sum of mat2 :"+kthSmallSum.kthSmallest(mat2, k3));
	}

}
