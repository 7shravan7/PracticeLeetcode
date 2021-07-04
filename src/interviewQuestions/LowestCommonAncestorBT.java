package interviewQuestions;

/* **Medium**   236. Lowest Common Ancestor of a Binary Tree
 * 
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

   According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes
    p and q as the lowest node in T that has both p and q as descendants 
    (where we allow a node to be a descendant of itself).”

	Example 1:
		Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
		Output: 3
		Explanation: The LCA of nodes 5 and 1 is 3.
		
	Example 2:
		Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
		Output: 5
		Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to
		               the LCA definition.
		               
	Example 3:
		Input: root = [1,2], p = 1, q = 2
		Output: 1

	Constraints:
		The number of nodes in the tree is in the range [2, 105].
		-109 <= Node.val <= 109
		All Node.val are unique.
		p != q
		p and q will exist in the tree.
 */
public class LowestCommonAncestorBT {
	
	private TreeNode lca(TreeNode root, TreeNode node1, TreeNode node2) {
		if(root == null) {
			return null;
		}
		if(root == node1 || root == node2) {
			return root;
		}
		TreeNode left = lca(root.left, node1, node2);
		TreeNode right = lca(root.right, node1, node2);
		if(left!=null && right!=null) {
			return root;
		}
		return left!=null ? left : right;
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if(root == p || root == q) {
			return root;
		}
		TreeNode lca = lca(root, p ,q);
		return lca;
	}
	
	/*
	 * If p or q is not found in tree we should return null as there will be no common ancestor at first
	 */
	public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
		boolean[] presentArr = new boolean[2];
		presentArr[0] = false; // p not present (default)
		presentArr[1] = false; // q not present (default)
		TreeNode lca = lca1(root, p, q, presentArr);
		if(presentArr[0] && presentArr[1]) {
			return lca;
		}
		return null;
	}
	
	private TreeNode lca1(TreeNode root, TreeNode p, TreeNode q, boolean[] presentArr) {
		if(root == null) {
			return null;
		}
		TreeNode lca = null;
		if(root == p || root == q) {
			if(root==p) {
				presentArr[0] = true;
			}
			if(root==q) {
				presentArr[1] = true;
			}
			lca =root;
		}
		TreeNode left = lca1(root.left, p, q, presentArr);
		TreeNode right = lca1(root.right, p, q, presentArr);
		if(lca!=null) {
			return lca;
		}
		if(left!=null && right!=null) {
			return root;
		}
		return left!=null ? left : right;
	}

	public static void main(String[] args) {
		LowestCommonAncestorBT lowestCommonAncestor = new LowestCommonAncestorBT();
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(5);
		root.right = new TreeNode(1);
		root.left.left = new TreeNode(6);
		root.left.right = new TreeNode(2);
		root.left.right.left = new TreeNode(7);
		root.left.right.right = new TreeNode(4);
		root.right.left = new TreeNode(0);
		root.right.right = new TreeNode(8);
		TreeNode lca = lowestCommonAncestor.lowestCommonAncestor1(root, root.left, root.right);
		System.out.println("Lowest Common Ancestor of root : "+lca.val);
		TreeNode lca1 = lowestCommonAncestor.lowestCommonAncestor1(root, root.left, root.left.right.right);
		System.out.println("Lowest Common Ancestor of root : "+lca1.val);
		TreeNode root2 = new TreeNode(1);
		root2.left = new TreeNode(2);
		TreeNode lca2 = lowestCommonAncestor.lowestCommonAncestor1(root2, root2.left, root2);
		System.out.println("Lowest Common Ancestor of root : "+lca2.val);
		// case when one of node or both not present in tree
		TreeNode lca3 = lowestCommonAncestor.lowestCommonAncestor(root, root.left.right.right, root2.left);
		if(lca3 == null) {
			System.out.println("Lowest Common Ancestor of root : null");
		} else {
			System.out.println("Lowest Common Ancestor of root : "+lca3.val);
		}
		System.out.println("Lowest Common Ancestor of root : "+lca2.val);
		TreeNode lca4 = lowestCommonAncestor.lowestCommonAncestor1(root, root.left.right.right, root2.left);
		if(lca4 == null) {
			System.out.println("Lowest Common Ancestor1 of root : null");
		} else {
			System.out.println("Lowest Common Ancestor1 of root : "+lca4.val);
		}
	}

}
