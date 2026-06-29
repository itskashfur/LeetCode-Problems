#Hello Ji Hello
class Solution:
    def numOfStrings(self, patterns: list[str], word: str) -> int:
        count = 0
        
        # Loop through each individual pattern string in the list
        for pattern in patterns:
            # Check if the pattern exists as a substring within 'word'
            if pattern in word:
                count += 1 # Increment the counter if a match is found
                
        # Return the total number of matching patterns
        return count