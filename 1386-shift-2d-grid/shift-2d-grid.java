import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        
        // Modulo k by total elements to eliminate full array rotations
        k = k % total;
        
        // Initialize the output list with empty rows
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(0); // Pre-fill with placeholder zeros
            }
            result.add(row);
        }
        
        // Map each element from original grid to its new position in result
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Convert 2D index (i, j) to 1D index
                int old1D = i * n + j;
                
                // Compute target 1D index after k shifts
                int new1D = (old1D + k) % total;
                
                // Convert 1D target index back to 2D coordinates
                int newRow = new1D / n;
                int newCol = new1D % n;
                
                // Place current element into its target location
                result.get(newRow).set(newCol, grid[i][j]);
            }
        }
        
        return result;
    }
}