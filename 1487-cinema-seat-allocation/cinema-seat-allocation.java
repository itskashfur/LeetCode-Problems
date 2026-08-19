import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        
        Map<Integer, Integer> reservedRows = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            
            reservedRows.put(row, reservedRows.getOrDefault(row, 0) | (1 << col));
        }

        
        int totalGroups = (n - reservedRows.size()) * 2;

       
        int leftMask = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);
        int rightMask = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);
        int middleMask = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);

        
        for (int mask : reservedRows.values()) {
            
            boolean leftAvailable = (mask & leftMask) == 0;
            boolean rightAvailable = (mask & rightMask) == 0;
            boolean middleAvailable = (mask & middleMask) == 0;

            
            if (leftAvailable && rightAvailable) {
                totalGroups += 2;
            } 
           
            else if (leftAvailable || rightAvailable || middleAvailable) {
                totalGroups += 1;
            }
        }

        return totalGroups;
    }
}