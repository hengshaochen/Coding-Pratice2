class Solution {
    public List<List<Integer>> permute(int[] nums) {
        // 這題沒有重複元素, 因此不用visited, 直接用list內建的contains就好
        // 第二題有重複元素，因此contains會有重複值，只好改用boolean[] vistied index來去重
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        
        dfs(new ArrayList<Integer>(), ans, nums);
        
        return ans;
    }
    
    public void dfs(List<Integer> cur, List<List<Integer>> ans, int[] nums) {
        if (cur.size() == nums.length) {
            ans.add(new ArrayList<Integer>(cur));
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (!cur.contains(nums[i])) {
                cur.add(nums[i]);
                
                dfs(cur, ans, nums);
                
                cur.remove(cur.size() - 1);
            }
        }
    }
}

// ----
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        
        Arrays.sort(nums);
        
        boolean[] visited = new boolean[nums.length];
        
        dfs(new ArrayList<Integer>(), ans, nums, visited);
        
        return ans;
    }
    
    public void dfs(List<Integer> cur, List<List<Integer>> ans, int[] nums, boolean[] visited) {
        if (cur.size() == nums.length) {
            ans.add(new ArrayList<Integer>(cur));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                
                if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1] == true) {
                    continue;
                }
                
                cur.add(nums[i]);
                visited[i] = true;
                
                dfs(cur, ans, nums, visited);
                
                cur.remove(cur.size() - 1);
                visited[i] = false;
                
            }
        }
    }
}