package interviewQuestions;

import java.util.LinkedList;
import java.util.Queue;

/* **Medium**   958. Check Completeness of a Binary Tree
 * 
 * Given the root of a binary tree, determine if it is a complete binary tree.

   In a complete binary tree, every level, except possibly the last, is completely filled, and all nodes 
   in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last
    level h.

	Example 1:
						 1
					   /   \
					  2     3
					 / \   /
					4   5  6	
		Input: root = [1,2,3,4,5,6]
		Output: true
		Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), 
					 and all nodes in the last level ({4, 5, 6}) are as far left as possible.
					 
	Example 2:
						 1
					   /   \
					  2      3
					 / \      \
					4   5      7	
					
		Input: root = [1,2,3,4,5,null,7]
		Output: false
		Explanation: The node with value 7 isn't as far left as possible.
		
	Constraints:
		The number of nodes in the tree is in the range [1, 100].
		1 <= Node.val <= 1000
 */
public class CheckCompletenessofBinaryTree {
	
	/*
	 * Time Complexity : O(n)
	 * Space Complexity: O(n)
	 * 
	 * Use BFS to do a level order traversal,add childrens to the bfs queue,until we met the first empty node.
	 * For a complete binary tree, there should not be any node after we met an empty one.
	 */
	public boolean isCompleteTreeBFS(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while(queue.peek()!=null) {         // until we encounter null node
			TreeNode node = queue.poll();
			queue.add(node.left);
			queue.add(node.right);
		}
		// after that every node should be null if it is Complete Binary Tree
		while(!queue.isEmpty() && queue.peek()==null) {
			queue.poll();
		}
		return queue.isEmpty();
	}

	public boolean isCompleteTreeBFSOptimzed(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null){
            return true;
        }
        queue.add(root);
        while(true){
            TreeNode node = queue.poll();
            if(node.left == null){
                if(node.right!=null){
                    return false;
                }
                break;
            }
            queue.add(node.left);
            if(node.right==null){
                break;
            }
            queue.add(node.right);
        }
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node.left!=null || node.right!=null){
                return false;
            }
        }
        return queue.isEmpty();
    }

	int maxIndex;
	int totalNodes;

	// DFS way (Best solution)
	public boolean isCompleteTree(TreeNode root) {
		maxIndex = 0;
		totalNodes = 0;
		dfs(root, 1);
		return totalNodes==maxIndex;
    }

	private void dfs(TreeNode node, int currIndex){
		if(node == null){
			return;
		}
		totalNodes++;
		maxIndex = Math.max(maxIndex, currIndex);
		dfs(node.left, currIndex*2);
		dfs(node.right, currIndex*2+1);
	}

	public static void main(String[] args) {
		CheckCompletenessofBinaryTree checkComplete = new CheckCompletenessofBinaryTree();
		TreeNode root1 = new TreeNode(1);
		root1.left = new TreeNode(2);
		root1.right = new TreeNode(3);
		root1.left.left = new TreeNode(4);
		root1.left.right = new TreeNode(5);
		root1.right.left = new TreeNode(6);
		System.out.println("Is Tree1 complete : "+ checkComplete.isCompleteTree(root1));
		TreeNode root2 = new TreeNode(1);
		root2.left = new TreeNode(2);
		root2.right = new TreeNode(3);
		root2.left.left = new TreeNode(4);
		root2.left.right = new TreeNode(5);
		root2.right.right = new TreeNode(7);
		System.out.println("Is Tree2 complete : "+ checkComplete.isCompleteTree(root2));
	}

}
