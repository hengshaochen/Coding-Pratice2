class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return ans;
        }
        /*
        ["eat", "tea", "tan", "ate", "nat", "bat"]
        [ 1       1     0       1      0      0]
        [ 1       1     2       1      0      0]
        [ 1       1     2       1      3      3]
        
        Worst Case: 當所有都不一樣，每次都要比較，譬如有5個string, 第一次比5次，第二次比4次，比較次數是5+4+3+...+1 = 15
        是n(n+1) / 2 = O(n^2) , 然後每次呼叫isAnagram都要花O(n)， 因此總共O(n^3)
        */
        
        boolean[] finish = new boolean[strs.length];
        for (int i = 0; i < strs.length; i++) {
            if (finish[i] == false) {
                List<String> cur = new ArrayList<>();
                for (int j = 0; j < strs.length; j++) {
                    if (i != j && finish[j] == false) {
                        if (isAnagram(strs[i], strs[j])) {
                            finish[j] = true;
                            cur.add(strs[j]);
                        }
                    }
                }
                cur.add(strs[i]);
                finish[i] = true;
                ans.add(cur);
            }
        }
        return ans;
    }
    
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


// 優化：
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // 建立一個HashMap, key是string, value是該string的答案arraylist
        // 針對每一個String排序, 若排序後的字在hashmap中, 則加入, 不在則新增至map中
        List<List<String>> ans = new ArrayList<>();
        
        // Arrays.sort不支援String, 要用char
        Map<String, List<String>> map = new HashMap<>();
        
        for (int i = 0; i < strs.length; i++) {
            char[] cur = strs[i].toCharArray();
            Arrays.sort(cur);
            String sorted_string = new String(cur);
            if (!map.containsKey(sorted_string)) {
                List<String> newlist = new ArrayList<>();
                newlist.add(strs[i]);
                map.put(sorted_string, newlist);
            } else {
                map.get(sorted_string).add(strs[i]);
            }
        }
        
        // 遍歷hashmap放到答案中
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            ans.add(entry.getValue());
        }
        
        return ans;
    }
}