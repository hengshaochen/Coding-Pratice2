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
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean insertFront = false;
        
        while (!q.isEmpty()) {
            int qsize = q.size();
            // 注意這邊要用LinkedList實作, 才能達到O(1) insertInFront的效果
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < qsize; i++) {
                TreeNode cur = q.remove();
                if (cur.left != null) {
                    q.add(cur.left);
                }
                if (cur.right != null) {
                    q.add(cur.right);
                }
                
                if (insertFront == false) {
                    list.add(cur.val);
                } else {
                    list.add(0, cur.val);
                }
            }
            ans.add(list);
            insertFront = insertFront == false ? true : false;
        }
        return ans;
    }
}