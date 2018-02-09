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
    int ans = Integer.MIN_VALUE;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return ans;
    }
    
    int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        // Step1: root pass to subproblem
        int left = dfs(root.left);
        int right = dfs(root.right);
        
        
        // Step2: current root job + return value
        ans = Math.max(ans, left + right);
        
        // Step3:
        return Math.max(left, right) + 1;
        
    }
}