package codes.LeetCode.may2020Challenges.week_1_1to7;
/*
 * Given an array of size n, find the majority element. The majority element is the element that appears 
 * more than n/2 times.
	You may assume that the array is non-empty and the majority element always exist in the array.
	Example 1:
		Input: [3,2,3]
		Output: 3
	Example 2:
		Input: [2,2,1,1,1,2,2]
		Output: 2
 */
public class MajorityElement {

	public static int majorityElement(int[] nums) {
		int len = nums.length;
		int majorityElement = nums[0];
		int majorityElementCount = 1;
		for(int i=1;i<len;i++) {
			if(nums[i]==majorityElement) {
				majorityElementCount++;
			} else {
				majorityElementCount--;
				if(majorityElementCount==0) {
					majorityElement = nums[i];
					majorityElementCount = 1;
				}
			}
		}
		return majorityElement;
	}

	public static void main(String[] args) {
		int[] nums = {2,2,1,1,1,2,2};
		System.out.println(MajorityElement.majorityElement(nums));
	}

}
