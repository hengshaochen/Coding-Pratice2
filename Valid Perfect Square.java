class Solution {
    public boolean isPerfectSquare(int num) {
        // 判斷一個數是否為完全平方數：就是1*1, 2*2, 3*3這種
        long start = 1;
        long end = num;
        while (start + 1 < end) {
            long mid = start + (end - start) / 2;
            // System.out.println("mid " + mid);
            if (mid * mid > num) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (end * end == num || start * start == num) {
            return true;
        }
        return false;
    }
}