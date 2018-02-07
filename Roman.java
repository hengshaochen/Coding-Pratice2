// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        String input = "V";
        // String input = "IIII";  // not valid
        
        System.out.println(romanToInt(input));
    }
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        
        int ans = 0;
        
        for (int i = 0; i < s.length();i ++) {
            if (i > 0 && map.get(s.charAt(i)) > map.get(s.charAt(i - 1))) {
                ans = ans - (map.get(s.charAt(i - 1))) + (map.get(s.charAt(i)) - map.get(s.charAt(i - 1)));
            } else {
                ans = ans + map.get(s.charAt(i));
            }
        }
        
        if ( (intToRoman(ans)).compareTo(s) != 0 ) {
            return -1;
        }
        return ans;
    }
    
    public String intToRoman(int n) {
        // Write your code here
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[n / 1000] + C[(n / 100) % 10] + X[(n / 10) % 10] + I[n % 10];
    }
}