class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9 * num) return "";  // even all 9s can't reach the sum
        int nines = sum / 9, rest = sum % 9;
        return ("9".repeat(nines) + rest + "0".repeat(num)).substring(0, num);  // 9s, then the rest, then zeros
    }
}