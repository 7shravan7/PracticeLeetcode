package interviewQuestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*   ** HARD **     Print all k-sum paths in a binary tree
    https://www.geeksforgeeks.org/print-k-sum-paths-binary-tree/

    A binary tree and a number k are given. Print every path in the tree with sum of the nodes
    in the path as k.
    A path can start from any node and end at any node and must be downward only,
        i.e. they need not be root node and leaf node; and negative numbers can also be there
        in the tree.

Examples:
    Input : k = 5
Root of below binary tree:
           1
        /     \
      3        -1
    /   \     /   \
   2     1   4     5
        /   / \     \
       1   1   2     6

Output :
3 2
3 1 1
1 3 1
4 1
1 -1 4 1
-1 4 2
5
1 -1 5
 */
public class PrintAllKSumPaths {

    public List<List<Integer>> getKSumPathsList(TreeNode root, int k) {
        List<List<Integer>> resultList = new ArrayList<>();
        kPathSum(root, k, new ArrayList<>(), resultList);
        return resultList;
    }

    private void kPathSum(TreeNode node, int k, List<Integer> pathList,
                          List<List<Integer>> resultList) {
        if(node == null){
            return;
        }
        pathList.add(node.val);
        kPathSum(node.left, k, pathList, resultList);
        kPathSum(node.right, k, pathList, resultList);
        int len = pathList.size();
        int sum = 0;
        for(int i=len-1;i>=0;i--){
            sum += pathList.get(i);
            if(sum == k){
                resultList.add(new ArrayList<>(pathList.subList(i, len)));
            }
        }
        pathList.remove(pathList.size()-1);
    }

    public void printResult(List<List<Integer>> pathList){
        pathList.forEach(paths->{
            paths.forEach(path-> System.out.print(path+" "));
            System.out.println();
        });
    }

    public static void main(String[] args) {
        PrintAllKSumPaths printAllKSumPaths = new PrintAllKSumPaths();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(-1);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(1);
        root.left.right.left = new TreeNode(1);
        root.right.left = new TreeNode(4);
        root.right.left.left = new TreeNode(1);
        root.right.left.right = new TreeNode(2);
        root.right.right = new TreeNode(5);
        root.right.right.right = new TreeNode(6);
        int k=5;
        List<List<Integer>> resultList = printAllKSumPaths.getKSumPathsList(root, k);
        printAllKSumPaths.printResult(resultList);
    }
}
