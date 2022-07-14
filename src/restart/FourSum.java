package restart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> resultList = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        for(int i=0;i<len-3;i++){
            if(i==0 || nums[i]!=nums[i-1]){
                int num1 = nums[i];
                for(int j=i+1;j<len-2;j++){
                    if(j==i+1 || nums[j]!=nums[j-1]){
                        int num2 = nums[j];
                        List<int[]> list = twoSum(nums, j+1, len-1, target-nums[i]-nums[j]);
                        if(!list.isEmpty()){
                            list.forEach(l->{
                                List<Integer> arrayList = new ArrayList<>();
                                arrayList.add(num1);
                                arrayList.add(num2);
                                arrayList.add(l[0]);
                                arrayList.add(l[1]);
                                resultList.add(arrayList);
                            });
                        }
                    }
                }
            }

        }
        return resultList;
    }

    private List<int[]> twoSum(int[] nums, int start, int end, int targetSum) {
        List<int[]> numList = new ArrayList<>();
        while(start<end){
            int currSum = nums[start]+nums[end];
            if(currSum == targetSum){
                int[] arr = new int[2];
                arr[0] = nums[start];
                arr[1] = nums[end];
                numList.add(arr);
                start++;
                while(start<end && nums[start] == nums[start-1]){
                    start++;
                }
                end--;
                while(start<end && nums[end] == nums[end+1]){
                    end--;
                }
            } else if (currSum < targetSum){
                start++;
            } else {
                end--;
            }
        }
        return numList;
    }

    private static void printResult(List<List<Integer>> resultList) {
        resultList.forEach(list->{
            System.out.println(list.get(0)+","+list.get(1)+","+list.get(2)+","+list.get(3));
        });
    }

    public static void main(String[] args) {
        int[] nums = {-2,-1,-1,1,1,2,2};
        FourSum fourSum = new FourSum();
        List<List<Integer>> resultList = fourSum.fourSum(nums, 0);
        printResult(resultList);
    }
}
