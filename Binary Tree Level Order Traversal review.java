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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        
        while (!q.isEmpty()) {
            int qsize = q.size();
            List<Integer> level = new ArrayList<>();
            
            for (int i = 0; i < qsize; i++) {
                TreeNode cur = q.remove();
                level.add(cur.val);
                if (cur.left != null) {
                    q.add(cur.left);
                }
                if (cur.right != null) {
                    q.add(cur.right);
                }
            }
            ans.add(new ArrayList<>(level));
        }
        return ans;
    }
}

// DFS
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        // DFS走preorder才會在level左到右
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        
        dfs(root, 0, ans);
        return ans;
    }
    
    public void dfs(TreeNode root, int depth, List<List<Integer>> ans) {
        if (root == null) {
            return;
        }
        
        if (depth == ans.size()) {
            ans.add(new ArrayList<Integer>());
        }
        
        ans.get(depth).add(root.val);
        
        dfs(root.left, depth + 1, ans);
        dfs(root.right, depth + 1, ans);
    }
}