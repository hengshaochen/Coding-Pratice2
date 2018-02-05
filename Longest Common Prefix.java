class Solution {
    public String longestCommonPrefix(String[] strs) {
        // corner case1: str == null || str is empty --> ""
        // corner case2: A != a 大小寫要區分
        // corner case3: only have one string: return that string
        if (strs == null || strs.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        
        String first = strs[0];
        for (int i = 0; i < first.length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                // 若發生當前掃到的strs[j]這個單字已經超過長度，直接回傳 || 當前掃到的strs[j]的第i個char和strs[0]不相等，直接回傳
                if (strs[j].length() <= i || first.charAt(i) != strs[j].charAt(i)) {
                    return sb.toString();
                }
            }
            sb.append(first.charAt(i));
        }
        
        return sb.toString();
    }
}