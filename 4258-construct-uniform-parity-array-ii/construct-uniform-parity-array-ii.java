class Solution {
    public boolean uniformArray(int[] nums1) {
        int minVal = Integer.MAX_VALUE;
        int minOdd = Integer.MAX_VALUE;

        for (int num : nums1) {
            if (num < minVal) {
                minVal = num;
            }
            if (num % 2 != 0 && num < minOdd) {
                minOdd = num;
            }
        }

        // If smallest element is odd, we can make all elements odd
        if (minVal % 2 != 0) {
            return true;
        }

        // Target 1: Can we make all elements even?
        boolean canBeEven = true;
        for (int num : nums1) {
            if (num % 2 != 0) {
                // To make an odd number even, we need num > minOdd
                if (minOdd == Integer.MAX_VALUE || num <= minOdd) {
                    canBeEven = false;
                    break;
                }
            }
        }

        // Target 2: Can we make all elements odd?
        boolean canBeOdd = true;
        for (int num : nums1) {
            if (num % 2 == 0) {
                // To make an even number odd, we need num > minOdd
                if (minOdd == Integer.MAX_VALUE || num <= minOdd) {
                    canBeOdd = false;
                    break;
                }
            }
        }

        return canBeEven || canBeOdd;
    }
}