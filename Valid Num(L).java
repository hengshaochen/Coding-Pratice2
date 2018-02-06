// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        System.out.println(isNumberNO_EXP("-.5"));
    }

    
    // 面試用這個版本
    public boolean isNumberNO_EXP(String s) {
        // Corner1: "    123"是否為number? 是，因此用s.trim去掉前面空格。若不是就不需要s.trim
        // Corner2: "1   2"是否為number? 不是
        // Corner3: "1..3" 是否為number? 不是
        // Corner4: "-.5" ".5"是否為number? 如果是，判斷條件就可以比較鬆，底下代碼就可。如果不是，把註解拿掉，就把.之前一定要出現數字才合法，先前沒數字就非法
        // Corner5: "   " 是否為number? 不是
        // Corner6: "36+9" 是否為number? 不是
        // Corner7: "36a" 是否為number? 不是
        // Corner8: "arr" 是否為number? 不是
        s = s.trim();
        
        boolean numberSeen = false;
        boolean pointSeen = false;
        
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                // 不用轉成int, 直接用ASCII比
                // Corner8
                numberSeen = true;
            } else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                if (i != 0) {
                    // 正負號不是出現在第一個位置
                    // Corner6
                    return false;
                }
            } else if (s.charAt(i) == '.') {
                if (pointSeen) {
                //if (numberSeen == false || pointSeen) { // 如果不允許 -.5 或是 .5 , 就改成這行
                // 點不能出現兩次
                // corner4, corner3
                    return false;
                }
                pointSeen = true;
            } else {
                // 出現其他東西 --> false
                // corner7, cotnrt2
                return false;
            }
        }
        
        // Corner5, 只被去除空格，因此不會進入上面判斷的迴圈，這時不能直接return true, 要看有沒有出現過數字
        return numberSeen;
    }
    
}