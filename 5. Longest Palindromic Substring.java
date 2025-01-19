Tags: String, DP
Time: O(n^2) Space: O(n^2)

Problem: Given a string, find the longest palindromic substring.

Related: Longest Palindromic Subsequence, Palindrome Partitioning II

O(n^2) is not too hard to think of. How about O(n)?

Method 1: DP of Interval
Very similar to 216. Longest Palindromic Subsequence, but this problem requires the substring (i+1, j-1) to be palindromic as well.

Similarly: Process i = n-1, from the end so [i + 1, j] is always ready to consume.

Use boolean dp[i][j] to mark the range (i, j) as palindromic or not.

When calculating dp[i][j], isPalin[i+1][j-1] should already be computed.

Time: O(n^2) dp.

Space: O(n^2)

String, Palindrome Definition
Split from the middle, traverse i: split from n different points: each split checks if it can extend as a palindromic center.

Two cases of palindromes: odd and even.

Worst case: when the entire string consists of the same character, time complexity becomes: 1 + 2 + 3 + ... + n = O(n^2).

O(n)
/*
Given a string S, find the longest palindromic substring in S. 
You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.

Example
Given the string = "abcdzdcab", return "cdzdc".

Challenge
O(n2) time is acceptable. Can you do it in O(n) time.

Hide Company Tags Amazon Microsoft Bloomberg
Hide Tags String
Hide Similar Problems (H) Shortest Palindrome (E) Palindrome Permutation


*/
// Method1: DP of interval
public class Solution {
    public String longestPalindrome(String s) {
    	if (s == null || s.length() <= 1) return s;
        int n = s.length();
    	boolean dp[][] = new boolean[n][n];
    	String str = String.valueOf(s.charAt(n - 1));
    	for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = true;
    		for (int j = i + 1; j < n; j++) {
    			if (s.charAt(i) == s.charAt(j) && (i + 1 == j || dp[i + 1][j - 1])) {
    				dp[i][j] = true;
    				str = str.length() <= (j - i + 1) ? s.substring(i, j + 1) : str;
    			}
    		}
    	}
    	return str;
    }
}


// O(n^2)
public class Solution {
	private int start, maxLen;

    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
        	return s;
        }
        for (int i = 0; i < s.length() - 1; i++) {
        	findMaxLen(s, i, i); // odd middle point i
			findMaxLen(s, i, i + 1); // even s(i) == s(i+1)
        }
        return s.substring(start, start + maxLen);
    }

    public void findMaxLen(String s, int i, int j) {
    	while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
    		i--;
    		j++;
    	}
		//Note: i and j has moved apart 1 extra step after while loop
		if (maxLen < j - i - 1) {
			maxLen = j - i - 1;
			start = i + 1;
		}
    }
}



```
