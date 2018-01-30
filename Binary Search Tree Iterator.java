/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {

    // 思路：BST走中序會是小到大, next()也要小到大
    // 因此我們可以用這種思路, 用Stack模擬中序
    Stack<TreeNode> s;
    TreeNode cur;
    public BSTIterator(TreeNode root) {
        s = new Stack<>();
        cur = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if (!s.isEmpty() || cur != null) {
            return true;
        }
        return false;
    }

    /** @return the next smallest number */
    public int next() {
        while (cur != null) {
            s.push(cur);
            cur = cur.left;
        }
        cur = s.pop();
        int val = cur.val;
        cur = cur.right;
        return val;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */