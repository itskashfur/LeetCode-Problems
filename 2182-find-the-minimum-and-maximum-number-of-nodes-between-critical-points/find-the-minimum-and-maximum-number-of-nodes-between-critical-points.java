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
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        ListNode prev = head;
        ListNode curr = head.next;
        int index = 1;

        int firstCritical = -1;
        int prevCritical = -1;
        int minDistance = Integer.MAX_VALUE;

        while (curr.next != null) {
            ListNode next = curr.next;

            // Check if current node is a critical point
            boolean isLocalMaxima = (curr.val > prev.val) && (curr.val > next.val);
            boolean isLocalMinima = (curr.val < prev.val) && (curr.val < next.val);

            if (isLocalMaxima || isLocalMinima) {
                if (firstCritical == -1) {
                    firstCritical = index;
                } else {
                    minDistance = Math.min(minDistance, index - prevCritical);
                }
                prevCritical = index;
            }

            prev = curr;
            curr = next;
            index++;
        }

        // Return [-1, -1] if fewer than two critical points are found
        if (firstCritical == -1 || prevCritical == firstCritical) {
            return new int[]{-1, -1};
        }

        int maxDistance = prevCritical - firstCritical;
        return new int[]{minDistance, maxDistance};
    }
}