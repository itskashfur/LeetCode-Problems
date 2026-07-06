import java.util.Arrays;

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // WHAT: Sort intervals: ascending by start point; descending by end point if starts match.
        // WHY: If starts match, the wider interval comes first, covering any subsequent narrower intervals.
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            } else {
                return Integer.compare(b[1], a[1]);
            }
        });

        int remainingCount = 0;
        int maxEnd = 0;

        // WHAT: Traverse each interval and compare its end point with the maximum end point seen so far.
        for (int[] interval : intervals) {
            int currentEnd = interval[1];

            // WHAT: If currentEnd fits inside maxEnd, it means this interval is completely covered.
            // WHY: Due to sorting, we already know its start point is >= the start point of the interval that formed maxEnd.
            if (currentEnd <= maxEnd) {
                continue; // Covered, drop it
            } else {
                // Not covered, so it stays as a unique remaining interval
                remainingCount++;
                maxEnd = currentEnd; // Update the boundary tracker
            }
        }

        return remainingCount;
    }
}