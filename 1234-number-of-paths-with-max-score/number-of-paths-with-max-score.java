class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1_000_000_007;

        // WHAT: dp[j][0] stores max_score, dp[j][1] stores path_count for column j
        // WHY: We optimize space to O(n) by using a single row that updates dynamically
        int[][] dp = new int[n][2];
        for (int j = 0; j < n; j++) {
            dp[j][0] = -1; // Initialize all cells as unreachable
        }

        // Base case: Starting point 'S' at the bottom-right corner
        dp[n - 1][0] = 0;
        dp[n - 1][1] = 1;

        // WHAT: Iterate backwards from the bottom row to the top row
        for (int i = n - 1; i >= 0; i--) {
            String rowStr = board.get(i);
            
            // WHAT: Iterate backwards from the rightmost column to the left
            for (int j = n - 1; j >= 0; j--) {
                char c = rowStr.charAt(j);

                // Skip the starting square 'S' since it's already initialized
                if (i == n - 1 && j == n - 1) continue;

                // If it's an obstacle, it cannot be part of any path
                if (c == 'X') {
                    dp[j][0] = -1;
                    dp[j][1] = 0;
                    continue;
                }

                int maxScore = -1;
                int paths = 0;

                // Check the three possible incoming directions: Down, Right, and Diagonal (Down-Right)
                // 1. Down neighbor: dp[j] (from previous iteration of row 'i+1')
                if (i + 1 < n && dp[j][0] != -1) {
                    if (dp[j][0] > maxScore) { maxScore = dp[j][0]; paths = dp[j][1]; }
                    else if (dp[j][0] == maxScore) { paths = (paths + dp[j][1]) % MOD; }
                }
                // 2. Right neighbor: dp[j+1] (already updated in the current row 'i')
                if (j + 1 < n && dp[j + 1][0] != -1) {
                    if (dp[j + 1][0] > maxScore) { maxScore = dp[j + 1][0]; paths = dp[j + 1][1]; }
                    else if (dp[j + 1][0] == maxScore) { paths = (paths + dp[j + 1][1]) % MOD; }
                }
                // 3. Diagonal neighbor: We need the old dp[j+1] from row 'i+1'. 
                // To do this simply with O(n) space without overwriting, we can look at a 2D 2-row layout.
                // For simplicity of execution and robust O(n) tracking, let's use a standard 2-row allocation.
            }
        }
        
        // Let's rewrite the inner structure with a clean 2-row DP matrix to keep it rock-solid.
        int[][][] gridDp = new int[2][n][2];
        for (int j = 0; j < n; j++) {
            gridDp[0][j][0] = -1;
            gridDp[1][j][0] = -1;
        }
        
        gridDp[(n - 1) % 2][n - 1][0] = 0;
        gridDp[(n - 1) % 2][n - 1][1] = 1;

        for (int i = n - 1; i >= 0; i--) {
            int currRow = i % 2;
            int nextRow = (i + 1) % 2;
            String rowStr = board.get(i);

            for (int j = n - 1; j >= 0; j--) {
                if (i == n - 1 && j == n - 1) continue;
                char c = rowStr.charAt(j);

                if (c == 'X') {
                    gridDp[currRow][j][0] = -1;
                    gridDp[currRow][j][1] = 0;
                    continue;
                }

                int maxScore = -1;
                int paths = 0;

                // Look Down
                if (i + 1 < n && gridDp[nextRow][j][0] != -1) {
                    maxScore = gridDp[nextRow][j][0];
                    paths = gridDp[nextRow][j][1];
                }
                // Look Right
                if (j + 1 < n && gridDp[currRow][j + 1][0] != -1) {
                    if (gridDp[currRow][j + 1][0] > maxScore) {
                        maxScore = gridDp[currRow][j + 1][0];
                        paths = gridDp[currRow][j + 1][1];
                    } else if (gridDp[currRow][j + 1][0] == maxScore) {
                        paths = (paths + gridDp[currRow][j + 1][1]) % MOD;
                    }
                }
                // Look Diagonal (Down-Right)
                if (i + 1 < n && j + 1 < n && gridDp[nextRow][j + 1][0] != -1) {
                    if (gridDp[nextRow][j + 1][0] > maxScore) {
                        maxScore = gridDp[nextRow][j + 1][0];
                        paths = gridDp[nextRow][j + 1][1];
                    } else if (gridDp[nextRow][j + 1][0] == maxScore) {
                        paths = (paths + gridDp[nextRow][j + 1][1]) % MOD;
                    }
                }

                if (maxScore == -1) {
                    gridDp[currRow][j][0] = -1;
                    gridDp[currRow][j][1] = 0;
                } else {
                    int val = (c == 'E') ? 0 : (c - '0');
                    gridDp[currRow][j][0] = maxScore + val;
                    gridDp[currRow][j][1] = paths;
                }
            }
        }

        int[] res = gridDp[0][0];
        return res[0] == -1 ? new int[]{0, 0} : res;
    }
}