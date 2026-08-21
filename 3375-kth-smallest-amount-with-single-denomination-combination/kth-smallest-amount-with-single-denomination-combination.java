import java.util.ArrayList;
import java.util.List;

class Solution {
    private record Subset(long lcm, int sign) {}

    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        List<Subset> subsets = new ArrayList<>();

        for (int mask = 1; mask < (1 << n); mask++) {
            long currentLcm = 1;
            int size = 0;
            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 1) {
                    currentLcm = lcm(currentLcm, coins[i]);
                    size++;
                }
            }
            int sign = (size % 2 == 1) ? 1 : -1;
            subsets.add(new Subset(currentLcm, sign));
        }

        long minCoin = coins[0];
        for (int coin : coins) {
            minCoin = Math.min(minCoin, coin);
        }

        long left = 1;
        long right = minCoin * k;
        long ans = right;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (countValid(mid, subsets) >= k) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    private long countValid(long x, List<Subset> subsets) {
        long total = 0;
        for (Subset subset : subsets) {
            total += subset.sign * (x / subset.lcm);
        }
        return total;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}