class Solution {
    public boolean stoneGameIX(int[] stones) {
        // Frequency array to store counts of remainders 0, 1, and 2 when divided by 3
        int[] count = new int[3];
        for (int stone : stones) {
            count[stone % 3]++;
        }

        // If count[0] is EVEN, 0s don't change the turn parity of 1s and 2s.
        // Alice wins if she can pick a starting choice (1 or 2) that has at least 1 stone available,
        // and both remainder 1 and 2 counts are greater than 0.
        if (count[0] % 2 == 0) {
            return count[1] > 0 && count[2] > 0;
        }

        // If count[0] is ODD, the single effective 0 flips the turn order.
        // Alice wins if the difference between count[1] and count[2] is greater than 2,
        // allowing her to force Bob into a lose condition despite the turn flip.
        return Math.abs(count[1] - count[2]) > 2;
    }
}