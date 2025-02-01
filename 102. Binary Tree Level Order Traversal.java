102.Binary Tree Level Order Traverse Problem : JAVA Solution
/*
Given a binary tree, return the level order traversal of its nodes' values. 
(ie, from left to right, level by level).

Example
Given binary tree {3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7
 

return its level order traversal as:

[
  [3],
  [9,20],
  [15,7]
]
Challenge
Challenge 1: Using only 1 queue to implement it.

Challenge 2: Use DFS algorithm to do it.

Tags Expand 
Queue Binary Tree Breadth First Search Binary Tree Traversal Uber LinkedIn Facebook

*/

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> rst = new ArrayList<>();
	    if (root == null) return rst;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            rst.add(list);
        }
	    return rst;
    }
}

//Method2: DFS
//Recursive with dfs: use a level to track. Add curr into corresponding level; each level > rst.size(), add a new [].
//Note: rst is a ArrayList<ArrayList<Integer>>, where each level is a arraylist; that is why we can add [] into rst to represent a level.

public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        dfs(root, 0, result);
        return result;    
    }
    
    public void dfs(TreeNode root, int level, List<List<Integer>> rst) {
        if (root == null) return;
        if (level >= rst.size()) rst.add(new ArrayList<>());
        rst.get(level).add(root.val);
        dfs(root.left, level + 1, rst);
        dfs(root.right, level + 1, rst);
    }
}


```
