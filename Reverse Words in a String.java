public class Solution {
    public String reverseWords(String s) {
        // corner1: "   the   sky    is   blue   " ? yes
        // corner2: "" , null string ? yes
        // corner3: "      the. sky. i@ blue" --> "blue i@ sky. the." 最後的空格不需要
        if (s == null || s.length() == 0) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        String[] sp = s.split(" ");
        
        for (int i = sp.length - 1; i >= 0; i--) {
            // 不是空格，記得是""
            if (sp[i].compareTo("") != 0) {
                sb.append(sp[i] + " ");
            }
        }
        
        // 當input為"       "，因為切出來的sp[i]都是""，都不會加入builder
        if (sb.length() == 0) {
            return "";
        } else {
            // 去除最尾端的空格
            return sb.substring(0, sb.length() - 1);
        }
    }
}