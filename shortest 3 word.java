// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    public Main() {
        //                     0         1         2          3        4         5
        String[] words = {"practice", "makes", "perfect", "coding", "makes", "practice"};
        
        String word1 = "practice";
        String word2 = "makes";
        String word3 = "coding";
        
        WordDistance dict = new WordDistance(words);
        System.out.println(dict.shortest(word1, word1));
        
        //System.out.println(dict.shortest3Word(word1, word2, word3));
        
    }
    
    
}

class WordDistance {
    // corner1: word1, word2  same? yes
    // corner2: Guarantee word1 and word2 in the words? if not in the words, return? 
    // 這個字典多大？如果很大遍歷字典會很慢, 可以建立一個資料結構, 用HashMap儲存該字出現的index, 然後比較兩word的index取最近的
    // 複雜度是 O(word1出現的次數 + word2出現的次數)
    HashMap<String, ArrayList<Integer>> map;
    public WordDistance(String[] words) {
        map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (!map.containsKey(words[i])) {
                map.put(words[i], new ArrayList<Integer>());
            }
            map.get(words[i]).add(i);
        }
    }
    
    public int shortest(String word1, String word2) {
        if (!map.containsKey(word1) || !map.containsKey(word2)) {
            return Integer.MAX_VALUE;
        }
        ArrayList<Integer> list1 = map.get(word1);
        ArrayList<Integer> list2 = map.get(word2);
        
        // word1 == word2
        int ans = Integer.MAX_VALUE;
        if (list1.equals(list2)) {
            for (int i = 0; i < list1.size() - 1; i++) {
                ans = Math.min(ans, Math.abs(list1.get(i) - list1.get(i + 1)) );
            }
            return ans;
        }
        
        // word1 != word2
        int i = 0;
        int j = 0;
        while (i < list1.size() && j < list2.size()) {
            ans = Math.min(ans, Math.abs(list1.get(i) - list2.get(j)) );
            if (list1.get(i) < list2.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return ans;
    }
    
    public int shortest3Word(String word1, String word2, String word3) {
        if (!map.containsKey(word1) || !map.containsKey(word2) || !map.containsKey(word3)) {
            return Integer.MAX_VALUE;
        }
        ArrayList<Integer> list1 = map.get(word1);
        ArrayList<Integer> list2 = map.get(word2);
        ArrayList<Integer> list3 = map.get(word3);
        
        int i = 0, j = 0, k = 0;
        int min = Integer.MAX_VALUE;
        while (i < list1.size() && j < list2.size() && k < list3.size()) {
            int cur = Math.abs(list1.get(i) - list2.get(j)) + Math.abs(list1.get(i) - list3.get(k))
                      + Math.abs(list2.get(j) - list3.get(k));
            
            min = Math.min(cur, min);
            
            // 移動數字最小的指標
            int min_number = Math.min(Math.min(list1.get(i), list2.get(j)), list3.get(k));
            if (min_number == list1.get(i)) {
                i++;
            } else if (min_number == list2.get(j)) {
                j++;
            } else {
                k++;
            }
        }
        return min;
    }
    

}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */