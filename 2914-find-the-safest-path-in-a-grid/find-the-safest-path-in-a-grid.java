import java.util.*;

class Solution {
    // 4-directional movement array (Down, Right, Up, Left)
    static final int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();

        // If the start or end cell contains a thief, the safeness factor is instantly 0
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1)
            return 0;

        // Step 1: Copy the List interface matrix into a fast 2D primitive array
        int[][] A = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = grid.get(i).get(j);
            }
        }

        // Queue used for Multi-source Breadth-First Search (BFS)
        Queue<int[]> q = new LinkedList<>();

        // Collect all coordinates containing a thief to begin multi-source BFS
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1) {
                    q.add(new int[]{i, j});
                }
            }
        }

        // Step 2: Multi-source BFS to calculate the Manhattan distance to any thief.
        // It updates empty cells (0) with distances starting incrementally from 1 (1, 2, 3...)
        while (q.size() > 0) {
            int[] head = q.poll();
            int i = head[0];
            int j = head[1];
            int v = A[i][j];

            for (int[] d : dirs) {
                int x = i + d[0];
                int y = j + d[1];

                // Boundary check & confirming cell is unvisited (value is 0)
                if (Math.min(x, y) >= 0 && Math.max(x, y) < n && A[x][y] == 0) {
                    A[x][y] = v + 1; // Mark cell distance
                    q.add(new int[]{x, y});
                }
            }
        }

        // Step 3: Dijkstra-like Pathfinding via a Max-Heap (Priority Queue)
        // Ordered descending by safeness factor (b[0] - a[0]) to always explore the safest paths first
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        pq.add(new int[]{A[0][0], 0, 0}); // {safeness_factor, row, col}

        while (pq.size() > 0) {
            int[] head = pq.poll();
            int sf = head[0];
            int i = head[1];
            int j = head[2];

            // If the target (bottom-right cell) is reached, return the maximum achievable safeness.
            // We subtract 1 because our BFS calculated distances starting from 1 instead of 0.
            if (i == n - 1 && j == n - 1)
                return sf - 1;

            for (int[] d : dirs) {
                int x = i + d[0];
                int y = j + d[1];

                // Boundary check & verifying cell is unvisited (positive distance value)
                if (Math.min(x, y) >= 0 && Math.max(x, y) < n && A[x][y] > 0) {
                    // Path safeness is limited by the bottleneck (minimum) cell value along the path
                    pq.add(new int[]{Math.min(sf, A[x][y]), x, y});
                    
                    // In-place tracking: Negate the value to mark it visited without using an extra boolean array
                    A[x][y] *= -1;
                }
            }
        }

        return A[n - 1][n - 1] - 1;
    }
}