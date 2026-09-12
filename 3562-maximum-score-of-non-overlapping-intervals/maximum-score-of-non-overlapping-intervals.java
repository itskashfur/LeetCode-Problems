import java.util.*;

class Solution {
    
    class Interval {
        int start, end, weight, id;
        public Interval(int start, int end, int weight, int id) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.id = id;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervalsList) {
        int n = intervalsList.size();
        Interval[] intervals = new Interval[n];
        
        for (int i = 0; i < n; i++) {
            intervals[i] = new Interval(
                intervalsList.get(i).get(0), 
                intervalsList.get(i).get(1), 
                intervalsList.get(i).get(2), 
                i
            );
        }

        Arrays.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

        long[][] dpScore = new long[n + 1][5];
        int[][][] dpIndices = new int[n + 1][5][];

        for (int i = 0; i <= n; i++) {
            for (int c = 0; c <= 4; c++) {
                dpIndices[i][c] = new int[0];
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int c = 1; c <= 4; c++) {
                
                long skipScore = dpScore[i + 1][c];
                int[] skipIdx = dpIndices[i + 1][c];

                int next = findNext(intervals, intervals[i].end);
                long takeScore = intervals[i].weight + dpScore[next][c - 1];
                int[] takeNextIdx = dpIndices[next][c - 1];
                
                int[] takeIdx = new int[takeNextIdx.length + 1];
                for (int k = 0; k < takeNextIdx.length; k++) {
                    takeIdx[k] = takeNextIdx[k];
                }
                takeIdx[takeNextIdx.length] = intervals[i].id;
                Arrays.sort(takeIdx);

                if (takeScore > skipScore) {
                    dpScore[i][c] = takeScore;
                    dpIndices[i][c] = takeIdx;
                } else if (takeScore < skipScore) {
                    dpScore[i][c] = skipScore;
                    dpIndices[i][c] = skipIdx;
                } else {
                    if (isSmaller(takeIdx, skipIdx)) {
                        dpScore[i][c] = takeScore;
                        dpIndices[i][c] = takeIdx;
                    } else {
                        dpScore[i][c] = skipScore;
                        dpIndices[i][c] = skipIdx;
                    }
                }
            }
        }
        
        return dpIndices[0][4];
    }

    private int findNext(Interval[] arr, int targetEnd) {
        int low = 0, high = arr.length - 1;
        int ans = arr.length;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid].start > targetEnd) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean isSmaller(int[] a, int[] b) {
        for (int i = 0; i < Math.min(a.length, b.length); i++) {
            if (a[i] != b[i]) {
                return a[i] < b[i];
            }
        }
        return a.length < b.length;
    }
}