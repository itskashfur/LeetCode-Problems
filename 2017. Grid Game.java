/*
Idea
Please note that robot 1 and robot 2 can only move down once.
There are total n possible paths of Robot 1. Each possible path of Robot 1, Robot 2 can only get one of following total points:
topSum: If robot 2 moves on the top row.
bottomSum: If robot 2 moves on the bottom row.
It means, total points that Robot 2 can get = max(topSum, bottomSum).
Finally, we need to choose one path among n paths of Robot 1 so that it can minimize total points of Robot 2.
*/

class Solution 
{
    public long gridGame(int[][] grid) 
    {
        long topSum = Arrays.stream(grid[0]).asLongStream().sum(), bottomSum = 0;
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < grid[0].length; ++i)
        {
            topSum -= grid[0][i];
            ans = Math.min(ans, Math.max(topSum, bottomSum));
            bottomSum += grid[1][i];
        }
        return ans;
    }
}
