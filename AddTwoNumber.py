class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

    @staticmethod
    def deserialize(data):
        if not data:
            return None
        data = data.strip('[]')
        nodes = data.split(',')
        dummy_head = ListNode(0)
        current = dummy_head
        for node in nodes:
            current.next = ListNode(int(node.strip()))
            current = current.next
        return dummy_head.next

    @staticmethod
    def serialize(node):
        result = []
        while node:
            result.append(str(node.val))
            node = node.next
        return '[' + ','.join(result) + ']'

class Solution:
    def addTwoNumbers(self, l1, l2):
        dummy_head = ListNode(0)
        p, q, curr = l1, l2, dummy_head
        carry = 0
        while p is not None or q is not None:
            x = p.val if p is not None else 0
            y = q.val if q is not None else 0
            sum = carry + x + y
            carry = sum // 10
            curr.next = ListNode(sum % 10)
            curr = curr.next
            if p is not None:
                p = p.next
            if q is not None:
                q = q.next
        if carry > 0:
            curr.next = ListNode(carry)
        return dummy_head.next

# Example usage
if __name__ == "__main__":
    l1 = ListNode.deserialize("[2,4,3]")
    l2 = ListNode.deserialize("[5,6,4]")

    sol = Solution()
    result = sol.addTwoNumbers(l1, l2)

    # Print the result
    print(ListNode.serialize(result))
