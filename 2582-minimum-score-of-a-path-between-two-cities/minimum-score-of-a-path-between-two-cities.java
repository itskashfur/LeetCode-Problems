class Solution {
    public int minScore(int n, int[][] roads) {
        // WHAT: Build an adjacency list where each node points to a list of pairs (neighbor, distance).
        // WHY: Adjacency lists allow efficient O(1) retrieval of a city's direct connections during traversal.
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int dist = road[2];
            // Since roads are bidirectional, add connections for both endpoints
            adj.get(u).add(new int[]{v, dist});
            adj.get(v).add(new int[]{u, dist});
        }
        
        // WHAT: Track visited nodes to avoid processing the same city infinitely.
        // WHY: Prevents cyclic loops and ensures every node in the component is processed exactly once.
        boolean[] visited = new boolean[n + 1];
        
        // WHAT: Initialize standard Breadth-First Search (BFS) queue.
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;
        
        int minScore = Integer.MAX_VALUE;
        
        // WHAT: Traverse the graph using BFS starting from city 1.
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            
            // WHAT: Inspect all connected roads for the current city.
            for (int[] neighbor : adj.get(curr)) {
                int nextNode = neighbor[0];
                int distance = neighbor[1];
                
                // WHAT: Update the minimum score globally with every encountered edge in this component.
                // WHY: We can always detour to cross this specific road and come back.
                minScore = Math.min(minScore, distance);
                
                // WHAT: If the neighboring city hasn't been visited, add it to the queue.
                if (!visited[nextNode]) {
                    visited[nextNode] = true;
                    queue.offer(nextNode);
                }
            }
        }
        return minScore;
    }
}