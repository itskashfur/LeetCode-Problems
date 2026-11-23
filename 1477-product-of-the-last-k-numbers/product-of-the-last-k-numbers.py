class ProductOfNumbers:

    def __init__(self):
        self.prefix = [1]  # prefix[i]: the product of the first i numbers after the last zero

    def add(self, num: int) -> None:
        if num == 0:
            self.prefix = [1]  # a window that reaches this zero is 0, so forget everything before it
        else:
            self.prefix.append(self.prefix[-1] * num)

    def getProduct(self, k: int) -> int:
        if k >= len(self.prefix):  # the window reaches back to a zero
            return 0
        return self.prefix[-1] // self.prefix[-1 - k]


# Your ProductOfNumbers object will be instantiated and called as such:
# obj = ProductOfNumbers()
# obj.add(num)
# param_2 = obj.getProduct(k)