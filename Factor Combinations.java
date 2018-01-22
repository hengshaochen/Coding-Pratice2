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