class Solution {
    public int searchInsert(int[] nums, int target) {
        // 找第一個 >= target的元素
        // 例如[1,3,5,6] target = 2, 要回傳index 1, 3是第一個>= target的元素
        //     [1,3,5,6] target = 5, 要回傳index 2  5第一個 >= target的元素
        //     [1,3,5,6] target = 7, 要回傳index 4, 因為找不到 >= target的元素，回傳end + 1
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
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