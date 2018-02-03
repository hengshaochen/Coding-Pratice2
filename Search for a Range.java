// 最優解，一個BS，程式碼簡潔，但是還是搜兩次。注意特判斷
class Solution {
    public int[] searchRange(int[] nums, int target) {
        // 先找左端點(FirstGreaterOrEqual) 找第一個nums[index] > target的地方
        // 接著找右端點，也用FirstGreaterOrEqual, 但是這次改成找target + 1, 最後找到的index 再 - 1即可
        int[] ans = {-1, -1};
        if (nums == null || nums.length == 0) {
            return ans;
        }
        
        int start = FirstGreaterOrEqual(nums, target);
        // 特判斷 尾端: arr = [2,2] target = 3,這是firstGreater會找到index = 2, 但其實這也不是我們要的target
        // 特判斷 前端: arr = [1] target = 0, 這時用firstGreater會找到index = 0, 但其實這不是我們要的target
        // 注意：要先特判斷尾端再特判斷前端，因為這時start已經outOfBound
        if (start == nums.length || nums[start] != target) {
            return ans;
        }
        
        ans[0] = start;
        ans[1] = FirstGreaterOrEqual(nums, target + 1) - 1;
        return ans;
    }
    
    public int FirstGreaterOrEqual(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) /2;
            if (nums[mid] == target) {
                end = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (nums[start] >= target) {
            return start;
        } else if (nums[end] >= target) {
            return end;
        } else {
            return end + 1;
        }
    }
}

// 兩個BS
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