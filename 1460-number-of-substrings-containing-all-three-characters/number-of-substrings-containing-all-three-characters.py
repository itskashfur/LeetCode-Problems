class Solution:
    def numberOfSubstrings(self, s: str) -> int:
        res = 0
        # Tracks the last seen index of 'a', 'b', and 'c' respectively
        # Initialized to -1 because we haven't seen them yet
        last_seen = [-1, -1, -1]
        
        for i, char in enumerate(s):
            # Map 'a' -> 0, 'b' -> 1, 'c' -> 2 using their ASCII values
            last_seen[ord(char) - ord('a')] = i
            
            # The number of valid substrings ending at index 'i' is determined 
            # by the minimum index among the last seen positions of 'a', 'b', and 'c'.
            # Any substring starting from index 0 up to min(last_seen) will be valid.
            res += min(last_seen) + 1
            
        return res