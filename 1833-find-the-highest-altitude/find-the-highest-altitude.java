class Solution {
    public int largestAltitude(int[] gain) {
        // WHAT: Initialize the maximum altitude reached and the running current altitude to 0.
        // WHY: The biker starts at point 0 with an altitude of 0. We track both the peak height and our current position.
        int maxAltitude = 0;
        int currentAltitude = 0;
        
        // WHAT: Loop through each net change value in the gain array sequentially.
        // WHY: Each element represents the relative vertical shift between consecutive points.
        for (int g : gain) {
            // Update the running total altitude by adding the current net change
            currentAltitude += g;
            
            // WHAT: Update maxAltitude if the newly calculated altitude is higher than the previous peak.
            // WHY: This ensures we capture the absolute highest coordinate reached at any point during the trip.
            maxAltitude = Math.max(maxAltitude, currentAltitude);
        }
        
        // WHAT: Return the highest tracked altitude point.
        return maxAltitude;
    }
}