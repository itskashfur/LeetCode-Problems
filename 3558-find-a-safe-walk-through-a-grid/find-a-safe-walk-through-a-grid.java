class Solution 
{
    public boolean findSafeWalk(List<List<Integer>> grid, int health) 
    {
        // WHAT: Get the number of rows (m) and columns (n) of the grid.
        // WHY: We need these boundaries to perform grid traversal and to know when we've reached the bottom-right corner destination.
        int m = grid.size(), n = grid.get(0).size();
        
        // WHAT: Create a 2D array 'dist' to track the minimum health damage taken to reach each cell.
        // WHY: Tracking the minimum damage per cell prevents revisiting cells with a worse or equal health status, which avoids infinite loops.
        int[][] dist = new int[m][n];
        
        // WHAT: Initialize all cell values in 'dist' to Integer.MAX_VALUE (infinity).
        // WHY: Before exploring, we assume all cells are unreachable. This ensures any valid calculated damage will be smaller than the initial value.
        for (int[] row : dist)
        {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        // WHAT: Create a Double-Ended Queue (Deque) to handle 0-1 BFS operations.
        // WHY: Traditional BFS doesn't work for weighted graphs, and Dijkstra's PriorityQueue takes O(log N) per operation. 
        // Since our edge weights are strictly 0 or 1, a Deque allows O(1) processing by sorting values automatically based on where we insert them.
        Deque<int[]> dq = new ArrayDeque<>();
        
        // WHAT: Set the initial starting damage at cell (0, 0) to its grid value, and push it to the front of the deque.
        // WHY: The problem specifies that starting on an unsafe cell immediately reduces health, so we must capture the initial cell's damage value.
        dist[0][0] = grid.get(0).get(0);
        dq.offerFirst(new int[]{0, 0});
        
        // WHAT: Define a compact 4-directional layout array. 
        // Pairwise combinations: (-1,0) = Up, (0,1) = Right, (1,0) = Down, (0,-1) = Left.
        // WHY: It eliminates boilerplate nested arrays or repetitive copy-pasted code Blocks for evaluating adjacent neighbors.
        int[] dir = {-1, 0, 1, 0, -1};
        
        // WHAT: Process cells sequentially until the deque is entirely empty.
        // WHY: This standard graph traversal structure guarantees we evaluate all reachable path combinations.
        while (!dq.isEmpty())
        {
            int[] curr = dq.pollFirst();
            int r = curr[0], c = curr[1];
            
            // WHAT: Check if we have arrived at the destination cell (m - 1, n - 1).
            // WHY: Because 0-1 BFS processes paths in strictly increasing order of cumulative cost, the first time we extract the destination cell, it is guaranteed to be the absolute minimum damage path.
            if (r == m - 1 && c == n - 1)
            {
                // If total damage taken is strictly less than our starting health, we survived with 1+ health remaining.
                return dist[r][c] < health;
            }
            
            // WHAT: Loop exactly 4 times to check all adjacent neighbors (Up, Right, Down, Left).
            for (int i = 0; i < 4; i++)
            {
                int nr = r + dir[i];
                int nc = c + dir[i + 1];
                
                // WHAT: Verify the neighbor cell is within the valid grid boundaries.
                // WHY: Attempting to access grid coordinates out of bounds results in an ArrayIndexOutOfBoundsException.
                if (nr >= 0 && nr < m && nc >= 0 && nc < n)
                {
                    // Get the damage cost of the neighbor cell (0 if safe, 1 if unsafe).
                    int weight = grid.get(nr).get(nc);
                    
                    // WHAT: Check if moving from the current cell to this neighbor yields a lower cumulative damage than previously recorded.
                    // WHY: We only update and explore a path if we find a safer/cheaper way to reach that specific coordinate.
                    if (dist[r][c] + weight < dist[nr][nc]) 
                    {
                        dist[nr][nc] = dist[r][c] + weight;
                        // Update to the newly discovered lower damage cost.
                        // WHAT: 0-1 BFS Optimization: If the cell is safe (weight 0), push to the front. If unsafe (weight 1), push to the back.
                        // WHY: This maintains strict sorting order inside the queue without sorting overhead. It ensures that shorter, safer paths are always completely evaluated before riskier paths.
                        
                        if (weight == 0)
                        {
                            dq.offerFirst(new int[]{nr, nc});
                        }
                        else
                        {
                            dq.offerLast(new int[]{nr, nc});
                        }
                    }
                }
            }
        }

        // WHAT: Fallback return statement evaluating the destination cell's total damage.
        // WHY: Required by the compiler syntax for cases where the loop completes, verifying if the final path found was safe enough.
        return dist[m - 1][n - 1] < health;
    }
}