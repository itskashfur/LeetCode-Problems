275. H-Index II JAVA : Solution

```
public class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
        	return 0;
        }
        int n = citations.length;
        int start = 0, end = n - 1;
        while (start + 1 < end) {
        	int mid = start + (end - start) / 2;
            int h = n - mid;
            if (citations[mid] < h) start = mid;
            else { // citations[mid] >= h
        		if (mid - 1 >= 0 && citations[mid - 1] <= h) return h;  // verify the prior node: (N-h)
                end = mid;
            }
        }
        if (citations[start] >= n - start) return n - start;
        if (citations[end] >= n - end) return n - end;
        return 0;
    }
}

```
