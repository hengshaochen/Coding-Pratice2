// 暴力法 O(n^3)
class Solution {
    public int maxProduct(int[] nums) {
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j <= nums.length - i; j++) {
                int[] subArr = Arrays.copyOfRange(nums, i, i + j);
                int cur_mul = 1;
                for (int k = 0; k < subArr.length; k++) {
                    cur_mul *= subArr[k];
                }
                ans = Math.max(cur_mul, ans);
            }
        }
        return ans;
    }
}

// 最佳解 O(n)
class Solution {
    public int maxProduct(int[] nums) {
        /*
        // 解法2: O(n)
        // 用max, min紀錄當前最小和最大, 還有一個ans紀錄答案, 然後掃一遍input array, 每次取MAX(max * num, min * num, num)
        // 為何紀錄最小？ cur_min: -8 , cur_max = 6, cur_array_num = -5 , 這時答案會更新為 min * num = -8 * -5 = 40
        // 為何紀錄最大？ cur_min: -3,  cur_max = 5, cur_array_num =  4 , 這時答案更新為  max * num = 5 * 4 = 20
        // 為何要取num？ cur_min:  -5, cur_max = 0, cur_array_num =  3 , 這時答案更新為num
        
        [2,3,-2,-5]
        init:
        min, max, ans = 2
        
        i = 1:
        min = 2
        max = 6
        ans = 6
        
        i = 2:
        min = -12
        max = 6
        ans = 6
            
        i = 3:
        min = -12
        max = -12 * -5 = 60
        ans = 60
        */
        
        // corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int min = nums[0];
        int max = nums[0];
        int ans = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            // 要加一個temp, 因為我們更新完Max, 要更新Min, 會用到“原本的”MAX, 而不是更新過的max
            int temp = max;
            max = Math.max(   Math.max(max * nums[i], min * nums[i]), nums[i]  );
            
            // 注意 要把他替換成temp
            min = Math.min(   Math.min(temp * nums[i], min * nums[i]), nums[i]  );
            
            ans = Math.max(   Math.max(max, min), ans);
        }
        return ans;
    }
}