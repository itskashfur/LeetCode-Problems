class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // WHAT: Create an array of pairs to sort nodes by value while retaining original indices.
        int[][] sortedPairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedPairs[i][0] = nums[i];
            sortedPairs[i][1] = i; // original index
        }
        // Sort ascending by value
        Arrays.sort(sortedPairs, (a, b) -> Integer.compare(a[0], b[0]));
        
        // WHAT: 'pos[i]' stores the position of original index 'i' in the sorted array.
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[sortedPairs[i][1]] = i;
        }
        // Determine maximum power of 2 needed for binary lifting table columns
        int maxL = 32 - Integer.numberOfLeadingZeros(n) + 1;
        int[][] up = new int[maxL][n];
        
        // WHAT: Use Sliding Window to find the furthest reachable right neighbor within maxDiff.
        // WHY: Greedily jumping as far as possible minimizes the total edge count (shortest path).
        int r = 0;
        for (int l = 0; l < n; l++) {
            while (r + 1 < n && sortedPairs[r + 1][0] - sortedPairs[l][0] <= maxDiff) {
                r++;
            }
            up[0][l] = r; // 2^0 = 1 jump from 'l' lands at 'r'
        }
        
        // WHAT: Build the binary lifting table up[j][i].
        // WHY: Allows computing large jump combinations efficiently.
        for (int j = 1; j < maxL; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }
        int qz = queries.length;
        int[] ans = new int[qz];
        // WHAT: Answer each query using binary lifting increments.
        for (int i = 0; i < qz; i++) {
            int uOriginal = queries[i][0];
            int vOriginal = queries[i][1];
            
            // Map original indices to sorted positions
            int u = pos[uOriginal];
            int v = pos[vOriginal];
            
            // Standardize: ensure u is the smaller position index
            if (u > v) {
                int temp = u; u = v; v = temp;
            }
            
            if (u == v) {
                ans[i] = 0;
            } else if (up[0][u] >= v) {
                ans[i] = 1; // Can reach directly in 1 jump
            } else if (up[maxL - 1][u] < v) {
                ans[i] = -1; // Unreachable even with maximum capacity jumps
            } else {
                int steps = 0;
                // Move from largest power of two down to smallest
                for (int j = maxL - 1; j >= 0; j--) {
                    if (up[j][u] < v) {
                        steps += (1 << j);
                        u = up[j][u]; // Update current node position
                    }
                }
                // One more final jump to bridge or overshoot past target v
                ans[i] = steps + 1;
            }
        }
        return ans;
    }
}