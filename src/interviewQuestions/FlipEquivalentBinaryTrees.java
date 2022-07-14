package interviewQuestions;
/*  **MEDIUM**      951.Flip Equivalent Binary Trees
For a binary tree T, we can define a flip operation as follows: choose any node, and
swap the left and right child subtrees.

A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y
 after some number of flip operations.

Given the roots of two binary trees root1 and root2, return true if the two trees are flip
equivalent or false otherwise.

Example 1:
Flipped Trees Diagram
Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,
null,8,7]
Output: true
Explanation: We flipped at nodes with values 1, 3, and 5.

Example 2:
Input: root1 = [], root2 = []
Output: true

Example 3:
Input: root1 = [], root2 = [1]
Output: false

Constraints:
The number of nodes in each tree is in the range [0, 100].
Each tree will have unique node values in the range [0, 99].

 */
public class FlipEquivalentBinaryTrees {

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2==null){
            return true;
        } else if (root1 == null || root2 == null){
            return false;
        }
        return root1.val == root2.val && (flipEquiv(root1.left, root2.right) || flipEquiv(root1.left, root2.left)) && (flipEquiv(root1.right, root2.left) || flipEquiv(root1.right, root2.right));
    }

    public static void main(String[] args) {
        FlipEquivalentBinaryTrees flipBt = new FlipEquivalentBinaryTrees();
        TreeNode root1 = new TreeNode(1);
        TreeNode root2 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root1.right = new TreeNode(3);
        root2.left = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.left.right.left = new TreeNode(7);
        root1.left.right.right = new TreeNode(8);
        root1.right.left = new TreeNode(6);
        root2.left.right = new TreeNode(6);
        root2.right.left = new TreeNode(4);
        root2.right.right = new TreeNode(5);
        root2.right.right.left = new TreeNode(8);
        root2.right.right.right = new TreeNode(7);
        System.out.println("Flip Equivalent Binary Trees of root1 and root2 : "+
                flipBt.flipEquiv(root1, root2));
        TreeNode root3 = new TreeNode(1);
        TreeNode root4 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root4.right = new TreeNode(2);
        root3.right = new TreeNode(3);
        root4.left = new TreeNode(3);
        root3.left.left = new TreeNode(4);
        root3.left.right = new TreeNode(5);
        root3.left.right.left = new TreeNode(17);
        root3.left.right.right = new TreeNode(8);
        root3.right.left = new TreeNode(6);
        root4.left.right = new TreeNode(6);
        root4.right.left = new TreeNode(4);
        root4.right.right = new TreeNode(15);
        root4.right.right.left = new TreeNode(8);
        root4.right.right.right = new TreeNode(7);
        System.out.println("Flip Equivalent Binary Trees of root3 and root4 : "+
                flipBt.flipEquiv(root3, root4));
    }
}
