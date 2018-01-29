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
    int rootval;
    public int closestValue(TreeNode root, double target) {
        // 思路：用一個min_node存當前最接近target的node val, 然後之後看是否能更新
        return helper(root, target, root.val);
        
    }
    
    public int helper(TreeNode root, double target, int min_node) {
        if (root == null) {
            return min_node;
        }
        
        // 如果現在的node和target距離比之前的min_node和target距離近的話 --> 更新min_node為當前
        if (Math.abs(min_node - target) > Math.abs(root.val - target)) {
            min_node = root.val;
        }
        
        if (target < root.val) {
            min_node = helper(root.left, target, min_node);
        } else {
            min_node = helper(root.right, target, min_node);
        }
        
        return min_node;
    }
}