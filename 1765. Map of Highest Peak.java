/*
1765. Map of Highest Peak

You are given an integer matrix isWater of size m x n that represents a map of land and water cells.

If isWater[i][j] == 0, cell (i, j) is a land cell.
If isWater[i][j] == 1, cell (i, j) is a water cell.
You must assign each cell a height in a way that follows these rules:

The height of each cell must be non-negative.
If the cell is a water cell, its height must be 0.
Any two adjacent cells must have an absolute height difference of at most 1. A cell is adjacent to another cell if the former is directly north, east, south, or west of the latter (i.e., their sides are touching).
Find an assignment of heights such that the maximum height in the matrix is maximized.

Return an integer matrix height of size m x n where height[i][j] is cell (i, j)'s height. If there are multiple solutions, return any of them.

Example 1:

Input: isWater = [[0,1],[0,0]]
Output: [[1,0],[2,1]]
Explanation: The image shows the assigned heights of each cell.
The blue cell is the water cell, and the green cells are the land cells.

Example 2:

Input: isWater = [[0,0,1],[1,0,0],[0,0,0]]
Output: [[1,1,0],[0,1,1],[1,2,2]]
Explanation: A height of 2 is the maximum possible height of any assignment.
Any height assignment that has a maximum height of 2 while still meeting the rules will also be accepted.
 

Constraints:

m == isWater.length
n == isWater[i].length
1 <= m, n <= 1000
isWater[i][j] is 0 or 1.
There is at least one water cell.

Idea

Firstly, we fill water cells with height = 0 and start to fill from their cells.
We prioritize filling cells with lowest height by using BFS (using queue, first in first out).
Because any two adjacent cells must have an absolute height difference of at most 1, we can set height of neighbors maximum 1 height bigger than current cell.

Complexity:

Time & Space: O(m * n)

*/
class Solution {
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length, n = isWater[0].length;
        Queue<int[]> bfs = new LinkedList<>();
        int[][] height = new int[m][n];
        for (int r = 0; r < m; r++)
        {
            for (int c = 0; c < n; c++)
            {
                if(isWater[r][c] == 1)
                {
                    height[r][c] = 0;
                    bfs.offer(new int[]{r, c});
                }
                else
                {
                    height[r][c] = -1;
                }
            }
        }
        int[] DIR = new int[]{0, 1, 0, -1, 0};
        while (!bfs.isEmpty())
        {
            int[] top = bfs.poll();
            int r = top[0], c = top[1];
            for(int i = 0; i < 4; i++)
            {
                int nr = r + DIR[i], nc = c + DIR[i+1];
                if (nr < 0 || nr == m || nc < 0 || nc == n || height[nr][nc] != -1) continue;
                height[nr][nc] = height[r][c] + 1;
                bfs.offer(new int[]{nr, nc});
            }
        }
        return height;
    }
}