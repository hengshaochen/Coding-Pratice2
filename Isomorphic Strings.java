class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), t.charAt(i));
            }
            
            if (map.get(s.charAt(i)) != t.charAt(i)) {
                return false;
            }
        }
        
        HashMap<Character, Character> revMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            if (!revMap.containsKey(t.charAt(i))) {
                revMap.put(t.charAt(i), s.charAt(i));
            }
            
            if (revMap.get(t.charAt(i)) != s.charAt(i)) {
                return false;
            }
        }
        
        return true;
    }
}