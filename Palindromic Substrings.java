// 法1: Brute Force: O(n^3)
class Solution {
    public int countSubstrings(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 1; j <= s.length() - i; j++) {
                String cur = s.substring(i, i + j);
                if (isPal(s.substring(i, i + j))) {
                    ans++;
                }
            }
        }
        return ans;
    }
    
    boolean isPal(String cur) {
        int start = 0;
        int end = cur.length() - 1;
        while (start < end) {
            if (cur.charAt(start) != cur.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}

// 法2: 以字符串中的每一个字符都当作回文串中间的位置，然后向两边扩散，每当成功匹配两个左右两个字符，结果res自增1，然后再比较下一对。注意回文字符串有奇数和偶数两种形式，如果是奇数长度，
// 那么i位置就是中间那个字符的位置，所以我们左右两遍都从i开始遍历；如果是偶数长度的，那么i是最中间两个字符的左边那个，右边那个就是i+1，这样就能cover所有的情况啦，而且都是不同的回文子字符串
class Solution {
    int ans = 0;
    public int countSubstrings(String s) {
        for (int i = 0; i < s.length(); i++) {
            checkPal(i, i, s);   // check odd
            checkPal(i, i + 1, s);  // check even
        }
        return ans;
    }
    
    void checkPal(int i, int j, String s) {
        while (i >= 0 && j <= s.length() - 1) {
            if (s.charAt(i) == s.charAt(j)) {
                // System.out.println(s.substring(i, j + 1));
                ans++;
                i--;
                j++;
            } else {
                return;
            }
        }
    }
}