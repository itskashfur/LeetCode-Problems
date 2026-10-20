class Solution:
    def sortableIntegers(self, nums: list[int]) -> int:
        n = len(nums)

        desc = [0, *accumulate(a > b for a, b in pairwise(nums))]
        # desc[i]: number of descents nums[j] > nums[j + 1] with j < i

        premax = list(accumulate(nums, max))
        # premax[i]: max of nums[0..i]

        sufmin = list(accumulate(reversed(nums), min))[::-1]
        # sufmin[i]: min of nums[i..n-1]

        cut = [True] + [premax[m - 1] <= sufmin[m] for m in range(1, n)]
        # cut[m]: if nums[:m] holds exactly the m smallest values

        def rotatable(l, r):
            # nums[l..r] is a rotated sorted run: at most one cyclic descent

            return desc[r] - desc[l] + (nums[r] > nums[l]) <= 1

        def sortable(k):
            # every block starts at a valid cut and can be rotated into order
            
            return all(cut[l] and rotatable(l, l + k - 1) for l in range(0, n, k))

        divisors = [k for k in range(1, n + 1) if n % k == 0]
        return sum(filter(sortable, divisors))