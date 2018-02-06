// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        System.out.println(Math.sqrt(85));
        System.out.println(mySqrt(85));
        System.out.println("");
        System.out.println(mySqrt2(85));
    }

    public int mySqrt(int x) {
        // 把這個問題轉成binary search, 要在1~x中找到一個數字, 該數字^2 會> x的
        long start = 1;
        long end = x;

        while (start + 1 < end) {
            System.out.println(start + " " + end);
            long mid = start + (end - start) / 2;
            long cur = mid * mid;
            // cur >= x 代表目標一定在現在end的左邊或是end
            if (cur > x) {
                end = mid;
            } else {
                start = mid;
            }
        }

        // 先看end若end符合就回傳end, 因為end符合就不可能是start, 因start一定也符合 start * start < x的條件
        if (end * end <= x) {
            return (int)end;
        }
        return (int)start;

    }

    public float mySqrt2(int target) {
        // 把這個問題轉成binary search, 要在1~x中找到一個數字, 該數字^2 會> x的
        float eps = 1e-6f;
        float start = 0, end = target;
        
        while (Math.abs(start - end) > eps) {
            float mid = start + (end - start) / 2;
            float cur = mid * mid;
            if (cur > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        return start;
    }
}


// Cubic Root
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        
        System.out.println(Math.cbrt(3));
        System.out.println(myCubic(3));
    }
    
    public double myCubic(int target) {
        // 把這個問題轉成binary search, 要在1~x中找到一個數字, 該數字^2 會> x的
        double eps = 1e-9;
        double start = 0, end = target;

        while (Math.abs(start - end) > eps) {
            double mid = start + (end - start) / 2;
            double cur = mid * mid * mid;
            if (cur > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        return start;
    }
}