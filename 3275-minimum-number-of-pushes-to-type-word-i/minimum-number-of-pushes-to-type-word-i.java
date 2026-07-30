class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int totalPushes = 0;
        
        for (int i = 0; i < n; i++) {
            // (i / 8) + 1 gives the cost (1 push for 1st group, 2 for 2nd, etc.)
            totalPushes += (i / 8) + 1;
        }
        
        return totalPushes;
    }
}