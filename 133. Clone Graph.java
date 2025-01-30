133.Clone Graph Problem : JAVA Solution
/*
Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph. 
Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.

 
Input:
{"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},{"$id":"3","neighbors":[{"$ref":"2"},{"$id":"4","neighbors":[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},{"$ref":"4"}],"val":1}

Explanation:
Node 1's value is 1, and it has two neighbors: Node 2 and 4.
Node 2's value is 2, and it has two neighbors: Node 1 and 3.
Node 3's value is 3, and it has two neighbors: Node 2 and 4.
Node 4's value is 4, and it has two neighbors: Node 1 and 3.
 

Note:

The number of nodes will be between 1 and 100.
The undirected graph is a simple graph, which means no repeated edges and no self-loops in the graph.
Since the graph is undirected, if node p has node q as neighbor, then node q must have node p as neighbor too.
You must return the copy of the given node as a reference to the cloned graph.


*/
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};
*/

/*
DFS
- Mark visited in map
- Add neibhors via dfs
- return node
*/
public class Solution {
    // old -> node node map
    Map<Node, Node> map = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (node == null) return node;
        if (map.containsKey(node)) return map.get(node);

        Node newNode = new Node(node.val, new ArrayList<>());
        map.put(node, newNode);
        for (Node neighbor: node.neighbors) {
            newNode.neighbors.add(cloneGraph(neighbor));
        }
        return newNode;
    }
}

/*
Thougths:
DFS with map in graph.
The serialized graph is just explaination for the test input.
1. copy the node
2. Mark 'added' using map(old, new)
3. for loop on the each one of the neighbors: map copy, record in map, and further dfs
4. once dfs completes, add newNeighbor as neighbor of the new node (get to it via map)
*/
public class Solution {
    HashMap<Node, Node> map = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (node == null) return null;
       
        map.put(node, new Node(node.val, new ArrayList<>()));
        dfs(node);
        return map.get(node);
    }
    
    public void dfs(Node node) {
        if (node == null) return;
        for (Node neighbor: node.neighbors) {
            if (!map.containsKey(neighbor)) {
                map.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                dfs(neighbor);
            }
            map.get(node).neighbors.add(map.get(neighbor));
        }
    }
}

/*
Thougths:
BFS, same concept as DFS.
1. Copy the root node, then copy all the neighbors. 
2. Mark copied node in map.
3. Use queue to contain the neighbors for next round: if it has neighbors
*/
public class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        
        HashMap<Node, Node> map = new HashMap<>();
        map.put(node, new Node(node.val, new ArrayList<>())); // copy root

        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        
        while(!queue.isEmpty()) {
            Node curr = queue.poll();
            for (Node neighbor: curr.neighbors) {
                if (!map.containsKey(neighbor)) { // Init neighbors
                    queue.offer(neighbor);
                    map.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                }
                map.get(curr).neighbors.add(map.get(neighbor)); // link neighbor
            }
        }
        
        return map.get(node);
    }
}
```
