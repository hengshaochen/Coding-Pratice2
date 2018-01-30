class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] ans = {-1, -1};
        if (nums == null || nums.length == 0) {
            return ans;
        }
        
        ans[0] = binarySearch(nums, target, 0);
        ans[1] = binarySearch(nums, target, 1);
        return ans;
    }
    
    public int binarySearch(int[] nums, int target, int leftOrRight) {
        int start = 0;
        int end = nums.length - 1;
        
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            
            if (nums[mid] == target) {
                if (leftOrRight == 0) {
                    // 盡量靠左
                    end = mid;
                } else {
                    // 盡量靠右
                    start = mid;
                }
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (leftOrRight == 0) {
            if (nums[start] == target) {
                return start;
            } else if (nums[end] == target) {
                return end;
            }
        } else {
            if (nums[end] == target) {
                return end;
            } else if (nums[start] == target) {
                return start;
            }
        }
        return -1;
    }
}