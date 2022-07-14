package restart;

import java.util.*;

public class MaxXORWIthAnElementFromArray {

    // Time Limit exceeded.
    // Having less than queries[i][1] elements in trie only
    public int[] maximizeXor1(int[] nums, int[][] queries) {
        Arrays.sort(nums); // sort the nums
        int len = queries.length;
        int[] resultArr = new int[len];
        for(int i=0;i<len;i++){
            int maxLen = (Integer.toBinaryString(Math.max(queries[i][0], queries[i][1]))).length();
            int xor = 0;
            int zeroMask = 1<<maxLen;
            TrieNode root = new TrieNode();
            TrieNode xorNode = root;
            String xorStr = Integer.toBinaryString(queries[i][0] | zeroMask).substring(1);
            for(int j=0;j<nums.length;j++){
                if(nums[j]>queries[i][1]){
                    if(j==0){
                        xor = -1;
                    }
                    break;
                }
                String str = (Integer.toBinaryString(nums[j] | zeroMask)).substring(1);
                TrieNode node = root;
                for(char ch:str.toCharArray()){
                    if(!node.containsChar(ch)){
                        node.put(ch);
                    }
                    node = node.getNode(ch);
                }
            }
            if(xor!=-1){
                for(char xorCh:xorStr.toCharArray()){
                    char oppBit = xorCh=='0' ? '1' : '0';
                    if(xorNode.containsChar(oppBit)){
                        xor = xor << 1 | 1;
                        xorNode = xorNode.getNode(oppBit);
                    } else {
                        xor = xor << 1;
                        xorNode = xorNode.getNode(xorCh);
                    }
                }
            }
            resultArr[i] = xor;
        }
        return resultArr;
    }

    //here same as prev but only change is we use offline queries
    // i.e, sort the queries based on queries[i][1] asc, so that we could contruct trie only once
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int len = queries.length;
        int[] resultArr = new int[len];
        Arrays.sort(nums);
        List<int[]> queriesIndexList = new ArrayList<>();
        int maxNumber = 0;
        for(int i=0;i<len;i++){
            int[] arr = new int[3];
            arr[0] = queries[i][0];
            arr[1] = queries[i][1];
            arr[2] = i;
            queriesIndexList.add(arr);
            maxNumber = Math.max(maxNumber, Math.max(arr[0],arr[1]));
        }
        int maxBitLength = Integer.toBinaryString(maxNumber).length();
        int zeroMask = 1<<maxBitLength;
        Collections.sort(queriesIndexList, (a,b)->a[1]-b[1]);
        TrieNode root = new TrieNode();
        int numIndex = 0;
        for(int i=0;i<len;i++){
            int[] queryArr = queriesIndexList.get(i);
            int limitVal = queryArr[1];
            while(numIndex<nums.length && nums[numIndex]<=limitVal){
                String binaryString = Integer.toBinaryString(nums[numIndex] | zeroMask).substring(1);
                insertIntoTrie(root, binaryString);
                numIndex++;
            }
            if(numIndex==0){
                resultArr[queryArr[2]] = -1;
                continue;
            }
            String xorString = Integer.toBinaryString(queryArr[0] | zeroMask).substring(1);
            resultArr[queryArr[2]] = getMaxXor(root, xorString);
        }
        return resultArr;

    }

    private void insertIntoTrie(TrieNode node, String binaryString){
        for(char bit: binaryString.toCharArray()){
            if(!node.containsChar(bit)){
                node.put(bit);
            }
            node = node.getNode(bit);
        }
    }

    private int getMaxXor(TrieNode node, String valStr){
        int xorVal = 0;
        for(char ch:valStr.toCharArray()){
            char oppBit = ch=='0' ? '1':'0';
            if(node.containsChar(oppBit)){
                xorVal = xorVal<<1 | 1;
                node = node.getNode(oppBit);
            } else {
                xorVal = xorVal<<1;
                node = node.getNode(ch);
            }
        }
        return xorVal;
    }

    class TrieNode {

        TrieNode[] children;

        public TrieNode() {
            children = new TrieNode[2];
        }

        public boolean containsChar(char ch){
            return children[ch-'0']!=null;
        }

        public TrieNode getNode(char ch){
            return children[ch-'0'];
        }

        public void put(char ch){
            children[ch-'0'] = new TrieNode();
        }

    }

    public static void printResult(int[] resultArr) {
        for(int res:resultArr){
            System.out.println(res);
        }
    }

    public static void main(String[] args) {
        MaxXORWIthAnElementFromArray maxXORWIthAnElementFromArray = new MaxXORWIthAnElementFromArray();
        int[] nums = {0,1,2,3,4};
        int[][] queries = {{3,1},{1,3},{5,6}};
        printResult(maxXORWIthAnElementFromArray.maximizeXor(nums, queries));
        int[] nums1 = {5,2,4,6,6,3};
        int[][] queries1 = {{12,4},{8,1},{6,3}};
        printResult(maxXORWIthAnElementFromArray.maximizeXor(nums1, queries1));
    }
}
