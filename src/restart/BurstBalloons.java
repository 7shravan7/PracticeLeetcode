package restart;

import com.sun.xml.internal.ws.encoding.MtomCodec;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BurstBalloons {

    private String getKey(int[] arr) {
        return Arrays.toString(arr);
    }

    private int[] reducedArray(int[] arr, int index) {
        List<Integer> list = IntStream.of(arr).boxed().collect(Collectors.toList());
        list.remove(index);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private int getCoinVal(int[] arr, int index){
        int prevVal = ((index-1) <0) ? 1 : arr[index-1];
        int nextVal = ((index+1) >=arr.length) ? 1 : arr[index+1];
        return prevVal * arr[index] * nextVal;
    }

    private int getMaxCoins(int[] nums, Map<String, Integer> dpMap){
        String key = getKey(nums);
        if(dpMap.containsKey(key)){
            return dpMap.get(key);
        }
        int len = nums.length;
        if(len == 1){
            dpMap.put(key, nums[0]);
            return nums[0];
        } else if (len ==2) {
            int maxCoins = nums[0]*nums[1] + Math.max(nums[0],nums[1]);
            dpMap.put(key, maxCoins);
            return maxCoins;
        } else {
            int maxCoins = 0;
            for (int i = 0; i < len; i++) {
                int[] arr = reducedArray(nums, i);
                int coinVal = getCoinVal(nums, i);
                maxCoins = Math.max(maxCoins, coinVal + getMaxCoins(arr, dpMap));
            }
            dpMap.put(key, maxCoins);
            return maxCoins;
        }
    }

    /*
        Time limit exceeded
        Time Complexity : O(N2^N) 2^N states and N numbers
     */
    public int maxCoins1(int[] nums) {
        Map<String, Integer> dpMap = new HashMap<>();
        int maxCoins = getMaxCoins(nums, dpMap);
        return maxCoins;
    }

    private String printArr(int[] arr, int s, int e){
        StringBuilder sb = new StringBuilder("[");
        for(int i=s;i<=e;i++){
            sb.append(arr[i]);
            if(i!=e){
                sb.append(",");
            }
        }
        return sb.append("]").toString();
    }

    private int maxCoinsTopDown(int[] nums, int left, int right, int[][] dp){
        //System.out.println("nums = " + Arrays.toString(nums) + ", left = " + left + ", right = " + right);
        if(right-left<0){
            return 0;
        }
        if(dp[left][right]!=0){
            return dp[left][right];
        }
        int maxCoins = 0;
        for(int i=left;i<=right;i++){
            int gain = nums[left-1] * nums[i] * nums[right+1];
            System.out.println("i;(l:r)::"+i+";("+left+","+right+") G="+ gain + " dp("+printArr(nums, left, i-1)+") + dp("
                    +printArr(nums,i+1,right)+")");
            int remaining = maxCoinsTopDown(nums, left, i-1, dp) +
                    maxCoinsTopDown(nums, i+1, right, dp);
            maxCoins = Math.max(maxCoins, remaining + gain);
            System.out.println("i;(l:r)::"+i+";("+left+","+right+") maxCoins="+ maxCoins);
        }
        dp[left][right] = maxCoins;
        return maxCoins;
    }

    public int maxCoins(int[] nums) {
        int n = nums.length + 2;
        int[] newNums = new int[n];
        System.arraycopy(nums, 0, newNums, 1, n - 2);
        newNums[0] = 1;
        newNums[n - 1] = 1;
        int[][] dp = new int[n][n];
        return maxCoinsTopDown(newNums, 1, n-2, dp);
    }

    public static void main(String[] args) {
        int[] nums1 = {3,1,5,8};
        int[] nums = {8,2,6,8,9,8,1,4,1,5,3,0,7,7,0,4,2,2};
        int[] nums2 = {1,5,3,0,7,7,0,4,2,2};
        BurstBalloons bb = new BurstBalloons();
        System.out.println(bb.maxCoins(nums1));
    }
}
