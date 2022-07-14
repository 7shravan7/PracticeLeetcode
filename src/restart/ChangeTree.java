package restart;

public class ChangeTree {

    public static void changeTree(BinaryTreeNode < Integer > root) {
        preorderVal(root);
    }

    private static void preorderVal(BinaryTreeNode<Integer> node){
        if(node == null){
            return;
        }
        int childSum = 0;
        childSum += node.left!=null ? node.left.data : 0;
        childSum += node.right!=null ? node.right.data : 0;
        if(node.data>childSum) {
            if(node.left!=null){
                node.left.data = node.data;
            } else if(node.right!=null){
                node.right.data = node.data;
            }
        }
        preorderVal(node.left);
        preorderVal(node.right);
        int sum = 0;
        sum += node.left!=null ? node.left.data : 0;
        sum += node.right!=null ? node.right.data : 0;
        if(node.left!=null || node.right!=null){
            node.data = sum;
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(35);
        root.left = new BinaryTreeNode<>(9);
        root.right = new BinaryTreeNode<>(10);
        root.left.left = new BinaryTreeNode<>(43);
        root.left.right = new BinaryTreeNode<>(14);
        root.right.left = new BinaryTreeNode<>(7);
        root.right.right = new BinaryTreeNode<>(9);
        changeTree(root);
    }
}


class BinaryTreeNode < Integer > {
    int data;
    BinaryTreeNode < Integer > left;
    BinaryTreeNode < Integer > right;

    public BinaryTreeNode(int data) {
        this.data = data;
    }
}
