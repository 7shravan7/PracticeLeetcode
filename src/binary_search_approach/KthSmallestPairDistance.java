package binary_search_approach;

import java.util.Arrays;

/*  ** HARD **    719.Find K-th Smallest Pair Distance
    The distance of a pair of integers a and b is defined as the absolute difference between
    a and b.

    Given an integer array nums and an integer k, return the kth smallest distance among
    all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length.

Example 1:

Input: nums = [1,3,1], k = 1
Output: 0
Explanation: Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1st smallest distance pair is (1,1), and its distance is 0.
Example 2:

Input: nums = [1,1,1], k = 2
Output: 0
Example 3:

Input: nums = [1,6,1], k = 3
Output: 5


Constraints:

n == nums.length
2 <= n <= 104
0 <= nums[i] <= 106
1 <= k <= n * (n - 1) / 2

 */
public class KthSmallestPairDistance {

    // this method runs 759ms on leetcode
    private boolean isAllowed1(int[] nums, int k, int diff) {
        int count=0;
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[j]-nums[i]>diff){
                    break;
                }
                count++;
            }
        }
        return count>=k;
    }

    // this method runs only in 9ms on leetcode
    private boolean isAllowed(int[] nums, int k, int diff) {
        int count = 0, left = 0;
        for (int right = 0; right < nums.length; ++right) {
            while (nums[right] - nums[left] > diff) {
                left++;
            }
            count += right - left;
        }
        return count>=k;
    }

    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int low = 0;
        int high = nums[nums.length-1]-nums[0];
        while(low<high){
            int mid = low + (high-low)/2;
            if(isAllowed1(nums, k, mid)){
                high = mid;
            } else {
                low = mid+1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        KthSmallestPairDistance kSmallPairDist = new KthSmallestPairDistance();
        int[] nums = {1,6,1,4,9,2};
        System.out.println(kSmallPairDist.smallestDistancePair(nums,3));
    }

}
