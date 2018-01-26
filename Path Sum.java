/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        return helper(root, 0, sum);
    }
    public boolean helper(TreeNode root, int cur, int sum) {
        // base case
        if (root == null) {
            return false;
        }
        
        // 先加root.val再和sum比較
        cur = cur + root.val;
        if (root.left == null && root.right == null) {
            if (cur == sum) {
                return true;
            } else {
                return false;
            }
        }
        
        return helper(root.left, cur, sum) || helper(root.right, cur, sum);
    }
}