package amazonInterview_Jul2021;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/*   NOT BOUNDARY TRAVERSAL
 * 								1
 *                             / \
 *                            2   3
 *                           / \   \
 *                          4   5   6
 *                         /     \
 *                        7       8
 *                        
 *       Explanation:
 *        PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP
 *        P       				1          P
 *        P                    / \         P
 *        P                   2   3        P
 *        P                  / \   \       P
 *        P                 4   5   6      P
 *        P                /     \         P
 *        P               7       8        P 
 *        PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP             
 *      o/p => 1 2 4 7 5 8 6 3
 */                      
public class BoundaryViewOfTree {
	
	public void boundaryViewOfTree(TreeNode node) {
		Map<Integer,LinkedList<TreeNode>> levelMap = new TreeMap<>();
		levelOrderTraversal(node, 0, levelMap);
		//printMap(levelMap);
		Map<Integer,LinkedList<TreeNode>> hDistMap = new TreeMap<>();
		horizontalDistance(node, 0, hDistMap);
		//printMap(hDistMap);
		Set<TreeNode> resultSet = new LinkedHashSet<>();
		for(int i=0;i<3;i++) {
			if(i==0) {
				levelMap.entrySet().forEach(entry->{
					resultSet.add(entry.getValue().peekFirst());
				});
			} else if (i==1) {
				hDistMap.entrySet().forEach(entry->{
					resultSet.add(entry.getValue().peekFirst());
				});
			} else {
				levelMap.entrySet().forEach(entry->{
					resultSet.add(entry.getValue().peekLast());
				});
			}
		}
		printResult(resultSet);
		return;
	}
	
	private void levelOrderTraversal(TreeNode node, int level, Map<Integer,LinkedList<TreeNode>> levelMap) {
		if(node == null) {
			return;
		}
		levelMap.putIfAbsent(level, new LinkedList<>());
		levelMap.get(level).add(node);
		levelOrderTraversal(node.left, level+1, levelMap);
		levelOrderTraversal(node.right, level+1, levelMap);
	}
	
	private void horizontalDistance(TreeNode node, int dist, Map<Integer,LinkedList<TreeNode>> hDistMap) {
		if(node == null) {
			return;
		}
		horizontalDistance(node.left, dist-1, hDistMap);
		horizontalDistance(node.right, dist+1, hDistMap);
		hDistMap.putIfAbsent(dist, new LinkedList<>());
		hDistMap.get(dist).add(node);
	}
	
	private void printResult(Set<TreeNode> resultSet) {
		for(TreeNode node: resultSet) {
			System.out.print(node.val+" ");
		}
		System.out.println();
	}
	
	private void printMap(Map<Integer, LinkedList<TreeNode>> map) {
		System.out.println();
		map.entrySet().forEach(entry->{
			System.out.print(entry.getKey()+"=");
			entry.getValue().forEach(list->System.out.print(list.val));
			System.out.println();
		});
	}
	
	public static void main(String[] args) {
		BoundaryViewOfTree bt = new BoundaryViewOfTree();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.right = new TreeNode(6);
		root.left.left.left = new TreeNode(7);
		root.left.right.right = new TreeNode(8);
		bt.boundaryViewOfTree(root);
		TreeNode root1 = new TreeNode(1);
		root1.left = new TreeNode(2);
		root1.right = new TreeNode(3);
		root1.left.left = new TreeNode(4);
		root1.left.right = new TreeNode(5);
		root1.right.right = new TreeNode(6);
		root1.left.left.left = new TreeNode(7);
		root1.left.right.left = new TreeNode(8);
		bt.boundaryViewOfTree(root1);
	}

}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode() {}
	TreeNode(int val) { this.val = val; }
	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}
