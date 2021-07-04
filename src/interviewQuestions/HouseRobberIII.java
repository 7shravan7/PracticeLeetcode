package interviewQuestions;

import java.util.HashMap;
import java.util.Map;

/* **Medium**    337. House Robber III
 * 
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, 
 * called root.

   Besides the root, each house has one and only one parent house. After a tour, the smart thief realized 
   that all houses in this place form a binary tree. It will automatically contact the police if two 
   directly-linked houses were broken into on the same night.

   Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting 
   the police.
   
   Example 1:
					3
	               / \ 
	              2   3
	               \   \
	                3   1
		Input: root = [3,2,3,null,3,null,1]
		Output: 7
		Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
		
	Example 2:
					3
				  /   \
				4	   5
			   / \      \
			  1	  3      1

		Input: root = [3,4,5,1,3,null,1]
		Output: 9
		Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 
	Constraints:
		The number of nodes in the tree is in the range [1, 104].
		0 <= Node.val <= 104
 */
public class HouseRobberIII {
	
	/*
	 * Recursive Solution 
	 * Time Limit Exceeded
	 */
	public int robRecur(TreeNode root) {
		if(root == null) {
			return 0;
		}
		int currIncluded = root.val;
		if(root.left!=null) {
			currIncluded += robRecur(root.left.left) + robRecur(root.left.right);
		}
		if(root.right!=null) {
			currIncluded += robRecur(root.right.left) + robRecur(root.right.right);
		}
		int currExcluded = robRecur(root.left) + robRecur(root.right);
		return Math.max(currIncluded, currExcluded);
	}
	
	public int robRecurMemoization(TreeNode root, Map<TreeNode, Integer> dpMap) {
		if(root == null) {
			return 0;
		}
		if(dpMap.containsKey(root)) {
			return dpMap.get(root);
		}
		int currIncluded = root.val;
		if(root.left!=null) {
			currIncluded += robRecurMemoization(root.left.left, dpMap) + robRecurMemoization(root.left.right, dpMap);
		}
		if(root.right!=null) {
			currIncluded += robRecurMemoization(root.right.left, dpMap) + robRecurMemoization(root.right.right, dpMap);
		}
		int currExcluded = robRecurMemoization(root.left, dpMap) + robRecurMemoization(root.right, dpMap);
		int maxAmount =  Math.max(currIncluded, currExcluded);
		dpMap.put(root, maxAmount);
		return maxAmount;
	}
	
	public int rob(TreeNode root) {
		Map<TreeNode, Integer> dpMap = new HashMap<>();
		return robRecurMemoization(root, dpMap);
	}
	
	/* !!!! approach wrong !!!!!
	 * public int rob1(TreeNode root) {
		if(root == null) {
			return 0;
		}
		List<Integer> levelOrderSumList = new ArrayList<>();
		// level order traversal
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()) {
			int size = queue.size();
			int levelSum = 0;
			while(size>0) {
				TreeNode node = queue.poll();
				levelSum += node.val;
				if(node.left!=null) {
					queue.add(node.left);
				}
				if(node.right!=null) {
					queue.add(node.right);
				}
				size--;
			}
			levelOrderSumList.add(levelSum);
		}
		// similar to House Robber
		int level = levelOrderSumList.size();
		int[] dp = new int[level+1];
		dp[level] = 0;
		dp[level-1] = levelOrderSumList.get(level-1);
		for(int i=level-2;i>=0;i--) {
			dp[i] = Math.max(levelOrderSumList.get(i) + dp[i+2], dp[i+1]);
		}
		return dp[0];
	}*/

	public static void main(String[] args) {
		HouseRobberIII houseRobber = new HouseRobberIII();
		TreeNode root1 = new TreeNode(3);
		root1.left = new TreeNode(2);
		root1.right = new TreeNode(3);
		root1.left.right = new TreeNode(3);
		root1.right.right = new TreeNode(1);
		System.out.println("Max amount of money of root1 (Recursive sol): "+houseRobber.robRecur(root1));
		System.out.println("Max amount of money of root1 (): "+houseRobber.rob(root1));
		TreeNode root2 = new TreeNode(3);
		root2.left = new TreeNode(4);
		root2.right = new TreeNode(5);
		root2.left.left = new TreeNode(1);
		root2.left.right = new TreeNode(3);
		root2.right.right = new TreeNode(1);
		System.out.println("Max amount of money of root2 (Recursive sol): "+houseRobber.robRecur(root2));
		System.out.println("Max amount of money of root2 (): "+houseRobber.rob(root2));
		TreeNode root3 = new TreeNode(79);
		root3.left = new TreeNode(99);
		root3.right = new TreeNode(77);
		root3.right.right = new TreeNode(69);
		root3.right.right.right = new TreeNode(60);
		root3.right.right.right.left = new TreeNode(53);
		root3.right.right.right.left.left = new TreeNode(73);
		root3.right.right.right.left.right = new TreeNode(11);
		root3.right.right.right.left.right.right = new TreeNode(62);
		root3.right.right.right.left.right.right.left = new TreeNode(27);
		System.out.println("Max amount of money of root3 (Recursive sol): "+houseRobber.robRecur(root3));
		System.out.println("Max amount of money of root3 (): "+houseRobber.rob(root3));
		TreeNode root4 = new TreeNode(3);
		root4.left = new TreeNode(2);
		root4.right = new TreeNode(3);
		root4.left.right = new TreeNode(3);
		root4.left.right.left = new TreeNode(2);
		root4.right.right = new TreeNode(1);
		root4.right.right.right = new TreeNode(4);
		System.out.println("Max amount of money of root4 (Recursive sol): "+houseRobber.robRecur(root4));
		System.out.println("Max amount of money of root4 (): "+houseRobber.rob(root4));
		TreeNode root5 = new TreeNode(3);
		root5.left = new TreeNode(2);
		root5.right = new TreeNode(20);
		root5.left.left = new TreeNode(1);
		root5.left.right = new TreeNode(5);
		root5.right.right = new TreeNode(4);
		System.out.println("Max amount of money of root5 (Recursive sol): "+houseRobber.robRecur(root5));
		System.out.println("Max amount of money of root5 (): "+houseRobber.rob(root5));
	}

}
