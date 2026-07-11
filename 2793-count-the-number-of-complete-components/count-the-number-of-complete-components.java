class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        // Line 1: Create an adjacency list to represent the graph.
        // We create an array of lists where adj[i] will store all neighbors of node i.
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        // Line 2: Create an array to keep track of how many connections (degree) each node has.
        int[] degree = new int[n];

        // Line 3: Populate the adjacency list and calculate the degree for each node.
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj[u].add(v);
            adj[v].add(u);
            degree[u]++; // Node u gets a new connection
            degree[v]++; // Node v gets a new connection
        }

        // Line 4: Keep track of which nodes we have already visited to avoid checking them again.
        boolean[] visited = new boolean[n];
        int completeComponentsCount = 0;

        // Line 5: Loop through every node from 0 to n-1.
        for (int i = 0; i < n; i++) {
            // If we haven't explored this node's group yet, let's explore it!
            if (!visited[i]) {
                // These lists will store all nodes belonging to the current group.
                List<Integer> componentNodes = new ArrayList<>();
                
                // Using Breadth-First Search (BFS) to find everyone in this group.
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                visited[i] = true;

                while (!queue.isEmpty()) {
                    int curr = queue.poll();
                    componentNodes.add(curr); // Add the node to our group list

                    // Check all direct neighbors of the current node
                    for (int neighbor : adj[curr]) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.add(neighbor);
                        }
                    }
                }

                // Line 6: Verify if the group we just found is "complete".
                int totalNodesInComponent = componentNodes.size();
                boolean isComplete = true;

                // For a component to be complete, every node inside it MUST 
                // have a degree exactly equal to (total nodes in component - 1).
                for (int node : componentNodes) {
                    if (degree[node] != totalNodesInComponent - 1) {
                        isComplete = false; // Someone doesn't have enough friends!
                        break;
                    }
                }

                // If everyone passed the test, increment our answer.
                if (isComplete) {
                    completeComponentsCount++;
                }
            }
        }

        // Line 7: Return the final count of complete groups found.
        return completeComponentsCount;
    }
}