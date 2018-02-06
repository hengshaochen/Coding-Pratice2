class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        int idx1 = -1;
        int idx2 = -1;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (word1.compareTo(words[i]) == 0) {
                idx1 = i;
            }
            if (word2.compareTo(words[i]) == 0) {
                idx2 = i;
            }
            // 兩個同時要找到 並且 有更小的ans再更新
            if (idx1 >= 0 && idx2 >= 0 && ans > Math.abs(idx1 - idx2)) {
                ans = Math.abs(idx1 - idx2);
            }
        }
        return ans;
    }
}

// II:
class WordDistance {

    HashMap<String, ArrayList<Integer>> map = new HashMap<>();
    public WordDistance(String[] words) {
        for (int i = 0; i < words.length; i++) {
            if (!map.containsKey(words[i])) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(words[i], list);
            } else {
                map.get(words[i]).add(i);    
            }
        }
    }
    
    // O(M*N)版本
    public int shortest(String word1, String word2) {
        int min = Integer.MAX_VALUE;
        ArrayList<Integer> list1 = map.get(word1);
        ArrayList<Integer> list2 = map.get(word2);
        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {
                min = Math.min(min, Math.abs(list1.get(i) - list2.get(j)));
            }
        }
        return min;
    }
    // O(M + N)版本則一即可
    public int shortest(String word1, String word2) {
        int min = Integer.MAX_VALUE;
        ArrayList<Integer> list1 = map.get(word1);
        ArrayList<Integer> list2 = map.get(word2);
        int i = 0;
        int j = 0;
        
        while (i < list1.size() && j < list2.size()) {
            min = Math.min(min, Math.abs(list1.get(i) - list2.get(j)));
            if (list1.get(i) < list2.get(j)) {
                i++;
            } else {
                j++;
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

// III:
class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int idx1 = -1;
        int idx2 = -1;
        int ans = Integer.MAX_VALUE;
        int pre_idx1 = -1;
        
        for (int i = 0; i < words.length; i++) {
            pre_idx1 = idx1;
            if (word1.compareTo(words[i]) == 0) {
                idx1 = i;
            }
            if (word2.compareTo(words[i]) == 0) {
                idx2 = i;
            }
            // 兩個同時要找到 並且 有更小的ans再更新
            
            // 分兩種case處理, 一種是word1 == word2, 這種就是新增一個變數pre_idx1紀錄前一次的idx1, 然後當pre_idx1 != pre_idx就更新ans
            if (word1.compareTo(word2) == 0) {
                if (pre_idx1 != -1 && pre_idx1 != idx1) {
                    ans = Math.min(ans, Math.abs(pre_idx1 - idx1));
                }
            } else {
                if (idx1 >= 0 && idx2 >= 0 && ans > Math.abs(idx1 - idx2)) {
                    ans = Math.abs(idx1 - idx2);
                }
            }
        }
        return ans;
    }
}

// II的Follow up: 一次可以查三個以上的單詞：（用Minimum Window Substring --- Sliding Window)
// 最後把minimum window size - 1就是答案
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        String[] input = {"pratice", "makes", "perfect", "coding", "makes", "bus"};
        //String[] query = {"bus", "coding", "makes"};
        String[] query = {"bus", "makes", "makes"};
        
        System.out.println(findShortest(input, query));
    }
    
    public int findShortest(String[] input, String[] query) {
        if (input.length == 0 || query.length == 0) {
            return 0;
        }
        
        // 先統計query的字數的數量
        HashMap<String, Integer> q_map = new HashMap<>();
        for (int i = 0; i < query.length; i++) {
            if (!q_map.containsKey(query[i])) {
                q_map.put(query[i], 1);
            } else {
                q_map.put(query[i], q_map.get(query[i]) + 1);
            }
        }
        
        // 設置起點終點及答案
        int i = 0, j = 0, min_distance = Integer.MAX_VALUE;
        
        // 當前window的map
        HashMap<String, Integer> cur_map = new HashMap<>();
        
        while (i < input.length) {
            while (!valid(cur_map, q_map) && j < input.length) {
                if (!cur_map.containsKey(input[j])) {
                    cur_map.put(input[j], 1);
                } else {
                    cur_map.put(input[j], cur_map.get(input[j]) + 1);
                }
                j++;
            }
            
            // 看看當前window會不會更小
            if (valid(cur_map, q_map)) {
                if (min_distance > j - i) {
                    min_distance = j - i;
                }
            }
            
            // 向右移動
            cur_map.put(input[i], cur_map.get(input[i]) - 1);
            i++;
        }
        
        return min_distance - 1;
    }
    
    public boolean valid(HashMap<String, Integer> cur_map, HashMap<String, Integer> q_map) {
        for (String q_element : q_map.keySet()) {
            if (!cur_map.containsKey(q_element) ||
                q_map.get(q_element) > cur_map.get(q_element)) {
                return false;
            }
        }
        return true;
    }
}
