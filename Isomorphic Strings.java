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

// Follow up2:
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


// Follow Up2:
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
    
        String[] input = {"abbcd", "offer", "apple", "qq", "oo", "aoo","lpp"};

        Map<String, List<String>> map = new HashMap<>();
    
        for (String cur : input) {
            String cur_encode = encodeString(cur);
            if (!map.containsKey(cur_encode)) {
                List<String> cur_list = new ArrayList<>();
                cur_list.add(cur);
                map.put(cur_encode, cur_list);
            } else {
                map.get(cur_encode).add(cur);   
            }
        }
        
        // 將答案放入ArrayList
        List<List<String>> ans = new ArrayList<>();
        for (String cur : map.keySet()) {
            ans.add(map.get(cur));
        }
        
        System.out.println(ans);
    }
    
    String encodeString(String s) {
        StringBuilder encodeS = new StringBuilder();
        HashMap<Character, Integer> mapS = new HashMap<>();
        int countS = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // 處理S
            if (!mapS.containsKey(s.charAt(i))) {
                encodeS.append(countS);
                mapS.put(s.charAt(i), countS++);
            } else {
                encodeS.append(mapS.get(s.charAt(i)));
            }
        }
        
        return encodeS.toString();
    }
}

