class Solution {
    public boolean isNumber(String s) {
        // 先去除前面的空格
        s = s.trim();
        
        boolean numberSeen = false;
        boolean pointSeen = false;
        boolean eSeen = false;
        
        // 這個變數定義成e後面有沒有數字, 一開始沒有e的情況下初始化為true, 因為沒有e出現, 就不怕e造成的不合法
        boolean numberAfterE = true;   // 不能出現1e這種情況, e的後面一定還要有數字還合法
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                // 不用轉成int, 直接用ASCII比
                numberSeen = true;
                numberAfterE = true;
            } else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                if (i != 0 && s.charAt(i - 1) != 'e') {
                    // 如果正負號不是出現在第一個位置, 不是出現在e的下一個就是false, 1e-6 這可以
                    return false;
                }
            } else if (s.charAt(i) == '.') {
                if (eSeen || pointSeen) {
                    // e後面不能有點, e不能出現兩次
                    return false;
                }
                pointSeen = true;
            } else if (s.charAt(i) == 'e') {
                if (eSeen || !numberSeen) {
                    // e只能出現一次, e前面沒有數字出現不行 例如e12 不行
                    return false;
                }
                eSeen = true;
                numberAfterE = false;
            } else {
                // 出現其他東西 --> false
                return false;
            }
        }
        
        // 1這種情況會true, 因為numberAfterE初始化為true
        return numberSeen && numberAfterE;
    }
}