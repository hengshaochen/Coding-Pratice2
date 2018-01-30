class Solution {
    String ans;
    public String longestPalindrome(String s) {
        // 中心擴散 O(n^2)
        ans = "";
        if (s == null || s.length() == 0) {
            return ans;
        }
        
        for (int i = 0; i < s.length(); i++) {
            helper(s, i, i);
            helper(s, i, i + 1);
        }
        return ans;
    }
    
    void helper(String s, int i, int j) {
        while (i >= 0 && j <= s.length() - 1 && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        // b a a a c
        //   i   j
        // i       j <--退出時的情況
        String cur = s.substring(i + 1, j);
        if (cur.length() > ans.length()) {
            ans = cur;
        }
    }
}