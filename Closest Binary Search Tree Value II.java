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
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        /*
        corner case: 
        input bst is empty? --> return empty list
        target is bigger than int
        k is zero? return empty list
        */
        List<Integer> ans = new ArrayList<>();
        if (root == null || k == 0) {
            return ans;
        }
        
        Comparator<TreeNode> cmp = new Comparator<TreeNode>() {
            public int compare(TreeNode e1, TreeNode e2) {
                // e1 = pq.peek(), e2 = 挑戰者. 如果 e1 - target < e2 - target 代表不用改變pq
                // 這是max heap
                if (Math.abs(e1.val - target) > Math.abs(e2.val - target)) {
                    return -1;
                }
                return 1;
                //return Math.abs(e1.val - target) < Math.abs(e2.val - target) ? 1 : -1;
            }
        };
        
        PriorityQueue<TreeNode> pq = new PriorityQueue<TreeNode>(k, cmp);
        
        helper(root, pq, target, k, cmp);
        
        int pqSize = pq.size();
        for (int i = 0; i < pqSize; i++) {
            ans.add(pq.remove().val);
        }
        
        return ans;
    }
    
    public void helper(TreeNode root, PriorityQueue<TreeNode> pq, double target, int k, Comparator<TreeNode> cmp) {
        if (root == null) {
            return;
        }
        
        helper(root.left, pq, target, k, cmp);
        
        if (pq.size() < k) {
            pq.add(root);
        } else {
            /*
            3 target = 8  --> 5
            6 target = 8  --> 2     2 - 5 > 0
            */
            if (cmp.compare(pq.peek(), root) < 0) {
                pq.remove();
                pq.add(root);
            }
        }
        
        helper(root.right, pq, target, k, cmp);
    }
}