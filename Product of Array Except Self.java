public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length, right = 1;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; ++i) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        for (int i = n - 1; i >= 0; --i) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }
}

// Space: O(n)
public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int[] fwd = new int[n], bwd = new int[n];
        fwd[0] = 1; bwd[n - 1] = 1;
        for (int i = 1; i < n; ++i) {
            fwd[i] = fwd[i - 1] * nums[i - 1];
        }
        for (int i = n - 2; i >= 0; --i) {
            bwd[i] = bwd[i + 1] * nums[i + 1];
        }
        for (int i = 0; i < n; ++i) {
            res[i] = fwd[i] * bwd[i];
        }
        return res;
    }
}


// 若可以用除法
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] product = new int[nums.length];
        int cur = 1;
        for (int i = 0; i < nums.length; i++) {
            cur = cur * nums[i];
            product[i] = cur;
        }
        
        for (int i = 0; i < nums.length; i++) {
            product[i] = product[nums.length - 1] / nums[i];
        }
        
        return product;
    }
}