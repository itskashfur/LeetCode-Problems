/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
 /*I will reWrite this with the help of gemini because i have write comments on my code for future */
class Solution 
{
    public ListNode deleteMiddle(ListNode head) 
    {
        // WHAT: Edge case check—if the list has only 1 node, deleting the middle leaves it empty.
        // WHY: A list of size 1 has its middle at index 0. Removing it results in an empty list (`null`).
        if (head.next == null) return null;

        // WHAT: Initialize two pointers. 'slow' starts at head, 'fast' starts two nodes ahead.
        // WHY: Starting 'fast' two nodes ahead ensures that when 'fast' reaches the end of the list, 
        // 'slow' will stop exactly ONE node *before* the actual middle node (the predecessor).
        ListNode slow = head;
        ListNode fast = slow.next.next;

        // WHAT: Loop until the 'fast' pointer runs out of nodes ahead.
        // WHY: 'fast' moves twice as quick as 'slow'. This loop maintains that perfect 2:1 distance ratio.
        while (fast != null && fast.next != null) 
        {
            slow = slow.next;          // Move slow 1 step
            fast = fast.next.next;     // Move fast 2 steps
        }

        // WHAT: Skip over the middle node by linking 'slow' directly to the node after it.
        // WHY: Since 'slow' is pointing to the predecessor of the middle node, changing its '.next' 
        // pointer directly drops the middle node out of the chain completely.
        slow.next = slow.next.next;

        // WHAT: Return the head of the modified list.
        // WHY: The structural mutation happened in place downstream, so the original 'head' pointer remains valid.
        return head;
    }
}