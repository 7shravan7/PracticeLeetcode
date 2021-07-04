package interviewQuestions;

/* **Medium**    11. Container With Most Water
 * 
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical 
 * lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). Find two lines, which, 
 * together with the x-axis forms a container, such that the container contains the most water.

   Notice that you may not slant the container.
   
 Example 1:  
   	Input: height = [1,8,6,2,5,4,8,3,7]
	Output: 49
	Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of
	 			 	water (blue section) the container can contain is 49.
	 			 	
 Example 2:
	Input: height = [1,1]
	Output: 1
	
 Example 3:
	Input: height = [4,3,2,1,4]
	Output: 16
	
 Example 4:
	Input: height = [1,2,1]
	Output: 2

 Constraints:
	n == height.length
	2 <= n <= 105
	0 <= height[i] <= 104
 */
public class ContainerWithMostWater {
	
	/*
	 * Time Complexity : O(n^2)
	 * Time limit exceeded in leetcode
	 */
	public int maxAreaBruteForce(int[] height) {
		int maxArea = 0;
		for(int i=0;i<height.length;i++) {
			for(int j=i+1;j<height.length;j++) {
				int minHeight = Math.min(height[i], height[j]);
				maxArea = Math.max(maxArea, minHeight*(j-i));
			}
		}
		return maxArea;
	}
	
	/*
	 * Optmized approach in single pass using 2 pointers
	 * Time complexity : O(n)
	 */
	public int maxArea(int[] height) {
		int maxArea = 0;
		int i=0;
		int j=height.length-1;
		while(i<j) {
			int minHeight = Math.min(height[i], height[j]);
			maxArea = Math.max(maxArea, minHeight*(j-i));
			if(minHeight == height[i]) {
				i++;
			} else {
				j--;
			}
		}
		return maxArea;
	}

	public static void main(String[] args) {
		ContainerWithMostWater containerWithMostWater = new ContainerWithMostWater();
		int[] heights1 = {1,8,6,2,5,4,8,3,7};
		System.out.println("Max area of heights1 by O(n^2) approach :"+containerWithMostWater.maxAreaBruteForce(heights1));
		System.out.println("Max area of heights1 by O(n) approach :"+containerWithMostWater.maxArea(heights1));
		int[] heights2 = {1,1};
		System.out.println("Max area of heights2 by O(n^2) approach :"+containerWithMostWater.maxAreaBruteForce(heights2));
		System.out.println("Max area of heights2 by O(n) approach :"+containerWithMostWater.maxArea(heights2));
		int[] heights3 = {4,3,2,1,4};
		System.out.println("Max area of heights3 by O(n^2) approach :"+containerWithMostWater.maxAreaBruteForce(heights3));
		System.out.println("Max area of heights3 by O(n) approach :"+containerWithMostWater.maxArea(heights3));
		int[] heights4 = {1,2,1};
		System.out.println("Max area of heights4 by O(n^2) approach :"+containerWithMostWater.maxAreaBruteForce(heights4));
		System.out.println("Max area of heights4 by O(n) approach :"+containerWithMostWater.maxArea(heights4));
	}

}
