package interviewQuestions;

/* **Easy**    206. Reverse Linked List
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
	Example 1:
		Input: head = [1,2,3,4,5]
		Output: [5,4,3,2,1]
	Example 2:
		Input: head = [1,2]
		Output: [2,1]
	Example 3:
		Input: head = []
		Output: []
		
	Constraints:
		The number of nodes in the list is the range [0, 5000].
		-5000 <= Node.val <= 5000
	Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?	
 */
public class ReverseLinkedList {
	
	public ListNode reverseListIterative(ListNode head) {
        ListNode temp = head;
        ListNode prev = null;
        while(temp!=null){
            ListNode next = temp.next;
            temp.next = prev;
            prev = temp;
            temp = next;
        }
        return prev;
    }
	
	public ListNode reverseListRecursive(ListNode head) {
		if(head == null || head.next == null) {
			return head;
		}
		ListNode temp = reverseListRecursive(head.next);
		head.next.next = head;
		head.next = null;
		return temp;
    }
	
	public void printList(ListNode head) {
		ListNode temp = head;
		while(temp!=null) {
			System.out.print(temp.val);
			temp = temp.next;
			if(temp!=null) {
				System.out.print("->");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
		ListNode head = new ListNode(1);
		head.next =  new ListNode(2);
		head.next.next =  new ListNode(3);
		head.next.next.next =  new ListNode(4);
		head.next.next.next.next =  new ListNode(5);
		System.out.println("Before reverse : ");
		reverseLinkedList.printList(head);
		ListNode newHead = reverseLinkedList.reverseListRecursive(head);
		System.out.println("After reverse : ");
		reverseLinkedList.printList(newHead);
	}
	
	
}

//Definition for singly-linked list.
class ListNode {
   int val;
   ListNode next;
   ListNode() {}
   ListNode(int val) { this.val = val; }
   ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
