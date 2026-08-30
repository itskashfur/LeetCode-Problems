class Solution:
    def minimumDeletions(self, nums: list[int]) -> int:
        n = len(nums)
        if n <= 2:
            return n

        # Find indices of min and max elements
        min_idx = nums.index(min(nums))
        max_idx = nums.index(max(nums))

        # Determine leftmost and rightmost targets
        left = min(min_idx, max_idx)
        right = max(min_idx, max_idx)

        # 1. Delete both from front
        cost_front = right + 1

        # 2. Delete both from back
        cost_back = n - left

        # 3. Delete one from front and one from back
        cost_both = (left + 1) + (n - right)

        return min(cost_front, cost_back, cost_both)