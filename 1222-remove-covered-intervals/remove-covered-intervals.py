class Solution:
    def removeCoveredIntervals(self, intervals: list[list[int]]) -> int:
        # WHAT: Sort intervals: ascending by start point (x[0]), descending by end point (-x[1]).
        # WHY: This ensures that for identical start points, the longest interval is processed first to catch and cover shorter ones.
        intervals.sort(key=lambda x: (x[0], -x[1]))

        remaining_count = 0
        max_end = 0

        # WHAT: Linearly scan the sorted list of intervals.
        for _, end in intervals:
            # WHAT: If the current end point is less than or equal to max_end, it is covered.
            # WHY: The sorting order ensures its start point is validly contained within a previously evaluated larger interval.
            if end <= max_end:
                continue  # Covered interval, skip counting it
            else:
                # This interval extends past the previous ones, making it unique and valid
                remaining_count += 1
                max_end = end  # Stretch our coverage boundary forward

        return remaining_count