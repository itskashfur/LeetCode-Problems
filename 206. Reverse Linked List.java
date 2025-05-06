206. Reverse Linked List Problem : JAVA Solution
```
/*
Reverse a linked list.

Example
For linked list 1->2->3, the reversed linked list is 3->2->1

Challenge
Reverse it in-place and in one-pass

Tags Expand 
Linked List Facebook Uber
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// 1) create dummy = head. 2) always insert in front of dummy, and set dummy to new head 3) move head = head.next
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) return head;
        
        ListNode dummy = null;
        while(head != null) {
            ListNode temp = head.next;
            head.next = dummy;
            dummy = head;
            head = temp;
        }
        return dummy;
    }
}

// recursive
class Solution {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        return reverse(head, null);
    }
    
    public ListNode reverse(ListNode source, ListNode target) {
        if (source == null) return target;
        
        ListNode temp = source.next;
        source.next = target;
        target = source;
        source = temp;
        return reverse(source, target);
    }
    
}
```
