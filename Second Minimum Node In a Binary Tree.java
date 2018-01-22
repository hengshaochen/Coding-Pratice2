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
        int one = Integer.MAX_VALUE;
        int sec = Integer.MAX_VALUE;
    public int findSecondMinimumValue(TreeNode root) {
        dfs(root);
        if (sec != Integer.MAX_VALUE) {
            return sec;
        }
        return -1;
    }
    void dfs(TreeNode root) {
        // base case:
        if (root == null) {
            return;
        }
        
        // Step2: 這層做的事情
        if (root.val <= one) {
            one = root.val;
        } else if (root.val <= sec) {
            sec = root.val;
        }
        
        // Step1: 傳給兒子
        if (root.left != null) {
            dfs(root.left);
            dfs(root.right);
        }
        
        // System.out.println(root.val + "one: " + one + " sec: " + sec);
        
    }
}