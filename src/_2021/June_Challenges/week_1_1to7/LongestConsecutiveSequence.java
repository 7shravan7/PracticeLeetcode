package _2021.June_Challenges.week_1_1to7;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

 You must write an algorithm that runs in O(n) time.

	Example 1:
		Input: nums = [100,4,200,1,3,2]
		Output: 4
		Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
		
	Example 2:
		Input: nums = [0,3,7,2,5,8,4,6,0,1]
		Output: 9

	Constraints:
		0 <= nums.length <= 105
		-109 <= nums[i] <= 109
 * 
 */
public class LongestConsecutiveSequence {
	
	/* 
	 * Time Complexity : O(n)
	 */
	public int longestConsecutiveOptimized(int[] nums) {
		if(nums.length<2) { // handle for empty arr or one element arr
			return nums.length;
		}
		Set<Integer> set = new HashSet<>();
		for(int num: nums) {
			set.add(num);
		}
		int longestConsecutiveSequence = 0;
		for(int num: nums) {
			// we are checking num+1 in loop so we can ignore for elements which has value lesser than this
			//       value(num-1) exists in input. We need to find first number(smaller) of this sequence
			if(!set.contains(num-1)) {
				int currNum = num;
				int currResult = 1;
				while(set.contains(currNum+1)) {
					currResult++;
					currNum += 1;
				}
				longestConsecutiveSequence = Math.max(longestConsecutiveSequence, currResult);
			}
		}
		return longestConsecutiveSequence;
	}
	
	// Time Complexity : O(nlogn)
    private static int longestConsecutiveSorted(int[] nums) {
        if (nums.length == 0) return 0;
        Arrays.sort(nums);
        int maxLen = 1;
        int currLen = 1;
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            // Seeing the same element does not increase the sequence
            if (nums[i] == nums[i-1])
                continue;
            
            if (nums[i] == nums[i-1] + 1) {
                currLen++;
            } else {
                maxLen = Math.max(maxLen, currLen);
                currLen = 1;
            }
        }
        return Math.max(maxLen, currLen);
    }
	
	/* Time limit exceeded
	 * Time Complexity : O(n^2)
	 */
	public int longestConsecutiveHashSet(int[] nums) {
		if(nums.length<2) { // handle for empty arr or one element arr
			return nums.length;
		}
		Set<Integer> set = new HashSet<>();
		for(int num: nums) {
			set.add(num);
		}
		int longestConsecutiveSequence = 0;
		for(int num: nums) {
			int currNum = num;
			int currResult = 1;
			while(set.contains(currNum+1)) {
				currResult++;
				currNum += 1;
			}
			longestConsecutiveSequence = Math.max(longestConsecutiveSequence, currResult);
		}
		return longestConsecutiveSequence;
	}
	
	/* Time limit will exceed for sure
	 * Time Complexity : O(n^3)
	 */
	public int longestConsecutiveBruteForce(int[] nums) {
		if(nums.length<2) { // handle for empty arr or one element arr
			return nums.length;
		}
		int longestConsecutiveSequence = 0;
		for(int num: nums) {
			int currNum = num;
			int currResult = 1;
			while(isExists(nums,currNum+1)) {
				currResult++;
				currNum += 1;
			}
			longestConsecutiveSequence = Math.max(longestConsecutiveSequence, currResult);
		}
		return longestConsecutiveSequence;
	}
	
	private boolean isExists(int[] nums, int currNum) {
		for(int num: nums) {
			if(num == currNum) {
				return true;
			}
		}
		return false;
	}
	

	public static void main(String[] args) {
		LongestConsecutiveSequence lcs= new LongestConsecutiveSequence();
		int[] nums1 = {100,4,200,1,3,2};
		System.out.println("Longest consecutive sequence of nums1 :"+lcs.longestConsecutiveBruteForce(nums1));
		System.out.println("Longest consecutive sequence of nums1 :"+lcs.longestConsecutiveHashSet(nums1));
		System.out.println("Longest consecutive sequence of nums1 :"+lcs.longestConsecutiveOptimized(nums1));
		int[] nums2 = {0,3,7,2,5,8,4,6,0,1};
		System.out.println("Longest consecutive sequence of nums2 :"+lcs.longestConsecutiveBruteForce(nums2));
		System.out.println("Longest consecutive sequence of nums2 :"+lcs.longestConsecutiveHashSet(nums2));
		System.out.println("Longest consecutive sequence of nums2 :"+lcs.longestConsecutiveOptimized(nums2));
	}

}
