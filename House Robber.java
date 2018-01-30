class Solution {
    public int rob(int[] nums) {
        // base case:
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        
        // init:
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        
        return dp[nums.length - 1];
        
        /*
        [10,20,5,40,60]
         10 20 20 60 80
        // dp[i] = rob house from index 0 to i --> MAX PROFIT
        // init: dp[0] = nums[0], dp[1] = nums[1]
        // dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i])
        // ans = dp[nums.length - 1]
        */
        
        
    }
}

// 優化到Space O(1)
class Solution {
    public int rob(int[] nums) {
        // base case:
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        
        // init:
        // pre1就是i的前一個, pre2就是i的前兩個
        int pre1 = Math.max(nums[0], nums[1]);
        int pre2 = nums[0];
        int max = 0;
        for (int i = 2; i < nums.length; i++) {
            max = Math.max(pre1, pre2 + nums[i]);
            pre2 = pre1;
            pre1 = max;
        }
        
        return max;
        
        /*
        [10,20,5,40,60]
         10 20 20 60 80
        // dp[i] = rob house from index 0 to i --> MAX PROFIT
        // init: dp[0] = nums[0], dp[1] = nums[1]
        // dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i])
        // ans = dp[nums.length - 1]
        */
        
        
    }
}