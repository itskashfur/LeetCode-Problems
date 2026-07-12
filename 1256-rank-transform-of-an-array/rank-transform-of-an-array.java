import java.util.*;

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        // Line 1: Make a copy of the original array.
        // WHY: We need to sort the elements to find their ranks, but we must preserve the original order to generate the final output.
        int[] sortedArr = arr.clone();
        
        // Line 2: Sort the cloned array in ascending order (smallest to largest).
        // WHY: Sorting groups identical items together and orders them so we can assign ranks sequentially.
        Arrays.sort(sortedArr);
        
        // Line 3: Create a Hash Map to link each unique number to its rank.
        // WHY: A Map provides O(1) instantaneous lookups when replacing values later.
        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;
        
        // Line 4: Loop through the sorted array to assign ranks.
        for (int num : sortedArr) {
            // WHAT: Only add the number to the map if it hasn't been added yet.
            // WHY: If the number is already in the map, it means we found a duplicate, and duplicates must share the exact same rank.
            if (!rankMap.containsKey(num)) {
                rankMap.put(num, rank);
                rank++; // Move to the next rank for the next unique number
            }
        }
        
        // Line 5: Re-use our sortedArr array variables to store the final ranks.
        // WHY: Overwriting an existing array saves memory.
        for (int i = 0; i < arr.length; i++) {
            sortedArr[i] = rankMap.get(arr[i]);
        }
        
        // Line 6: Return the transformed array containing the ranks.
        return sortedArr;
    }
}