import java.util.*;

class Solution {
    public String smallestNumber(String num, long t) {
        // Step 1: Factorize t
        int c2 = 0, c3 = 0, c5 = 0, c7 = 0;
        long tempT = t;
        
        while (tempT % 2 == 0) { tempT /= 2; c2++; }
        while (tempT % 3 == 0) { tempT /= 3; c3++; }
        while (tempT % 5 == 0) { tempT /= 5; c5++; }
        while (tempT % 7 == 0) { tempT /= 7; c7++; }

        if (tempT > 1) return "-1"; // Invalid prime factor

        int n = num.length();
        int[] numDigits = new int[n];
        for (int i = 0; i < n; i++) numDigits[i] = num.charAt(i) - '0';

        // Longest zero-free prefix
        int maxPrefix = n;
        for (int i = 0; i < n; i++) {
            if (numDigits[i] == 0) {
                maxPrefix = i;
                break;
            }
        }

        // Cumulative prime factors
        int[] pref2 = new int[n + 1];
        int[] pref3 = new int[n + 1];
        int[] pref5 = new int[n + 1];
        int[] pref7 = new int[n + 1];

        for (int i = 0; i < maxPrefix; i++) {
            int d = numDigits[i];
            pref2[i + 1] = pref2[i] + getFactorCount(d, 2);
            pref3[i + 1] = pref3[i] + getFactorCount(d, 3);
            pref5[i + 1] = pref5[i] + getFactorCount(d, 5);
            pref7[i + 1] = pref7[i] + getFactorCount(d, 7);
        }

        // Try matching prefix of length L (from maxPrefix down to 0)
        for (int L = maxPrefix; L >= 0; L--) {
            int req2 = Math.max(0, c2 - pref2[L]);
            int req3 = Math.max(0, c3 - pref3[L]);
            int req5 = Math.max(0, c5 - pref5[L]);
            int req7 = Math.max(0, c7 - pref7[L]);

            // If L == n, we are checking if the original string num is already valid
            if (L == n) {
                if (getMinDigitsNeeded(req2, req3, req5, req7) == 0) {
                    return num;
                }
                continue;
            }

            int startDigit = numDigits[L] + 1;

            for (int d = startDigit; d <= 9; d++) {
                int r2 = Math.max(0, req2 - getFactorCount(d, 2));
                int r3 = Math.max(0, req3 - getFactorCount(d, 3));
                int r5 = Math.max(0, req5 - getFactorCount(d, 5));
                int r7 = Math.max(0, req7 - getFactorCount(d, 7));

                int minLenNeeded = getMinDigitsNeeded(r2, r3, r5, r7);
                int remLen = n - 1 - L;

                if (minLenNeeded <= remLen) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < L; i++) sb.append(numDigits[i]);
                    sb.append(d);
                    fillTrailing(sb, remLen, r2, r3, r5, r7);
                    return sb.toString();
                }
            }
        }

        // If length n is not enough, construct smallest valid number of length >= n + 1
        int minLenNeeded = getMinDigitsNeeded(c2, c3, c5, c7);
        int totalLen = Math.max(n + 1, minLenNeeded);

        StringBuilder sb = new StringBuilder();
        fillTrailing(sb, totalLen, c2, c3, c5, c7);
        return sb.toString();
    }

    private static int getFactorCount(int num, int p) {
        int cnt = 0;
        while (num > 0 && num % p == 0) {
            cnt++;
            num /= p;
        }
        return cnt;
    }

    private static int getMinDigitsNeeded(int r2, int r3, int r5, int r7) {
        int count8 = r2 / 3;
        int rem2 = r2 % 3;
        int count9 = r3 / 2;
        int rem3 = r3 % 2;

        int count6 = 0, count4 = 0, count2 = 0, count3 = 0;

        if (rem2 == 2) {
            count4 = 1;
        } else if (rem2 == 1) {
            if (rem3 == 1) {
                count6 = 1;
                rem3 = 0;
            } else {
                count2 = 1;
            }
        }

        if (rem3 == 1) count3 = 1;

        return count8 + count9 + count6 + count4 + count2 + count3 + r5 + r7;
    }

    private static void fillTrailing(StringBuilder sb, int remLen, int r2, int r3, int r5, int r7) {
        for (int pos = 0; pos < remLen; pos++) {
            int neededForRest = remLen - 1 - pos;
            for (int d = 1; d <= 9; d++) {
                int next2 = Math.max(0, r2 - getFactorCount(d, 2));
                int next3 = Math.max(0, r3 - getFactorCount(d, 3));
                int next5 = Math.max(0, r5 - getFactorCount(d, 5));
                int next7 = Math.max(0, r7 - getFactorCount(d, 7));

                if (getMinDigitsNeeded(next2, next3, next5, next7) <= neededForRest) {
                    sb.append(d);
                    r2 = next2; r3 = next3; r5 = next5; r7 = next7;
                    break;
                }
            }
        }
    }
}