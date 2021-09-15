package interviewQuestions;

import java.util.Stack;

/* **Hard**    84. Largest Rectangle in Histogram
 * 
 * Given an array of integers heights representing the histogram's bar height where the width of each bar 
 * is 1, return the area of the largest rectangle in the histogram.

	Example 1:
		Input: heights = [2,1,5,6,2,3]
		Output: 10
		Explanation: The above is a histogram where width of each bar is 1.
					 The largest rectangle is shown in the red area, which has an area = 10 units.
					 
	Example 2:
		Input: heights = [2,4]
		Output: 4

	Constraints:
		1 <= heights.length <= 105
		0 <= heights[i] <= 104
 */
public class LargestRectangleInHistogram {
	
	/*
	 * Time Complexity : O(n^2)
	 * Space Complexity: O(1) 
	 */
	public int largestRectangleAreaBruteForce(int[] heights) {
		int maxArea = 0;
		int len = heights.length;
		for(int i=0;i<len;i++) {
			int minHeight = heights[i];
			for(int j=i;j<len;j++) {
				minHeight = Math.min(minHeight, heights[j]);
				int width = j-i+1;
				maxArea = Math.max(maxArea, minHeight*width);
			}
		}
		return maxArea;
	}
	
	/*
	 * Time Complexity : O(n)
	 * Space Complexity: O(n) 
	 */
	public int largestRectangleArea(int[] heights) {
		int maxArea = 0;
		int len = heights.length;
		Stack<Integer> stack = new Stack<>();
		stack.push(-1); // default left limit -1 indicates no less element in left side
		for(int i=0;i<len;i++) {
			int height = heights[i];
			int rightLimit = i;
			while((stack.peek()!=-1) && height<heights[stack.peek()]) {
				int popHeight = heights[stack.pop()];
				int leftLimit = stack.peek();
				maxArea = Math.max(maxArea, popHeight * (rightLimit-leftLimit-1));
			}
			stack.push(i); // push index not height
		}
		int rightLimit = len;
		while(stack.peek()!=-1) {
			int popHeight = heights[stack.pop()];
			int leftLimit = stack.peek();
			maxArea = Math.max(maxArea, popHeight * (rightLimit-leftLimit-1));
		}
		return maxArea;
	}
	
	
	/* !!!my approach wrong!!!
	public int largestRectangleArea(int[] heights) {
		int maxArea = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		for(int i=1;i<heights.length;i++) {
			if(stack.isEmpty() || heights[stack.peek()]<=heights[i]) {
				stack.push(i);
			} else {
				while(!stack.isEmpty() && heights[stack.peek()]>heights[i]) {
					int popedIndex = stack.pop();
					maxArea = Math.max(maxArea, (i-popedIndex)*heights[popedIndex]);
				}
				stack.push(i);
			}
		}
		while(!stack.isEmpty()) {
			int popedIndex = stack.pop();
			if(stack.isEmpty()) {
				maxArea = Math.max(maxArea, heights[popedIndex]);
			} else {
				maxArea = Math.max(maxArea, (popedIndex-stack.peek()+1)*heights[stack.peek()]);
			}
		}
		return maxArea;
	}*/

	public static void main(String[] args) {
		LargestRectangleInHistogram largestRect = new LargestRectangleInHistogram();
		int[] heights1 = {2,1,5,6,2,3};
		System.out.println("Largest Rectangle area 1:"+largestRect.largestRectangleArea(heights1));
		int[] heights2 = {2,4};
		System.out.println("Largest Rectangle area 2:"+largestRect.largestRectangleArea(heights2));
		int[] heights3 = {1,2,3,4,5,6,7,8};
		System.out.println("Largest Rectangle area 3:"+largestRect.largestRectangleArea(heights3));
		int[] heights4 = {8,6,4,2,0};
		System.out.println("Largest Rectangle area 4:"+largestRect.largestRectangleArea(heights4));
		int[] heights5 = {0};
		System.out.println("Largest Rectangle area 5:"+largestRect.largestRectangleArea(heights5));
	}

}
