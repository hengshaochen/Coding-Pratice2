// 最優解遍歷一次, 但空間是O(N)
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
    int max_depth = 1;
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        // 回傳當前深度
        map.put(1, new ArrayList<Integer>());
        DFS(root, map);
        
        // 掃treeMap
        for (int i = 1; i <= max_depth; i++) {
            ans.add(map.get(i));
        }
        return ans;
    }
    
    int DFS(TreeNode root, HashMap<Integer, ArrayList<Integer>> map) {
        // base case
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            map.get(1).add(root.val);
            return 1;
        }
        
        // Step1: 呼叫兒子 / 兒子要回傳深度給我
        int left = DFS(root.left, map);
        int right = DFS(root.right, map);
        
        // 刪除左右兒子：到這行時, 左右一定走訪過了 所以可以刪了
        root.left = root.right = null;
        
        // Step2: 這層要做什麼: 把這層加入map中
        int cur_depth = Math.max(left, right) + 1;
        if (!map.containsKey(cur_depth)) {
            map.put(cur_depth, new ArrayList<>());
        }
        map.get(cur_depth).add(root.val);
        
        // Step3: 回傳什麼: 這層的depth
        max_depth = Math.max(max_depth, cur_depth);
        return cur_depth;
        
    }
}

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
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<Integer> cur_leaf = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        
        // 設dummy node, 讓root也能被刪除
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;
        
        while (dummy.left != null || dummy.right != null) {
            cur_leaf.clear();
            helper(dummy, cur_leaf);
            ans.add(new ArrayList<>(cur_leaf));
        }
        return ans;
    }
    // helper回傳值定義為該節點是否能刪除, helper用來刪除leaf node
    boolean helper(TreeNode root, List<Integer> ans) {
        // base case
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            ans.add(root.val);
            return true;
        }
        
        // Step1: 往左右子樹走, 左右子樹應要回傳是否能刪除
        boolean left = helper(root.left, ans);
        boolean right = helper(root.right, ans);
        
        // Step2: 刪除為leaf node的左右兒子
        if (left == true) {
            root.left = null;
        }
        if (right == true) {
            root.right = null;
        }
        
        // Step3: 回傳值一定是false, 因為helper只用來刪除這次的leaf, 如果是leaf最上面base case就會終止
        return false;
    }
}

// TreeMap
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
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        // 回傳當前深度
        map.put(1, new ArrayList<Integer>());
        DFS(root, map);
        
        // 掃treeMap
        for (Integer cur : map.keySet()) {
            ans.add(map.get(cur));
        }
        return ans;
    }
    
    int DFS(TreeNode root, TreeMap<Integer, ArrayList<Integer>> map) {
        // base case
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            map.get(1).add(root.val);
            return 1;
        }
        
        // Step1: 呼叫兒子 / 兒子要回傳深度給我
        int left = DFS(root.left, map);
        int right = DFS(root.right, map);
        
        // 刪除左右兒子：到這行時, 左右一定走訪過了 所以可以刪了
        root.left = root.right = null;
        
        // Step2: 這層要做什麼: 把這層加入map中
        int cur_depth = Math.max(left, right) + 1;
        if (!map.containsKey(cur_depth)) {
            map.put(cur_depth, new ArrayList<>());
        }
        map.get(cur_depth).add(root.val);
        
        // Step3: 回傳什麼: 這層的depth
        return cur_depth;
        
    }
}