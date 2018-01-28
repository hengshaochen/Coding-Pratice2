class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        // 思路：用i遍歷flowerbed, 當i不為0, pre end都要是0就把n--, 但是
        // 注意corner case: 當i == 0, 在最左邊, 前面沒花盆所以pre = 0
        // i == flowerbed.length - 1, 在最右邊, 後面沒花盆所以end = 0
        
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                int pre = i == 0 ? 0 : flowerbed[i - 1];
                int post = i == flowerbed.length - 1 ? 0 : flowerbed[i + 1];
                
                if (pre == 0 && post == 0) {
                    flowerbed[i] = 1;
                    n--;
                }
            }
        }
        // 只要可以種 >= n 的花就可以true
        if (n <= 0) {
            return true;
        }
        return false;
        
        /*
        1 0 0 0 0
        p i e
        1 0 0 0 0
        p i e
        1 0 1 0 0
          p i e
        1 0 1 0 0
            p i e
        1 0 1 0 1
              p i e
        可種兩盆
        */
    }
}