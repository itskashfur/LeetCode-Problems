import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Group reserved seats by row using bitwise masks.
        // Each key is a row number, and the value is a bitmask of reserved seats in that row.
        Map<Integer, Integer> reservedRows = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            // Set the col-th bit to 1 to mark the seat as reserved
            reservedRows.put(row, reservedRows.getOrDefault(row, 0) | (1 << col));
        }

        // Start with the assumption that every completely unreserved row can take 2 groups.
        // Rows with reservations are handled individually next.
        int totalGroups = (n - reservedRows.size()) * 2;

        // Bitmasks corresponding to the required seat numbers for each block:
        // Left group:   Seats 2, 3, 4, 5  -> (1<<2) | (1<<3) | (1<<4) | (1<<5)
        // Right group:  Seats 6, 7, 8, 9  -> (1<<6) | (1<<7) | (1<<8) | (1<<9)
        // Middle group: Seats 4, 5, 6, 7  -> (1<<4) | (1<<5) | (1<<6) | (1<<7)
        int leftMask = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);
        int rightMask = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);
        int middleMask = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);

        // Process only the rows that have at least one reservation
        for (int mask : reservedRows.values()) {
            // Check if all required seats for each block are completely free (bitwise AND is 0)
            boolean leftAvailable = (mask & leftMask) == 0;
            boolean rightAvailable = (mask & rightMask) == 0;
            boolean middleAvailable = (mask & middleMask) == 0;

            // If both left and right blocks are free, we can seat 2 groups in this row
            if (leftAvailable && rightAvailable) {
                totalGroups += 2;
            } 
            // Otherwise, if any single block (left, right, or middle) is free, we can fit 1 group
            else if (leftAvailable || rightAvailable || middleAvailable) {
                totalGroups += 1;
            }
        }

        return totalGroups;
    }
}