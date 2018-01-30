class Solution {
    /*
     * @param k : description of k
     * @param nums : array of nums
     * @return: description of return
     */
    public int kthLargestElement(int k, int[] nums) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }
    
    private int quickSelect(int[] nums, int start, int end, int k) {
        int i = start, j = end;
        int pivot = nums[(start + end) / 2];
        
        while (i <= j) {
            while (i <= j && nums[i] > pivot) {
                i++;
            }
            while (i <= j && nums[j] < pivot) {
                j--;
            }
            
            if (i <= j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                
                i++;
                j--;
            }
        }
        
        // 往左
        if (start + k - 1 <= j) {
            return quickSelect(nums, start, j, k);
        }


        // 往右
        if (start + k - 1 >= i) {
            return quickSelect(nums, i, end, k - (i - start));
        }
        
        // 剛好在j i之間
        return nums[j + 1];
    }
};

///// K smallest
public class Solution {
    /*
     * @param nums: an integer unsorted array
     * @param k: an integer from 1 to n
     * @return: the kth largest element
     */
    public int kthSmallest(int k, int[] nums) {
        if (nums == null || nums.length == 0) { return -1; }
        //k = nums.length - k + 1;
        return quickSelect(nums, 0, nums.length - 1, nums.length - k + 1);
    }
    private int quickSelect(int[] nums, int start, int end, int k) {
        int i = start, j = end;
        int pivot = nums[(start + end) / 2];
        
        while (i <= j) {
            while (i <= j && nums[i] > pivot) {
                i++;
            }
            while (i <= j && nums[j] < pivot) {
                j--;
            }
            
            if (i <= j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                
                i++;
                j--;
            }
        }
        
        // 往左
        if (start + k - 1 <= j) {
            return quickSelect(nums, start, j, k);
        }


        // 往右
        if (start + k - 1 >= i) {
            return quickSelect(nums, i, end, k - (i - start));
        }
        
        // 剛好在j i之間
        return nums[j + 1];
    }
}