// 最優解空間O(1), 時間O(n)
// 思路：做後序遍歷，維護一個cur_level, 代表當前層數，
// 關鍵在於如果當前層數==當前arraylist ans.size()就新增一層arraylist.
// 然後加入當前node到對應的層數的arraylist即可。注意root==null要回傳-1 
// 因為arraylist初始化的size是0.leaf node是在第0層
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
        
        post_traversal(root, ans);
        return ans;
    }
    
    public int post_traversal(TreeNode root, List<List<Integer>> ans) {
        // base case
        if (root == null) {
            // 要讓leaf node的level是0, 才能剛好等於arraylist.size, 才能new ArrayList<>()
            return -1;
        }
        
        // Step1: pass to child and receive from child
        int left = post_traversal(root.left, ans);
        int right = post_traversal(root.right, ans);
        
        // Step2: job of current node
        int cur_level = Math.max(left, right) + 1;
        // 關鍵！代表這層還沒被開闢過
        if (cur_level == ans.size()) {
            ans.add(new ArrayList<>());
        }
        ans.get(cur_level).add(root.val);
        
        // Step3: return value
        return cur_level;
    }

    /*
          1
         / \
        2   3
       / \     
      4   5   
              arraylist.size() == 0 等於 cur_level == 0 --> ans.add(new ArrayList<>())
              level 0: 4 5 
              -------------
              arraylist.size() == 1 等於 cur_level == 1 --> ans.add(new ArrayList<>())
              level 0: 4 5 
              level 1: 2 
              -------------
              arraylist.size() == 2 等於 cur_level == 2 --> ans.add(new ArrayList<>())
              level 0: 4 5 
              level 1: 2 
              level 2: 1 
              -------------
              level 0: 4 5 3 
              level 1: 2 
              level 2: 1 
    */
}


// 遍歷一次, 但空間是O(N)
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


// 空間O(1) , 但複雜度高
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