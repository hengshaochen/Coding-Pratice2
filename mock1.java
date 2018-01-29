Find the second minimum value of this binary tree
    2
   / \
   2  2
   Ans: -1 (not found)
   
   Assumption: at least 2 nodes in the binary tree.
   
   8
   
   
  / \
 10  11
 
 10
 
 one : smallest element in tree, sec: second small element in tree
 ans = 6
 
 one sec MAX
 
 root = 5
 
     one: MAX_VALUE > 5 --> 5
     sec: MAX_VALUE
 ----
 root = 6
     one: 5 < 6 can not update
     sec: MAX_VALUE > 6 --> sec update to 6
 ----
 root = 8
    one: 5 < 8 --> not update
    sec: 6 < 8 --> not update
 ----
 root = 4
    one: 5 > 4 -->5update 4
    sec: 6
   
    5
   6  8
     4 10
     
     -----
     TreeNode
     
     int one = Integer.MAX_VALUE;
     int sec = Integer.MAX_VALUE;
     public int findSecMin(TreeNode root) {
         dfs(root);
         
         if (sec != Integer.MAX_VALUE) {
             return sec;
         }
         // [2,2,2]
         return -1;
     }
     
    void dfs(TreeNode root) {
         // base case
         if (root == null) {
             return;
         }
         
         // root = 5  one or sec find if it can update or not
         if (root.val <= one) {
             one = root.val;
         } else if (root.val <= sec) {
             sec = root.val;
         }
         
         dfs(root.left);
         dfs(root.right);
         
     }
     
     
     
-------------------------------------

Given a number, determine whether the number is power of 2 or not.
0 --> X
1 --> X
2 --> V
4 --> V
8 --> V 2 ^ 3


65 = 2^6
64 --> 2 ^ 6 

O(
2 * 2 * 2 * 2 = target
2*2*2*2*2*2 = 64  64 * 2 = 128 > 68 
2 * 2 * 2 * 2 
65
68
Integer.MAX_VALUE --> long
2^31-1


64 --> 32 --> 16 --> 8 --> 4 --> 2
65 --> 32 --> 16 

65 % 2 != 0 --> not a power of 2

65 

64 --> 32 * 2

0 --> false, 1 --> false 
------

32 --> 16 --> 8 --> 4 --> 2 --> true
14 --> 7  --> false
1  --> false

-8 --> true --> -2 * -2 * -2
this is pow of negative 2 ?

-8 --> 8 
-4 --> 4 --> 2
(-4) = -2 * -2 = 

    public boolean checkPow(int num) {
        if (num == 0 || num == 1) {
            return false;
        } 
        -8, -2 * -2 * -2   , 3 negative
        -4, -2 * -2        , 2 negative
        int ori_num = num;  // -8
        
        num = Math.abs(num);
        int count = 0;
              1      2  --> 3
        8 --> 4  --> 2  --> 1
        
        while (num != 1) {
            if (num % 2 != 0) {
                return false;
            }
            // num % 2 == 0
            num = num / 2;
            count += 1;
        }
        
        if (ori_num < 0) {
            // negative
            if (count % 2 != 0) {
                return true;
            } else {
                reutrn false;
            }
        } else {
            // positive
            return true;
       }
    }

0100
1001

        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                count += 1;
            }
        }

1, 2, 4, 8, 16, ...
0  1  2  3

0001 -> 00....0001 AND 
0010
0100
1000
