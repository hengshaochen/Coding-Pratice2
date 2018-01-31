// 1. Fib number
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        int n = 10;
        
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        System.out.println(dp[n]);
    }
}

// 優化空間
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        int n = 10;
        
        int prepre = 0;
        int pre = 1;
        int cur = 0;
        for (int i = 2; i <= n; i++) {
            cur = prepre + pre;
            prepre = pre;
            pre = cur;
        }
        System.out.println(cur);
    }
}

/////////////////////////////////
// CSS
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        //String input = "#F00";
        String input = "#800080";
        
        // 明天要加上一些corner case
        if (input.length() < 4 || input.length() > 6) {
            // exception
        }
        
        if (input.length() == 4) {
            StringBuilder sb = new StringBuilder();
            sb.append('#');
            sb.append(input.charAt(1));
            sb.append(input.charAt(1));
            sb.append(input.charAt(2));
            sb.append(input.charAt(2));
            sb.append(input.charAt(3));
            sb.append(input.charAt(3));
            input = sb.toString();
        }
        
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i <= 9; i++) {
            map.put( (char)(i + '0') , i);
        }
        for (int i = 0; i <= 5; i++) {
            map.put( (char)('A' + i) , i + 10);
        }
        
        System.out.println(map.get('C'));
        input = input.toUpperCase();
        
        long r = (map.get(input.charAt(1))) * 16 + (map.get(input.charAt(2)));
        long g = (map.get(input.charAt(3))) * 16 + (map.get(input.charAt(4)));
        long b = (map.get(input.charAt(5))) * 16 + (map.get(input.charAt(6)));
        
        long ans = r + (long)Math.pow(16, 2) * g + (long)Math.pow(16, 4) * b;
        
        
        System.out.println(ans);
    }
}