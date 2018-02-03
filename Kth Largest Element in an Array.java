class Solution {
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }
    
    private int quickSelect(int[] nums, int start, int end, int k) {
        int i = start;
        int j = end;
        
        int pivot = nums[(start + end) / 2];
        while (i <= j) {
            while (i <= j && nums[i] > pivot) {
                // i找比pivot小的
                i++;
            }
            while (i <= j && nums[j] < pivot) {
                // j找比pivot大的
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
        /*
        [5,3,10,12,13]
         i         j 
        [13,3,10,12,5]
            i    j 
        [13,12,10,3,5]
            j     i
        start = 0, end = 4, j = 1, i = 3, k = 2
        0 + 2 - 1 = 1 <= 1 --> 往左
        --------
        quickSelect(nums, 0, 1, 2)
        [13,12]
         i  j   pivot = 13
        [13,12]
         ij
        [13,12]
      j      i
        j = -1, start = 0, end = 1
        0 + 2 - 1 = 1 >= 1 往右
        quickSelect(nums, 1, 1, 2 - (1 - 0)) = quickSelect(nums, 1, 1, 1)
        ----
        start = 1, end = 1, i = 1, j = 1
        [13,12]
          j    i
        */
        // 往左
        System.out.println("i: " + i + " j: " + j + " start: " + start + " end: " + end + " k:" + k);
        if (start + k - 1 <= j) {
            return quickSelect(nums, start, j, k);
        }
        // 往右
        if (start + k - 1 >= i) {
            System.out.println("t");
            return quickSelect(nums, i, end, k - (i - start));
        }
        // 剛好是start, en之間的
        return nums[j + 1];
    }
}

// Smallest
public class Solution {
    /*
     * @param k: An integer
     * @param nums: An integer array
     * @return: kth smallest element
     */
    public int kthSmallest(int k, int[] nums) {
        // [1,2,3,4,5] 要從找第2大的程式中找第2小, 就是原本的第len - k + 1大
        // 5 - 2 + 1 = 第4大
        return quickSelect(nums, 0, nums.length - 1, nums.length - k + 1);
    }
    
    private int quickSelect(int[] nums, int start, int end, int k) {
        int i = start;
        int j = end;
        
        int pivot = nums[(start + end) / 2];
        while (i <= j) {
            while (i <= j && nums[i] > pivot) {
                // i找比pivot小的
                i++;
            }
            while (i <= j && nums[j] < pivot) {
                // j找比pivot大的
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
        /*
        [5,3,10,12,13]
         i         j 
        [13,3,10,12,5]
            i    j 
        [13,12,10,3,5]
            j     i
        start = 0, end = 4, j = 1, i = 3, k = 2
        0 + 2 - 1 = 1 <= 1 --> 往左
        --------
        quickSelect(nums, 0, 1, 2)
        [13,12]
         i  j   pivot = 13
        [13,12]
         ij
        [13,12]
      j      i
        j = -1, start = 0, end = 1
        0 + 2 - 1 = 1 >= 1 往右
        quickSelect(nums, 1, 1, 2 - (1 - 0)) = quickSelect(nums, 1, 1, 1)
        ----
        start = 1, end = 1, i = 1, j = 1
        [13,12]
          j    i
        */
        // 往左
        System.out.println("i: " + i + " j: " + j + " start: " + start + " end: " + end + " k:" + k);
        if (start + k - 1 <= j) {
            return quickSelect(nums, start, j, k);
        }
        // 往右
        if (start + k - 1 >= i) {
            System.out.println("t");
            return quickSelect(nums, i, end, k - (i - start));
        }
        // 剛好是start, en之間的
        return nums[j + 1];
    }
}