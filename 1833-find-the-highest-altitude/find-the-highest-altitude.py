class Solution:
    def largestAltitude(self, gain: list[int]) -> int:
        # WHAT: Set the initial maximum altitude and running current altitude tracking variables to 0.
        # WHY: The trip begins at an altitude reference point of 0, which serves as our baseline.
        max_altitude = 0
        current_altitude = 0
        
        # WHAT: Walk through each point's change value within the gain list.
        for g in gain:
            # Update our running altitude calculation with the step's climb or descent value
            current_altitude += g
            
            # WHAT: Compare the current altitude to our peak recorded value, saving the higher result.
            # WHY: This records the highest overall peak achieved at any stage of the journey.
            if current_altitude > max_altitude:
                max_altitude = current_altitude
                
        return max_altitude