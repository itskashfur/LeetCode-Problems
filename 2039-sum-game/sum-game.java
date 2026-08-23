class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;

        int leftSum = 0, leftQ = 0;
        for (int i = 0; i < half; i++) {
            if (num.charAt(i) == '?') {
                leftQ++;
            } else {
                leftSum += num.charAt(i) - '0';
            }
        }

        int rightSum = 0, rightQ = 0;
        for (int i = half; i < n; i++) {
            if (num.charAt(i) == '?') {
                rightQ++;
            } else {
                rightSum += num.charAt(i) - '0';
            }
        }

        if ((leftQ + rightQ) % 2 != 0) {
            return true;
        }
        return (leftSum - rightSum) * 2 + 9 * (leftQ - rightQ) != 0;
    }
}
    