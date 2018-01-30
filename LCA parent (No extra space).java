/**
 * Definition of ParentTreeNode:
 * 
 * class ParentTreeNode {
 *     public ParentTreeNode parent, left, right;
 * }
 */


public class Solution {
    /*
     * @param root: The root of the tree
     * @param A: node in the tree
     * @param B: node in the tree
     * @return: The lowest common ancestor of A and B
     */
    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root, ParentTreeNode A, ParentTreeNode B) {
        
        int depthA = getDepth(A);
        int depthB = getDepth(B);
        
        if (depthA > depthB) {
            move(A, depthA - depthB);
        } else {
            move(B, depthB - depthA);
        }
        
        while (A != B) {
            A = A.parent;
            B = B.parent;
        }
        // 同一層同時往上移動, 移動到相同的就是LCA
        // ParentTreeNode ans = A;
        return A;
    }
    
    int getDepth(ParentTreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int count = 0;
        while (root != null) {
            count += 1;
            root = root.parent;
        }
        
        return count;
    }
    
    void move(ParentTreeNode root, int diff) {
        while (diff > 0) {
            root = root.parent;
            diff -= 1;
        }
    }
}