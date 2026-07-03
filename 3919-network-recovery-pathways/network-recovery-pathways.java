import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        
        // WHAT: Build an adjacency list to represent our Directed Acyclic Graph (DAG).
        // WHY: An adjacency list allows efficient O(1) lookups of neighboring nodes during traversal.
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        int maxCost = 0;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];
            // WHAT: Only add edges where the destination node 'v' is online (or is the destination n-1).
            // WHY: If a node is offline, no valid path can pass through it. Skipping it early saves processing time.
            if (online[v]) {
                adj.get(u).add(new int[]{v, cost});
                maxCost = Math.max(maxCost, cost);
            }
        }
        
        // WHAT: Initialize Binary Search boundaries.
        // WHY: The path score must be between 0 and the maximum edge cost present in the graph.
        int low = 0, high = maxCost;
        int ans = -1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            // WHAT: Check if a valid path exists where every edge has a cost >= mid and total cost <= k.
            // WHY: If it's possible, we try to find a larger minimum edge cost by searching the right half.
            if (isValid(n, adj, k, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return ans;
    }
    
    private boolean isValid(int n, List<List<int[]>> adj, long k, int minEdgeCost) {
        // WHAT: Min-Heap for Dijkstra's algorithm storing pairs of {accumulated_cost, node}.
        // WHY: Dijkstra's greedily expands the path with the lowest cumulative cost, ensuring we find the absolute shortest path to n-1.
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        
        // WHAT: Create a distance tracker initialized to infinity.
        // WHY: We track the minimum cost to reach each node to avoid revisiting cells with a less optimal path.
        long[] minCost = new long[n];
        Arrays.fill(minCost, Long.MAX_VALUE);
        
        minCost[0] = 0;
        pq.offer(new long[]{0, 0}); // {cost, node}
        
        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long currentCost = curr[0];
            int u = (int) curr[1];
            
            // WHAT: Early exit check if we reached the final destination node.
            // WHY: Since it's a min-heap, the first time node n-1 is extracted, it is guaranteed to have the minimum path cost.
            if (u == n - 1) {
                return currentCost <= k;
            }
            
            // WHAT: Optimization skip.
            // WHY: If we already found a cheaper way to reach node 'u', discard this older, more expensive path.
            if (currentCost > minCost[u]) continue;
            
            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int edgeCost = neighbor[1];
                
                // WHAT: Filter condition matching our Binary Search criteria.
                // WHY: We completely ignore edges that don't satisfy our minimum edge cost threshold.
                if (edgeCost >= minEdgeCost) {
                    long nextCost = currentCost + edgeCost;
                    
                    // WHAT: Relaxation step if a cheaper path to node 'v' is discovered.
                    // WHY: We only push to the queue if this path minimizes recovery expenses and stays within budget limit 'k'.
                    if (nextCost <= k && nextCost < minCost[v]) {
                        minCost[v] = nextCost;
                        pq.offer(new long[]{nextCost, v});
                    }
                }
            }
        }
        
        return false;
    }
}