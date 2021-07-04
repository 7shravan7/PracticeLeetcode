package dp.leetcode;
/*
 * Time Complexity - O(n)
 * Space Complexity - O(n)
 */
public class TrappingRainWater {
	
	// 3 O(n) passes
	public int trap(int[] height) {
		int len = height.length;
		if(len==0){ // handle if arr is empty
			return 0;
		}
		int[] leftMaxArr = new int[len];
		leftMaxArr[0]=-1;
		for(int i=1;i<len;i++) {
			leftMaxArr[i] = Math.max(leftMaxArr[i-1],height[i-1]);
		}
		int[] rightMaxArr = new int[len];
		rightMaxArr[len-1] = -1;
		for(int i=height.length-2;i>=0;i--) {
			rightMaxArr[i] = Math.max(rightMaxArr[i+1],height[i+1]);
		}
		int trap=0;
		for(int i=1;i<len;i++) {
			int maxStoreHere = Math.min(leftMaxArr[i],rightMaxArr[i]);
			if(maxStoreHere>height[i]) {
				trap += maxStoreHere-height[i];
			}
		}
		return trap;
	}

	public static void main(String[] args) {
		TrappingRainWater trappingRainWater = new TrappingRainWater();
		int[] height1 = {0,1,0,2,1,0,1,3,2,1,2,1};
		System.out.println(trappingRainWater.trap(height1));
		int[] height2 = {4,2,0,3,2,5};
		System.out.println(trappingRainWater.trap(height2));
	}

}
