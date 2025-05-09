215. Kth Largest Element In An Array Problem : JAVA Solution

```
/**
Find the kth largest element in an unsorted array. 
Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.
*/

/*
sort O(nlogn). Better O(n)
use a min-heap with size k, so min item will always be at top to be removed O(logk)
Overall runtime O(nlogk)
*/
class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return -1;

        PriorityQueue<Integer> queue = new PriorityQueue<>(); // min-heap
        
        for (int i = 0; i < nums.length; i++) { 
            if (i < k || nums[i] > queue.peek()) queue.offer(nums[i]);
            if (queue.size() > k) queue.poll();
        }
        
        return queue.poll();
    }
}


/*
- Quick sort/ partition
- Partition to return the `low` index, which should match targetIndex.
*/
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return partition(nums, 0, n - 1, n - k);
    }
    
    private int partition (int[] nums, int start, int end, int targetIndex) {
        // define low/high
        int pivot = end;
        int low = start, high = end, pivotNum = nums[pivot];
        
        // move pointer and swap
        while (low < high) {
            while (low < high && nums[low] < pivotNum) low++; // break when nums[low] >= pivotNum
            while (low < high && nums[high] >= pivotNum) high--; // break when nums[high] < pivotNum
            swap(nums, low, high);
        }
        swap(nums, low, pivot);

        // compare if low == targetIndex; or recursively partition to find targetIndex
        if (low == targetIndex) return nums[low];
        else if (low < targetIndex) return partition(nums, low + 1, end, targetIndex);
        return partition(nums, start, low - 1, targetIndex);
    }
    
    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
```
