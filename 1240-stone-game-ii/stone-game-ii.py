from functools import cache

class Solution:
    def stoneGameII(self, piles: list[int]) -> int:
        n = len(piles)
        
        # Precompute suffix sums
        # suffixSum[i] stores the sum of all elements from piles[i] to the end
        suffixSum = [0] * n
        suffixSum[-1] = piles[-1]
        for i in range(n - 2, -1, -1):
            suffixSum[i] = suffixSum[i + 1] + piles[i]

        @cache
        def helper(i: int, M: int) -> int:
            # Base Case: If the remaining piles (i to n) can all be taken at once
            if i + 2 * M >= n:
                return suffixSum[i]
            
            max_stones = 0
            # Try taking X piles where 1 <= X <= 2 * M
            for X in range(1, 2 * M + 1):
                next_M = max(M, X)
                # Opponent plays optimally from position i + X with parameter next_M
                opponent_stones = helper(i + X, next_M)
                
                # Current player gets the rest of the total available stones
                current_stones = suffixSum[i] - opponent_stones
                max_stones = max(max_stones, current_stones)
                
            return max_stones

        return helper(0, 1)