class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // suffix[i] = length of word2 suffix matched in word1[i...n-1]
        int[] suffix = new int[n + 1];
        int j = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                j--;
            }
            suffix[i] = m - 1 - j;
        }

        int[] result = new int[m];
        j = 0;
        boolean modified = false;

        for (int i = 0; i < n && j < m; i++) {
            boolean isMatch = (word1.charAt(i) == word2.charAt(j));

            if (isMatch) {
                result[j++] = i;
            } else if (!modified && suffix[i + 1] >= m - 1 - j) {
                // Change current character to match word2[j]
                result[j++] = i;
                modified = true;
            }
        }

        return (j == m) ? result : new int[0];
    }
}