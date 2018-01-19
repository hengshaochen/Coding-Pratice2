// 快速幂
class Solution {
    public double myPow(double x, int n) {
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        
        double now_mul = x;
        double ans = 1;
        while (n != 0) {
            if (n % 2 == 1) {
                ans = ans * now_mul;
            }
            now_mul = now_mul * now_mul;
            n = n / 2;
        }
        return ans;
    }
}

// 二分法
class Solution {
    public double myPow(double x, long n) {
        // base case
        if (n == 1 || x == 0 || x == 1) {
            return x;
        }
        if (n == 0) {
            return 1;
        }
        
        // 前處理次方為負數
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        
        double ans = 1;
        
        // Step1: 切子問題 / 拿到子問題算好的ans
        double sub_ans = myPow(x, n / 2);
        
        // Step2: 當層做的事情：把子問題 乘上 子問題, 且若當層次方數 mod 2 為1, 還要再乘以x
        if (n % 2 == 1) {
            ans = sub_ans * sub_ans * x;
        } else {
            ans = sub_ans * sub_ans;
        }
        
        // Step3: 回傳給父親
        return ans;
    }
}