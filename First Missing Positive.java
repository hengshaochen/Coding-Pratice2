// CORRECT VERSION
class Solution {
    public int firstMissingPositive(int[] nums) {
        // 題目要找從 1 ~ nums.length 缺的東西, 例如[3,4,-1,1]就是找1~4缺了哪個值 答案是缺了2
        // 解法要用hashing的思路，把數值mapping到應該放的位置
        // 把[3,4,-1,1] 變成 [1,-1,3,4] 有點像排序
        for (int i = 0; i < nums.length; i++) {
            // Hashing的思想：nums[nums[i] - 1] != nums[i]
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                swap(nums, nums[i] - 1, i);
            }
        }
        
        // 掃[1,-1,3,4] 看哪個不等於index + 1，該值就是缺少的。
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        // 符合corner case當nums.length == 0要回傳1
        return nums.length + 1;
        
    }
    
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}


// DEBUG VERSION
class Solution {
    public int firstMissingPositive(int[] nums) {
        
        // 把[3,4,-1,1] 變成 [1,-1,3,4] 有點像排序
        for (int i = 0; i < nums.length; i++) {
            // Hashing的思想：nums[nums[i] - 1] != nums[i]
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                System.out.println("Y");
                swap(nums, nums[i] - 1, i);
            }
            
            for (int j = 0; j < nums.length; j++) {
                System.out.print(nums[j] + " ");
            }
            System.out.println("");
                
        }
        
        // 掃[1,-1,3,4] 看哪個不等於index + 1
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
        
    }
    
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}