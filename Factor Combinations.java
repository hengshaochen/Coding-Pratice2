// 視頻版
class Solution {
    public List<List<Integer>> getFactors(int n) {
        // corner case1: 當n = 1 ? []
        // corner case2: 當n == odd? []
        List<List<Integer>> ans = new ArrayList<>();
        helper(ans, new ArrayList<Integer>(), n, 2);
        return ans; 
    }
    
    public void helper(List<List<Integer>> ans, List<Integer> cur, int n, int start) {
        // base case
        if (n == 1) {
            // 必須存在 > 1個因數才加入，例如37就不能加入
            if (cur.size() > 1) {
                ans.add(new ArrayList<>(cur));
            }
            return;
        }
        
        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                cur.add(i);
                helper(ans, cur, n / i, i);
                cur.remove(cur.size() - 1);
            }
        }
    }
    
    /*
          12
    6 [2]   
    3 [2,2]      1[2,6] 
     1 [2,2,3] 不會嘗試[2,2,2] 因為 3 % 2 != 0
     */
}

// 優化版本：
class Solution {
    public List<List<Integer>> getFactors(int n) {
        // corner case1: 當n = 1 ? []
        // corner case2: 當n == odd? []
        List<List<Integer>> ans = new ArrayList<>();
        helper(ans, new ArrayList<Integer>(), n, 2);
        return ans; 
    }
    
    public void helper(List<List<Integer>> ans, List<Integer> cur, int n, int start) {
        // base case
        if (n == 1) {
            // 必須存在 > 1個因數才加入，例如37就不能加入
            // // input:8 , 只有8 就不加入
            if (cur.size() > 1) {
                ans.add(new ArrayList<>(cur));
            }
            
            return;
        }
        
        for (int i = start; i <= n; i++) {
            // 優化
            // 例如當remain = 12, i要跑2 ~ 3就可以，因為下一層譬如果跑i = 4, remain是12/4=3,
            // i == 4開始跑 但4 並沒有 <= remain 等於這層for不會跑，所以提前剪枝！
            if (i > n / i) {
                break;
            }
            if (n % i == 0) {
                cur.add(i);
                helper(ans, cur, n / i, i);
                cur.remove(cur.size() - 1);
            }    
        }
        
        // 優化
        // // 前面i>remain / i 剪枝，友軍被誤傷，因此要在這邊加入，當i取到remain是要加入到答案中的
        cur.add(n);
        helper(ans, cur, 1, n);
        cur.remove(cur.size() - 1);
    }
    
    /*
          12
    6 [2]   
    3 [2,2]      1[2,6] 
     1 [2,2,3] 不會嘗試[2,2,2] 因為 3 % 2 != 0
     */
} 

// 清楚版
class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> ans = new ArrayList<>();
        if (n == 1) {
            return ans;
        }
        
        DFS(2, n, 1, new ArrayList<Integer>(), ans);
        return ans;
    }
    
    void DFS(int start, int n, int cur_product, List<Integer> cur, List<List<Integer>> ans) {
        // base case
        if (cur_product > n) {
            return;
        }
        if (cur_product == n) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        
        for (int i = start; i < n; i++) {
            // 剪枝
            if (cur_product * i > n) {
                break;
            }
            
            // 要是n的因數才有必要選
            if (n % i == 0) {
                cur.add(i);

                DFS(i, n, cur_product * i , cur, ans);
                
                cur.remove(cur.size() - 1);
            }
            
        }
    }
}

// version2:
class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> ans = new ArrayList<>();
        if (n == 1) {
            return ans;
        }
        
        DFS(2, n, 1, new ArrayList<Integer>(), ans);
        return ans;
    }
    void DFS(int start, int n, int cur_product, List<Integer> cur, List<List<Integer>> ans) {
        // base case
        if (cur_product > n) {
            return;
        }
        if (cur_product == n) {
            if (cur.size() > 1) {
                ans.add(new ArrayList<>(cur));
            }
            return;
        }
        
        for (int i = start; i <= n / cur_product; i++) {
            // 要是n的因數才有必要選
            if (n % i == 0) {
                cur.add(i);
                cur_product = cur_product * i;

                DFS(i, n, cur_product, cur, ans);
                cur.remove(cur.size() - 1);
                cur_product = cur_product / i;

                // 剪枝
                if (cur_product * i >= n) {
                    //System.out.println(cur_product * i);
                    break;
                }
            }
            
        }
    }
}