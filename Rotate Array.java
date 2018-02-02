// 開額外空間的解法
class Solution {
    public void rotate(int[] nums, int k) {
        // corner case: k > nums.length
        k %= nums.length;
        
        int[] ans = new int[nums.length];
        for (int i = 0; i < k; i++) {
            ans[i] = nums[nums.length - k + i];
        }
        for (int i = k; i < nums.length; i++) {
            ans[i] = nums[i - k];
        }
        
        for (int i = 0; i < nums.length; i++) {
            nums[i] = ans[i];
        }
    }
}

// 三步翻轉法
class Solution {
    public void rotate(int[] nums, int k) {
        // corner case: k > nums.length
        k %= nums.length;
        //  0 1 2 3 4 5 6                  7 6 5   4 3 2 1 
        // [1,2,3,4,5,6,7]  n = 7, k = 3, [5,6,7] [1,2,3,4]
        // 0 ~ nums.length - k - 1轉, nums.length - k ~ nums.lengt - 1  轉 --> 整個再轉
        // index 0 ~ 3轉, 4 ~ 6轉 
        rev(0, nums.length - k - 1, nums);
        rev(nums.length - k, nums.length - 1, nums);
        rev(0, nums.length - 1, nums);
    }
    
    public void rev(int i, int j, int[] nums) {
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }
}