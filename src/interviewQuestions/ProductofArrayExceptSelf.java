package interviewQuestions;

/* **Medium**   238. Product of Array Except Self
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements
 *  of nums except nums[i].

	The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

	Example 1:
		Input: nums = [1,2,3,4]
		Output: [24,12,8,6]
		
	Example 2:
		Input: nums = [-1,1,0,-3,3]
		Output: [0,0,9,0,0]

	Constraints:
		2 <= nums.length <= 105
		-30 <= nums[i] <= 30
	The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
	
	Follow up:
		Could you solve it in O(n) time complexity and without using division?
		Could you solve it with O(1) constant space complexity? (The output array does not count as extra space for
		 space complexity analysis.)
 
 */
public class ProductofArrayExceptSelf {

	/*
	 * Time Complexity  : O(n)
	 * Space Complexity : O(1)
	 */
	public int[] productExceptSelf(int[] nums) {
		int[] productArr = new int[nums.length];
		productArr[0]=1;
		for(int i=1;i<nums.length;i++) {
			productArr[i] = productArr[i-1] * nums[i-1];
		}
		int rightMul = 1;
		for(int i=nums.length-2;i>=0;i--) {
			rightMul = rightMul * nums[i+1];
			productArr[i] = productArr[i] * rightMul;
		}
		return productArr;
	}
	
	public void printArr(int[] productArr) {
		for(int i: productArr) {
			System.out.print(i+",");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		ProductofArrayExceptSelf productOfArray = new ProductofArrayExceptSelf();
		int[] nums1 = {1,2,3,4};
		System.out.println("Product of Array Except Self for nums1 : ");
		productOfArray.printArr(productOfArray.productExceptSelf(nums1));
		int[] nums2 = {-1,1,0,-3,3};
		System.out.println("Product of Array Except Self for nums2 : ");
		productOfArray.printArr(productOfArray.productExceptSelf(nums2));
	}

}
