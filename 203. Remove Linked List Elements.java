203.Remove Linked List Element Problem : JAVA Solution
/*
Remove all elements from a linked list of integers that have value val.

Have you met this question in a real interview? Yes
Example
Given 1->2->3->3->4->5->3, val = 3, you should return the list as 1->2->4->5

Tags Expand 
Linked List
*/

/*
Thoughts:
While loop through. Maintain a parent, so it can be used to skip current node.
*/

class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode node = dummy;
        while (head != null) {
            if (head.val == val) {
                node.next = head.next;
            } else {
                node = node.next;
            }
			head = head.next;    
        }

        return dummy.next;
    }
}
