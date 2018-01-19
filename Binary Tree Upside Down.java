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
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        // base case
        if (root == null || root.left == null) {
            return root;
        }
        
        // Step1:
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        
        // Step2:
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        
        // Step3:
        return newRoot;
    }
}