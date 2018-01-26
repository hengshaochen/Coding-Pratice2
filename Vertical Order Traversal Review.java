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
    public List<List<Integer>> verticalOrder(TreeNode root) {
        // 注意要用BFS, 因為同一個#的必須上到下. 不能用DFS (DFS會錯的test case: [3,9,8,4,0,1,7,null,null,null,2,5])
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        
        HashMap<Integer, ArrayList<Integer>> ans_map = new HashMap<>();
        HashMap<TreeNode, Integer> map = new HashMap<>();
        
        int min = 0;
        int max = 0;
        
        Queue<TreeNode> q = new LinkedList<>();
        
        q.add(root);
        map.put(root, 0);
        
        while (!q.isEmpty()) {
            int qsize = q.size();
            for (int i = 0; i < qsize; i++) {
                TreeNode cur = q.remove();
                int cur_vertical = map.get(cur);
                
                min = Math.min(min, cur_vertical);
                max = Math.max(max, cur_vertical);
                
                if (!ans_map.containsKey(cur_vertical)) {
                    ans_map.put(cur_vertical, new ArrayList<Integer>());
                }
                ans_map.get(cur_vertical).add(cur.val);
                
                if (cur.left != null) {
                    q.add(cur.left);
                    map.put(cur.left, cur_vertical - 1);
                }
                if (cur.right != null) {
                    q.add(cur.right);
                    map.put(cur.right, cur_vertical + 1);
                }
            }
        }
        
        for (int i = min; i <= max; i++) {
            ans.add(ans_map.get(i));
        }
        return ans;
    }
}