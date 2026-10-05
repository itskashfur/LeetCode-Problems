class Solution {
    public int scoreOfParentheses(String S) {
        return F(S, 0, S.length());
    }

    private int F(String S, int i, int j) {
        int ans = 0, bal = 0;

        // Split string into primitives
        for (int k = i; k < j; ++k) {
            bal += S.charAt(k) == '(' ? 1 : -1;
            if (bal == 0) {
                if (k - i == 1) {
                    ans++;
                } else {
                    ans += 2 * F(S, i + 1, k);
                }
                // Move start pointer for the next primitive
                i = k + 1; 
            }
        }

        return ans;
    }
}