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
        if (root == null || root.left == null) {
            // 因為upsideDown的定義是把左兒子當成新的root, 沒有左兒子沒辦法upsideDown
            return root;
        }
        
        // Step1:
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        
        // Step2:
        // root.left相當於是newRoot, root是還沒翻轉前的
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        
        // Step3:
        return newRoot;
        
    }
}