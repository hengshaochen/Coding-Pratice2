class Solution {
    public boolean isAnagram(String s, String t) {
        // corner: s跟t都存在嗎？
        
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), 1);
            } else {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            }
        }
        
        for (int i = 0; i < t.length(); i++) {
            if (!map.containsKey(t.charAt(i))) {
                return false;
            } else {
                map.put(t.charAt(i), map.get(t.charAt(i)) - 1);
            }
        }
        
        for (Character c : map.keySet()) {
            if (map.get(c) != 0) {
                return false;
            }
        }
        
        return true;
    }
}

// 優化程式碼：
public class Solution {
    public boolean isAnagram(String s, String t) {
        // The idea is simple. It creates a size 26 int arrays as buckets for each letter in alphabet.
        // It increments the bucket value with String s and decrement with string t.
        // So if they are anagrams, all buckets should remain with initial value which is zero. So just checking that and return


        int[] alphabet = new int[26];
        
        for (int i = 0; i < s.length(); i++) {
            alphabet[s.charAt(i) - 'a']++;
        }
        
        for (int i = 0; i < t.length(); i++) {
            alphabet[t.charAt(i) - 'a']--;
        }
        
        for (int i : alphabet) {
            if (i != 0) {
                return false;
            }
        }
        
        return true;
    }
}