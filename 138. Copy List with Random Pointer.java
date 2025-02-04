138.Copy list with Random pointer problem : JAVA Solution
/*
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

 
Input:
{
    "$id":"1",
    "next":{
        "$id":"2",
        "next":null,
        "random":{"$ref":"2"},
        "val":2
    },
    "random":{"$ref":"2"},
    "val":1
}

Explanation:
Node 1's value is 1, both of its next and random pointer points to Node 2.
Node 2's value is 2, its next pointer points to null and its random pointer points to itself.

*/  

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */

/*
    Iterative through the list. 
    Use a dummyHead and return dummyHead.next at the end.
    In each iteration, check if Head is already exist, or make a new one
    * use HashMap<oldNode, newNode> to mark if a node has been visited.
    deep copy the random node of head as well.
    
    border case: if head == null, return null
*/

class Solution {
    public Node copyRandomList(Node head) {
        Node node = new Node(); //creat node, used to iterate over all nodes
        Node dummy = node;
        HashMap<Node, Node> map = new HashMap<>(); // <original, new>
        
        while(head != null) {
            // replicate head and head.random
            replicateNode(map, head);
            replicateNode(map, head.random);
            
            node.next = map.get(head);
            node.next.random = map.get(head.random);

            node = node.next;
            head = head.next;
        }
        return dummy.next;
    }
    
    private void replicateNode(HashMap<Node, Node> map, Node original) {
        if (original == null) return;
        if (!map.containsKey(original)) {
            Node newNode = new Node();
            newNode.val = original.val;
            map.put(original, newNode);
        }
    }
}


/*
Thinking process:
1. Loop through the original list
2. Use a HashMap<old node, new node>. User the old node as a key and new node as value.
3. Doesn't matter of the order of node that being added into the hashMap.
    For example, node1 is added.
    node1.random, which is node 99, will be added into hashMap right after node1.
4. During the loop:
    If head exist in hashmap, get it; if not existed, create new node using head, add into hashMap
    If head.random exist, get it; if not, add a new node using head.random.

*/
public class Solution {
    public Node copyRandomList(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node dummy = new Node();
        Node pre = dummy;
        Node newNode;
        while (head != null) {
            //Add new node 
            if (map.containsKey(head)) {
                newNode = map.get(head);
            } else {
                newNode = new Node();
                newNode.val = head.val;
                map.put(head, newNode);
            }
            //Add new node's random node
            if (head.random != null) {
                if (map.containsKey(head.random)) {
                    newNode.random = map.get(head.random);
                } else {
                    newNode.random = new Node(head.random.val);
                    map.put(head.random, newNode.random);
                }
            }
            //append and shift
            pre.next = newNode;
            pre = newNode;
            head = head.next;
        }
        return dummy.next;
    }
}
