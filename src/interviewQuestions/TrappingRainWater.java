package interviewQuestions;

/* **Hard**  42. Trapping Rain Water
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, 
 * compute how much water it can trap after raining.
  
   Example 1:
    Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
	Output: 6
	Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

   Example 2:
	Input: height = [4,2,0,3,2,5]
	Output: 9
	
	Constraints:
		n == height.length
		0 <= n <= 3 * 104
		0 <= height[i] <= 105
 */
public class TrappingRainWater {
	
	/*
	 * Time complexity : O(n) 3 times
	 * Space Complexity: O(n)
	 */
	public int trap(int[] height) {
		int len = height.length;
		int[] leftMaxArr = new int[len];
		leftMaxArr[0]=height[0];
		for(int i=1;i<len;i++) {
			leftMaxArr[i] = Math.max(leftMaxArr[i-1], height[i]);
		}
		int[] rightMaxArr = new int[len];
		rightMaxArr[len-1]=height[len-1];
		for(int i=len-2;i>=0;i--) {
			rightMaxArr[i] = Math.max(rightMaxArr[i+1], height[i]);
		}
		int trapWaterUnits = 0;
		for(int i=0;i<len;i++) {
			trapWaterUnits += (Math.min(leftMaxArr[i], rightMaxArr[i]) - height[i]);
		}
		return trapWaterUnits;
	}
	
	/*
	 * Time complexity : O(n)
	 * Space Complexity: O(1)
	 */
	public int trap2Pointers(int[] height) {
		int len = height.length;
		if(len == 0) {
			return 0;
		}
		int leftMax = -1;
		int rightMax = -1;
		int left=0;
		int right=len-1;
		int trapWaterUnits = 0;
		while(left<right) {
			if(height[left]<height[right]) {
				if(leftMax<height[left]) {
					leftMax = height[left];
				} else {
					trapWaterUnits += leftMax-height[left];
				}
				left++;
			} else {
				if(rightMax<height[right]) {
					rightMax = height[right];
				} else {
					trapWaterUnits += rightMax-height[right];
				}
				right--;
			}
		}
		return trapWaterUnits;
	}

	public static void main(String[] args) {
		TrappingRainWater trappingRainWater = new TrappingRainWater();
		int[] height1 = {0,1,0,2,1,0,1,3,2,1,2,1};
		System.out.println(trappingRainWater.trap(height1));
		System.out.println(trappingRainWater.trap2Pointers(height1));
		int[] height2 = {4,2,0,3,2,5};
		System.out.println(trappingRainWater.trap(height2));
		System.out.println(trappingRainWater.trap2Pointers(height2));
	}

}
