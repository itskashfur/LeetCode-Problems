from collections import deque
import heapq

class Solution:
    def maximumSafenessFactor(self, grid: list[list[int]]) -> int:
        n = len(grid)
        
        # If the start or end cell has a thief, safeness factor is immediately 0
        if grid[0][0] == 1 or grid[n-1][n-1] == 1:
            return 0
            
        # Initialize the safeness distance grid with infinity
        # dist[r][c] will store the Manhattan distance to the nearest thief
        dist = [[float('inf')] * n for _ in range(n)]
        queue = deque()
        
        # Step 1: Add all thieves to the queue and set their distance to 0
        for r in range(n):
            for c in range(n):
                if grid[r][c] == 1:
                    dist[r][c] = 0
                    queue.append((r, c))
                    
        # Multi-source BFS to calculate the shortest distance from each cell to any thief
        directions = [(0, 1), (0, -1), (1, 0), (-1, 0)]
        while queue:
            r, c = queue.popleft()
            for dr, dc in directions:
                nr, nc = r + dr, c + dc
                # If neighbor is valid and we found a shorter path to a thief
                if 0 <= nr < n and 0 <= nc < n and dist[nr][nc] == float('inf'):
                    dist[nr][nc] = dist[r][c] + 1
                    queue.append((nr, nc))
                    
        # Step 2: Find the maximum safeness path using a Max-Heap (Dijkstra-like)
        # Python's heapq is a min-heap by default, so we store negative values to simulate a max-heap
        max_heap = [(-dist[0][0], 0, 0)]  # (negative safeness, row, col)
        
        # Track maximum safeness encountered so far for each cell to avoid redundant processing
        max_safeness = [[-1] * n for _ in range(n)]
        max_safeness[0][0] = dist[0][0]
        
        while max_heap:
            d, r, c = heapq.heappop(max_heap)
            current_safeness = -d
            
            # If we reached the bottom-right cell, return the maximum safeness factor achieved
            if r == n - 1 and c == n - 1:
                return current_safeness
                
            for dr, dc in directions:
                nr, nc = r + dr, c + dc
                if 0 <= nr < n and 0 <= nc < n:
                    # The safeness of the path to the neighbor is limited by 
                    # either the current path's safeness or the neighbor's own safeness value
                    path_safeness = min(current_safeness, dist[nr][nc])
                    
                    # If this path offers a higher safeness factor than previously found for this cell
                    if path_safeness > max_safeness[nr][nc]:
                        max_safeness[nr][nc] = path_safeness
                        heapq.heappush(max_heap, (-path_safeness, nr, nc))
                        
        return 0