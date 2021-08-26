package interviewQuestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* **Medium**    437. Path Sum III
 * 
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.

  The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).


	Example 1:
		Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
		
							10
						   /  \
						  5   -3
						 / \    \
						3   2    11
					   / \	 \
					  3  -2   1
 						
		Output: 3
		Explanation: The paths that sum to 8 are shown.
		
	Example 2:
		Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
		Output: 3
 
	Constraints:
		The number of nodes in the tree is in the range [0, 1000].
		-109 <= Node.val <= 109
		-1000 <= targetSum <= 1000
 */
public class PathSumIII {
	
	int noOfPaths;
	
	// array list approach
	// Time complexity : O(n*h*h) since pathList max size is height of tree
	public void pathSum(TreeNode node, int targetSum, List<Integer> pathList) {
		if(node == null) {
			return;
		}
		pathList.add(node.val);
		pathSum(node.left, targetSum, pathList);
		pathSum(node.right, targetSum, pathList);
		int sum = 0;
		for(int i=pathList.size()-1;i>=0;i--) {
			sum += pathList.get(i);
			if(targetSum == sum) {
				noOfPaths++;
			}
		}
		pathList.remove(pathList.size()-1);
	}
	
	Map<Integer, Integer> prefixSumMap;
	
	// prefix sum
	public void pathPrefixSum(TreeNode node, int targetSum, int prefixSum) {
		if(node == null) {
			return;
		}
		int currSum = prefixSum + node.val;
		if(currSum == targetSum) {
			noOfPaths++;
		}
		noOfPaths += prefixSumMap.getOrDefault(currSum-targetSum, 0);
		prefixSumMap.put(currSum, prefixSumMap.getOrDefault(currSum,0)+1);
		pathPrefixSum(node.left, targetSum, currSum);
		pathPrefixSum(node.right, targetSum, currSum);
		prefixSumMap.put(currSum, prefixSumMap.get(currSum)-1);
	}

	public int pathSum(TreeNode root, int targetSum) {
		if(root == null) {
			return 0;
		}
		noOfPaths = 0;
		//pathSum(root, targetSum, new ArrayList<>());
		prefixSumMap = new HashMap<>();
		pathPrefixSum(root, targetSum, 0);
		return noOfPaths;
	}

	public static void main(String[] args) {
		PathSumIII pathSum = new PathSumIII();
		TreeNode root1 = new TreeNode(10);
		root1.left = new TreeNode(5);
		root1.right = new TreeNode(-3);
		root1.left.left = new TreeNode(3);
		root1.left.right = new TreeNode(2);
		root1.right.right = new TreeNode(11);
		root1.left.left.left = new TreeNode(3);
		root1.left.left.right = new TreeNode(-2);
		root1.left.right.right = new TreeNode(1);
		int targetSum1 = 8;
		System.out.println("No of path sum with target "+targetSum1 + " : "+pathSum.pathSum(root1, targetSum1));
		TreeNode root2 = new TreeNode(5);
		root2.left = new TreeNode(4);
		root2.right = new TreeNode(8);
		root2.left.left = new TreeNode(11);
		root2.right.left = new TreeNode(13);
		root2.right.right = new TreeNode(4);
		root2.left.left.left = new TreeNode(7);
		root2.left.left.right = new TreeNode(2);
		root2.right.right.left = new TreeNode(5);
		root2.right.right.right = new TreeNode(1);
		int targetSum2 = 22;
		System.out.println("No of path sum with target "+targetSum2 + " : "+pathSum.pathSum(root2, targetSum2));
	}

}
