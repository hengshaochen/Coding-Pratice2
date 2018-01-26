// 暴力法 O(n^2) 窮舉所有subarray
class Solution {
    public int maxSubArray(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int cur_sum = 0;
            for (int j = i; j < nums.length; j++) {
                cur_sum = cur_sum + nums[j];
                ans = Math.max(ans, cur_sum);
            }
        }
        return ans;
    }
}

// prefix O(n)
class Solution {
    public int maxSubArray(int[] nums) {
        // 用一個pre保存累加數值, 用min保存當前最小值
        int minPrefix = 0;
        int max = Integer.MIN_VALUE;
        int prefix = 0;
        for (int i = 0; i < nums.length; i++) {
            prefix = prefix + nums[i];
            max = Math.max(max, prefix - minPrefix);
            minPrefix = Math.min(minPrefix, prefix);
        }
        return max;
        
        /*
        pre = -2
        max = -2
        min = -2
        ---
        pre = -2
        max = Max(max, pre - min) = -1 - (-2) = 1
        min = -2
        
        [-2,1,-3]
         -2 -1 -4
          i
        */ 
    }
}