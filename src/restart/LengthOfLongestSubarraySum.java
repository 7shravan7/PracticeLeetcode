package restart;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubarraySum {

    public static int getLongestLength(int[] nums) {
        int longestLength = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int sum=0;
        for(int i=0;i<nums.length;i++){
            sum += nums[i];
            if(sum == 0){
                longestLength = Math.max(longestLength, i+1); //+1 to get the length as it is 0 indexed
            } else {
                if(map.containsKey(sum)){
                    longestLength = Math.max(longestLength, i-map.get(sum));
                } else {
                    map.put(sum, i);
                }
            }
        }
        return longestLength;
    }

    public static void main(String[] args) {
        int[] nums = {9, -3, 3, -1, -6, -5};
        int longestLen = getLongestLength(nums);
        System.out.println(longestLen);
    }
}
