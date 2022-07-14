package restart;

import java.util.*;

public class SubarrayWIthGivenXor {

    public int solve(ArrayList<Integer> A, int B) {
        Map<Integer, Integer> map =new HashMap<>();
        int xorVal = 0;
        int count = 0;
        for(int i=0;i<A.size();i++){
            int num=A.get(i);
            xorVal ^= num;
            if(xorVal == B){
                count += 1;
            }
            if(map.containsKey(xorVal^B)){
                count += map.get(xorVal^B);
            }
            if(!map.containsKey(xorVal)){
                map.put(xorVal, 0);
            }
            map.put(xorVal, map.get(xorVal)+1);
        }
        return count;
    }

    public static void main(String[] args) {
        SubarrayWIthGivenXor subarray = new SubarrayWIthGivenXor();
        int[] nums={4, 2, 2, 6, 4};
        int val = 6;
        ArrayList<Integer> inputList = new ArrayList<>();
        for(int num: nums){
            inputList.add(num);
        }
        System.out.println("Number of subarrays with xor "+val +" is "+ subarray.solve(inputList, val));
    }
}
