import java.util.Arrays;

class Solution {
    public int numOfStrings(String[] patterns, String word) {
        // Filter the stream to keep only patterns found in word, then count them
        return (int) Arrays.stream(patterns)
                           .filter(word::contains)
                           .count();
    }
}