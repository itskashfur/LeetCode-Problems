70. Climbing Stairs
/*
You are climbing a stair case. It takes n steps to reach to the top.
Each time you can either climb 1 or 2 steps. 
In how many distinct ways can you climb to the top?
Dynamic Programming
*/

/*
Thoughts:
Can be recursive, it goes like: climbStairs(n-1) + climbStairs(n - 2)
That has too much of redundant calculation. 
Improve by Memoization
*/
class Solution {
    int[] memo;
    public int climbStairs(int n) {
        if (n <= 1) return 1;
        memo = new int[n];
        return climb(n - 1) + climb(n - 2);
    }
    
    public int climb(int n) {
        if (n <= 1) return 1;
        if (memo[n] > 0) return memo[n];
        memo[n - 1] = climb(n - 1);
        memo[n - 2] = climb(n - 2);
        return memo[n - 1] + memo[n - 2];
    }
}

/*
Thoughts:
DP, consider the last step. It can be reached by 2 steps or 1 steps.
DP[i] represents # ways to reach index i.
DP[i] = DP[i - 1] + DP[i - 2].
Create DP = int [n + 1]
init: DP[0] = 1; DP[1] = 1;
Return DP[n]
*/

class Solution {
    public int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
    }
}

/*
Rolling array
[i] only associates with i-2, i-1.
*/
class Solution {
    public int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] dp = new int[2];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i % 2] = dp[(i - 1) % 2] + dp[(i - 2) % 2];
        }
        
        return dp[n % 2];
    }
}
```
