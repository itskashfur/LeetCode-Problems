/*
Implement int sqrt(int x).

Compute and return the square root of x, 
where x is guaranteed to be a non-negative integer.

Since the return type is an integer, 
the decimal digits are truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2
Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.
*/

/*
Thoughts:
Binary Search a candidate between [0~x]
*/
class Solution {
    public int mySqrt(int x) {
        long start = 0;
        long end = x;
        while (start + 1 < end) {
            long mid = (start + end) >> 1;
            if (mid * mid < x) {
                start = mid;
            } else if (mid * mid > x) {
                end = mid;
            } else {
                return (int)mid;
            }
        }
        if (end * end <= x) {
            return (int)end;
        } else {
            return (int)start;
        }
    }
}

/*
Thoughts:
We need to assume x is positive. There must be m such that m*m = x. Find m using binary search.
Note, naturally m will be in [0, x]
*/
class Solution {
    public int mySqrt(int x) {
        /*
        // 之后的逻辑都包含
        if (x <= 0) {
            return 0;
        }*/
        long start = 0;
        long end = x;
        while(start <= end) {
            long mid = (start + end) / 2; // Or: long mid = start + (end - start) / 2;
            
            if (mid * mid < x) {
                start = mid + 1;
            } else if (mid * mid > x){
                end = mid - 1;
            } else {
                return (int)mid;
            }
        }
        //When start > end, while loop ends. That means, end must be the largest possible integer that end^2 is closest to x.
        return (int)end;
    }
}

/*
What if sqrt(double)?
*/
class Solution {
    public double mySqrt(double x) {
        double start = 0;
        double end = x;
        double eps = 1e-12;

        while (end - start > eps) {
            double mid = start + (end - start) / 2;
            if (mid * mid < x) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (end * end <= x) {
            return end;
        } else {
            return start;
        }
    }
}

```
