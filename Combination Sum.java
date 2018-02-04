// Combination Sum
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // corner1 : candidates is empty, return empty ans
        // corner2 : is that all candidates and number is positive?  ex: target = 0,  [-2,2] [-2,-2,2,2] ? YES ALL POSITIVE
        List<List<Integer>> ans = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return ans;
        }
        
        Arrays.sort(candidates);
        
        dfs(new ArrayList<Integer>(), 0, 0, candidates, target, ans);
        
        return ans;
    }
    
    void dfs(List<Integer> cur, int cur_sum, int startIdx, int[] candidates, int target, List<List<Integer>> ans) {
        // base case:
        if (cur_sum == target) {
            ans.add(new ArrayList<Integer>(cur));
        }
        if (cur_sum > target) {
            return;
        }
        
        for (int i = startIdx; i < candidates.length; i++) {
            // 剪枝
            
            cur.add(candidates[i]);
            dfs(cur, cur_sum + candidates[i], i, candidates, target, ans);
            cur.remove(cur.size() - 1);
        }
    }
}


// Combination Sum II
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return ans;
        }
        /*
        [1,1,2,5,6,7,10]
        [1]
        [1,1]
        [1,1,2]  [1,1,2,7]可以剪枝
        [1,1,2,5] back
        */
        
        Arrays.sort(candidates);
        
        dfs(0, new ArrayList<Integer>(), 0, candidates, target, ans);
        
        return ans;
    }
    
    public void dfs(int startIdx, List<Integer> cur, int cur_sum, int[] candidates, int target, List<List<Integer>> ans) {
        // base case
        if (cur_sum > target) {
            return;
        }
        if (cur_sum == target) {
            ans.add(new ArrayList<>(cur));
        }
        
        for (int i = startIdx; i < candidates.length; i++) {
            // remove duplicate
            // 1,2,5 , 1',2,5 <-- remove
            if (i > 0 && candidates[i] == candidates[i - 1] && i > startIdx) {
                continue;
            }
            
            // 剪枝，[1,1,2,5] 已經 > target, 不用再嘗試[1,1,2,7]...
            if (cur_sum + candidates[i] > target) {
                break;
            }
            
            cur.add(candidates[i]);
            dfs(i + 1, cur, cur_sum + candidates[i], candidates, target, ans);
            cur.remove(cur.size() - 1);
        }
    }
}