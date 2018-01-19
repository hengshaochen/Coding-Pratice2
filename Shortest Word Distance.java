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
