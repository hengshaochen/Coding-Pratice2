// 簡單的正解 O(n^2)
class Solution {
    public int triangleNumber(int[] nums) {
        // 解法 類似3sum, 關鍵：先排序, 然後把i固定在最大的那個數字
        // 然後 start = 0, end = i - 1開始逛裡面夾擠, 然後如果A[start] + A[end] > A[i], 則ans = ans + (end - start)
        // 因為當前start + end已經比A[i]大, 把start往右移動沒意義, 直接加完ans後 end--
        // 若不夠大start++
        Arrays.sort(nums);
        
        int ans = 0;
        for (int i = nums.length - 1; i >= 2; i--) {
            int start = 0;
            int end = i - 1;
            while (start < end) {
                if (nums[start] + nums[end] > nums[i]) {
                    ans = ans + (end - start);
                    end--;
                } else {
                    start++;
                }
            }
        }
        
        return ans;
    }
}

// 暴力法 O(n^3)
class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] > nums[k]) {
                        ans += 1;
                    }
                }
            }
        }
        return ans;
    }
}


// 會有重複的子問題, 有BUG, 解決辦法是要加入一個Set, 用String編碼該3bits index是否有走訪過 太麻煩
class Solution {
    int ans = 0;
    public int triangleNumber(int[] nums) {
        // 思路：類似3sum, 先排序, [2,2,3,4] , 然後for ( i = 0 ~ < length - 2)
        // i = 0
        // 2 2 3 4, 
        // i s   e --> 2 + 2 not > 4 切子問題, s ++ or e-- 遞回
        // 然後把每個子問題加起來
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            helper(nums[i], i + 1, nums.length - 1, nums);
        }
    }
    public void helper(int fixedValue, int startIndex, int endIndex, int[] nums) {
        // base case
        if (startIndex >= endIndex) {
            return;
        }
        
        while (startIndex < endIndex) {
            if (fixedValue + nums[startIndex] <= nums[endIndex]) {
                // 這次不能形成合法三角形, 繼續切子問題
                helper(fixedValue, startIndex++, endIndex, nums);
                helper(fixedValue, startIndex, endIndex--, nums);
            } else {
            
            }
        }
    }
}