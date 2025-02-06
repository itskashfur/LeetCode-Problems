131.Palindrome Partitioning Problem : JAVA Solution
```
/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]
*/

/*
Thoughts:
DFS to return all possible palindromes:
partition at index x in [0, s.length() )
Optimize by calculating boolean[][] isPalin ahead of time

// 99.82%
*/
class Solution {
    boolean[][] isPalin;
    int n;
    public List<List<String>> partition(String s) {
        List<List<String>> rst = new ArrayList<>();
        if (s == null || s.length() == 0) return rst;
        n = s.length();
        calcPalin2(s);
        dfs(rst, new ArrayList<>(), s, 0);
        return rst;
    }
    
    private void dfs(List<List<String>> rst, List<String> list, String s, int index) {
        if (index == n) {
            rst.add(new ArrayList<>(list));
            return;
        }
        for (int i = index + 1; i <= n; i++) {
            if (isPalin[index][i - 1]) { // 也需要查看自身是不是 palindrome: s.charAt(x). isPalin[i][j] 是 inclusive的
                list.add(s.substring(index, i));
                dfs(rst, list, s, i);
                list.remove(list.size() - 1);
            }
        }
    }
    // Optiona, faster. Kinda DP, isPalin[i][j] shows palindrome status for s[i,j] inclusivly
    private void calcPalin(String s) {
        char[] arr = s.toCharArray();
        isPalin = new boolean[n][n];
        int i, j;
        
        for (int mid = 0; mid < n; mid++) {
            // odd: single char in center
            i = j = mid;
            while (i >= 0 && j < n && arr[i] == arr[j]) {
                isPalin[i--][j++] = true;
            }
            
            // even: always even number of palindrome characters
            i = mid;
            j = mid + 1;
            while (i >= 0 && j < n && arr[i] == arr[j]) {
                isPalin[i--][j++] = true;
            }
        }
    }
    
    // Option2: a bit slower, 95%.
    private void calcPalin2(String s) {
        char[] arr = s.toCharArray();
        isPalin = new boolean[n][n];

        // 1) init single digit, len=1
        for (int i = 0; i < n; i++) {
            isPalin[i][i] = true;
        }

        // 2) init len=2
        for (int i = 0; i < n - 1; i++) {
            isPalin[i][i + 1] = arr[i] == arr[i + 1];
        }
        
        // 3) finish len=3~n
        for (int i = n-1; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                isPalin[i][j] = arr[i] == arr[j] && isPalin[i+1][j-1];
            }
        }
    }

/*
    // Manual Check Palindrome
    public boolean isPalindrome(String s){
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
*/
}


```
