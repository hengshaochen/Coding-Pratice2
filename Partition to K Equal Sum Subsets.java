// my ans
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        
        if (k <= 0 || sum % k != 0) {
            return false;
        }
        
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        
        return dfs(0, 0, k, visited, nums, sum / k);
    }
    
    boolean dfs(int startIndex, int cur_sum, int cur_k, boolean[] visited, int[] nums, int target) {
        if (cur_k == 1) {
            return true;
        }
        
        if (cur_sum == target) {
            return dfs(0, 0, cur_k - 1, visited, nums, target);
        }
        
        for (int i = startIndex; i < nums.length; i++) {
            if (visited[i] == false) {
                
                // 剪枝
                if (cur_sum + nums[i] > target) {
                    break;
                }
                visited[i] = true;
                
                if (dfs(startIndex + 1, cur_sum + nums[i], cur_k, visited, nums, target) == true) {
                    return true;
                }
                
                visited[i] = false;
            }
        }
        return false;
    }
}

// faster
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int num:nums)sum += num;
        if(k <= 0 || sum%k != 0)return false;
        int[] visited = new int[nums.length];
        return canPartition(nums, visited, 0, k, 0, 0, sum/k);
    }
    
    public boolean canPartition(int[] nums, int[] visited, int start_index, int k, int cur_sum, int cur_num, int target){

        if(k==1)return true;
        if(cur_sum == target && cur_num>0)return canPartition(nums, visited, 0, k-1, 0, 0, target);
        for(int i = start_index; i<nums.length; i++){
            if(visited[i] == 0){
                visited[i] = 1;
                if(canPartition(nums, visited, i+1, k, cur_sum + nums[i], cur_num++, target))return true;
                
                visited[i] = 0;

                System.out.println(cur_sum);
                for (int j = 0; j < visited.length; j++) {
                    System.out.print(visited[j] + " ");
                }
                System.out.println("");
            }
        }
        return false;
    }
}