/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        q.add(root);
        
        while (!q.isEmpty()) {
            TreeNode cur = q.remove();
            if (cur == null) {
                sb.append("null,");
            } else {
                sb.append(cur.val + ",");
                q.add(cur.left);
                q.add(cur.right);
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        
        // System.out.println(sb);
        
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    // String to Tree
    public TreeNode deserialize(String data) {
        String[] d = data.split(",");
        
        // 特殊情況, 當傳來的data的root為null時, 直接return null, 不然Integer.parseInt(d[0])會錯
        if (d[0].compareTo("null") == 0) {
            return null;
        }
        
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(d[0]));
        q.add(root);
        
        // i為指標指向現在要處理的String中的節點
        int i = 1;
        while (!q.isEmpty()) {
            TreeNode cur = q.remove();
            
            if (cur == null) {
                // null的節點不用接任何左右兒子
                continue;
            }
            
            if (d[i].compareTo("null") != 0) {
                // 把左兒子接上
                cur.left = new TreeNode(Integer.parseInt(d[i]));
                q.add(cur.left);
            }
            i = i + 1;
            
            if (d[i].compareTo("null") != 0) {
                // 把右兒子街上
                cur.right = new TreeNode(Integer.parseInt(d[i]));
                q.add(cur.right);
            }
            i = i + 1;
        }
        
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));