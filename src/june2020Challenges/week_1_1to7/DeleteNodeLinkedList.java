package june2020Challenges.week_1_1to7;

public class DeleteNodeLinkedList {
	
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	public void deleteNode(ListNode node) {
        ListNode nextNode = node.next;
        node.val = nextNode.val;
        node.next = nextNode.next;
    }
	
	public void printList(ListNode node) {
		while(node!=null) {
			System.out.print(node.val+",");
			node = node.next;
		}
	}

	public static void main(String[] args) {
		DeleteNodeLinkedList list = new DeleteNodeLinkedList();
		ListNode head = list.new ListNode(1);
		head.next = list.new ListNode(5);
		head.next.next = list.new ListNode(7);
		head.next.next.next = list.new ListNode(9);
		list.printList(head);
		System.out.println();
		list.deleteNode(head.next.next);
		System.out.println("---After delete---");
		list.printList(head);
	}

}
