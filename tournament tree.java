// "static void main" must be defined in a public class.
public class Main {
    class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(8);
        root.right.right = new TreeNode(7);
        
        System.out.println(findSecondMIN_INT(root));
        System.out.println(findSecMin_in_Tournament_Tree(root, root).val);
    }
    
    // 型態是回傳INT
    public int findSecondMIN_INT(TreeNode root) {
        // 只有兩種case, 一種是leaf node, 一種是有兩個兒子
        
        // base case: leaf node
        if (root.left == null && root.right == null) {
            return Integer.MAX_VALUE;
        }
        
        // 思路：每次走和root相等的那個分支，然後把該分支的min和“不走的那個分支的root"取min
        if (root.left.val == root.val) {
            // root和左兒子相等，往左找sec min, 和右子樹比
            return Math.min(findSecondMIN_INT(root.left), root.right.val);
        } else {
            // root.right.val == root.val
            return Math.min(findSecondMIN_INT(root.right), root.left.val);
        }
    }

    // 型態是回傳NODE
    public TreeNode findSecMin_in_Tournament_Tree(TreeNode cur, TreeNode root) {
        // base case
        /*
        if (root == null) {
            return null;
        }
        */
        // leaf node
        if (cur.left == null || cur.right == null) {
            if (cur.val != root.val) {
                return cur;
            } else {
                // 當找到為root的值後，回傳最大值，不然會找到第一小的，而不是第二小。
                // root == cur
                return new TreeNode(Integer.MAX_VALUE);
            }
        }
        
        
        // 思路：root一定是最小的，用一個variable維護當前最小值
        // 每次看左右子樹，左右子樹一定有值，因為一定要有兩個人才能比賽
        // 一定是complete binary tree
        // 每次走和root相等的那枝分枝，然後另外一枝分枝看能不能更新min
        
        if (cur.left.val == cur.val) {
            TreeNode l = findSecMin_in_Tournament_Tree(cur.left, root);
            if (l.val < cur.right.val) {
                return l;
            } else {
                return cur.right;
            }
        } else {
            TreeNode r = findSecMin_in_Tournament_Tree(cur.right, root);
            if (r.val < cur.left.val) {
                return r;
            } else {
                return cur.left;
            }
        }
    }
    
}