class Solution:
    def maxProduct(self, n: int) -> int:
        # Convert n to digits, sort them in descending order, and multiply the top 2
        digits = sorted([int(d) for d in str(n)], reverse=True)
        return digits[0] * digits[1]