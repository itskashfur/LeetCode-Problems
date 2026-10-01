class Solution:
    def isValid(self, str: str) -> bool:
        if len(str) % 2:
            return False

        S = list(str)
        i = 0

        for c in S:
            if (ord(c) & 3) != 1:
                S[i] = c
                i += 1
            else:
                if i == 0:
                    return False
                i -= 1
                if (((ord(c) - ord(S[i])) + 1) >> 1) != 1:
                    return False

        return i == 0