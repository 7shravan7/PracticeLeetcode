package restart;

import java.util.ArrayList;
import java.util.List;

public class MaxConsecutiveOnesII {

    // sliding window technique
    // increase the window when there are 1 or 0 zeroes
    // decrease the window by contracting the left unless a valid window is reached
    public int findMaxConsecutiveOnes(int[] nums) {
        int leftPtr = 0;
        int rightPtr =0;
        int longestSequence = 0;
        int zeroCount = 0;
        while(rightPtr<nums.length){
            if(nums[rightPtr]==0){
                zeroCount++;
            }
            while(zeroCount==2){
                if(nums[leftPtr]==0){
                    zeroCount--;
                }
                leftPtr++;
            }
            longestSequence = Math.max(longestSequence, rightPtr-leftPtr+1);
            rightPtr++;
        }
        return longestSequence;
    }

    // my approach
    public int findMaxConsecutiveOnes1(int[] nums) {
        int maxConsecutiveOnes = 0;
        List<Integer> zeroIndexList = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            if(nums[i] == 0){
                zeroIndexList.add(i);
            }
        }
        if(nums.length==0){
            return 0;
        }
        if(zeroIndexList.isEmpty()){
            maxConsecutiveOnes = nums.length;
        } else if (zeroIndexList.size() == nums.length){
            maxConsecutiveOnes = 1;
        } else {
            int size = zeroIndexList.size();
            for(int i=0;i<size;i++){
                int leftIndex = i==0 ? 0 : zeroIndexList.get(i-1);
                int rightIndex = i==size-1 ? size-1 : zeroIndexList.get(i+1);
                int currIndex = zeroIndexList.get(i);
                maxConsecutiveOnes = Math.max(maxConsecutiveOnes, leftIndex+rightIndex);
            }
        }
        return maxConsecutiveOnes;
    }

    public static void main(String[] args) {
        MaxConsecutiveOnesII maxConsecutiveOnesII = new MaxConsecutiveOnesII();
        int[] nums1 = {1,1,0,1,1,1,1,1,1,0,1,};
        System.out.println(maxConsecutiveOnesII.findMaxConsecutiveOnes(nums1));
        int[] nums2 = {1,0,1,1,0,1};
        System.out.println(maxConsecutiveOnesII.findMaxConsecutiveOnes(nums2));
    }
}
