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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        
        // definition: find all ans that path sum equals to sum 
        helper(root, new ArrayList<Integer>(), ans, 0, sum);
        return ans;
    }
    
    public void helper(TreeNode root, List<Integer> cur_path, List<List<Integer>> ans, int cur_sum, int sum) {
        // base case
        if (root == null) {
            return;
        }
        
        // The job of current node
        cur_sum = cur_sum + root.val;
        cur_path.add(root.val);
        
        // leaf node determine
        if (root.left == null && root.right == null) {
            if (cur_sum == sum) {
                ans.add(new ArrayList<Integer>(cur_path));
            }
            // 回朔
            cur_path.remove(cur_path.size() - 1);
            return;
        }
        
        // divide to subproblem
        helper(root.left, cur_path, ans, cur_sum, sum);
        helper(root.right, cur_path, ans, cur_sum, sum);
        
        // 回朔
        cur_path.remove(cur_path.size() - 1);
    }
}