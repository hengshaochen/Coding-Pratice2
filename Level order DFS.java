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
    public List<List<Integer>> LevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        
        dfs(root, ans, 0);
        
        return ans;
    }
    
    public void dfs(TreeNode root, List<List<Integer>> ans, int depth) {
        // base case
        if (root == null) {
            return;
        }
        
        if (ans.size() == depth) {
            ans.add(new ArrayList<Integer>());
        }
        ans.get(depth).add(root.val);
        
        dfs(root.left, ans, depth + 1);
        dfs(root.right, ans, depth + 1);
        
    }
}


// ZIGG
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        
        dfs(root, ans, 0, false);
        
        return ans;
    }
    
    public void dfs(TreeNode root, List<List<Integer>> ans, int depth, boolean inverse) {
        // base case
        if (root == null) {
            return;
        }
        
        if (ans.size() == depth) {
            ans.add(new ArrayList<Integer>());
        }
        if (inverse == false) {
            ans.get(depth).add(root.val);
        } else {
            ans.get(depth).add(0, root.val);
        }
        
        inverse = inverse == false ? true : false;
        
        dfs(root.left, ans, depth + 1, inverse);
        dfs(root.right, ans, depth + 1, inverse);
        
    }
}