tags: Array, Hash Table
time: O(n)
space: O(n)

HashMap<value, index>
Relatively Violent and Simple Method
Relatively Violent and Simple Method: Find a value, store an index.

If a result is matched in the HashMap, then return the stored index in the HashMap.

O(n) space and time.

Sort Array, Two Pointers Method
Sort array, two pointers method:

Use two pointers to search by incrementing from front and decrementing from back. Sorting takes O(nlogn) time.

Note that you need to use additional space to retain the original array, to find the index later. (You cannot use HashMap here because it uses value as the key, but the value might be repeated.)

O(n) space, O(nlogn) time.

  


```
/**
LeetCode: 0-based answer
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
*/
/*
Thoughts:
    Using a HashMap, O(n) space and O(n) time.
    Thinking process:
    Push everything into a HashMap.
    Check if one element exist in the HashMap, if so save it. Meanwhile, save the other one.
    Trick: after adding into the HashMap, we are looking for the 2nd index first. 
        Always check (target - current) from the HashMap. 
        If exist, that means index0 has already been pushed into the HashMap and current value is at index1.
    (key, value) = (numbers[i], i)
    Note: return index+1 because this is not 0-based.
*/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] rst = new int[2];
        if (nums == null || nums.length <= 1) return rst;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                rst[0] = map.get(target - nums[i]);
                rst[1] = i;
                break;
            } 
            map.put(nums[i], i);
        }
        return rst;
    }
}

/*
Given an array of integers, find two numbers such that they add up to a specific target number.
The function twoSum should return indices of the two numbers such that they add up to the target, 
where index1 must be less than index2. 
Please note that your returned answers (both index1 and index2) are NOT zero-based.
Example
numbers=[2, 7, 11, 15], target=9
return [1, 2]
Note
You may assume that each input would have exactly one solution
Challenge
Either of the following solutions are acceptable:
O(n) Space, O(nlogn) Time
O(n) Space, O(n) Time
Tags Expand 
Two Pointers Sort Hash Table Array Airbnb Facebook
*/

//2. O(n) Space O(nlogn) time
/*
    Feels like binary search when looking at O(nlogn)
    1. sort
    2. loop all number
    3. binary search on rest
*/
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return null;
        }
        int[] original = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            original[i] = numbers[i];
        }
        
        Arrays.sort(numbers);
        int start = 0; 
        int end = numbers.length - 1;
        int num1 = -1;
        int num2 = -1;
        while (start != end) {
            int sum = numbers[start] + numbers[end];
            if (sum == target) {
                num1 = numbers[start];
                num2 = numbers[end];
                break;
            }else if (sum < target) {
                start++;
            } else {
                end--;
            }
        }

        //Find the num1,num2 in original array and record the index
        int[] rst = new int[2];
        rst[0] = -1;
        rst[1] = -1;
        for (int i = 0; i < original.length; i++) {
            if (original[i] == num1 || original[i] == num2) {
                if (rst[0] == -1) {
                    rst[0] = i + 1;
                } else {
                    rst[1] = i + 1;
                    break;
                }
            }
        }
        return rst;
    }
}









```
