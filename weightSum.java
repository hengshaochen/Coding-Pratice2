// package whatever; // don't place package name!

import java.io.*;

class MyCode {
    class TreeNode {
        TreeNode left, right;
        int val;
        TreeNode(int val) {
            this.val = val;
        }
    }
  public static void main (String[] args) {
    new MyCode();
  }
  
    public MyCode() {
      TreeNode root = new TreeNode(3);
      root.left = new TreeNode(9);
      root.right = new TreeNode(5);
      root.left.left = new TreeNode(6);
      root.left.left.right=  new TreeNode(7);
      root.right.right = new TreeNode(4);
      
      System.out.println(weightSum(root, 1));
    }
  
  public int weightSum(TreeNode root, int level) {
    // base case
    if (root == null) {
      return 0;
    }
    
    // Step1:傳給兒子 / 兒子要回傳什麼給你
    int left = weightSum(root.left, level + 1);
    int right = weightSum(root.right, level + 1);
    
    // Step2:這層要做的事
    int sum = left + right + (root.val * level);
    
    // Step3:回傳
    return sum;
    
  }
}
