class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>();
        for (String cur : wordList) {
            dict.add(cur);
        }
        
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>(); // 走訪過的標記避免重複走訪
        
        q.add(beginWord);
        visited.add(beginWord);
        
        int ans = 1;
        while (!q.isEmpty()) {
            ans++;
            int qsize = q.size();
            for (int i = 0; i < qsize; i++) {
                String cur = q.remove();
                List<String> neighbor = getNeighbor(cur, dict);
                
                for (String nextWord : neighbor) {
                    if (visited.contains(nextWord)) {
                        continue;
                    }
                    
                    // 終止條件：找到endWord
                    if (endWord.compareTo(nextWord) == 0) {
                        return ans;
                    }
                    
                    q.add(nextWord);
                    visited.add(nextWord);
                }
                
            }
        }
        return 0;
    }
    
    List<String> getNeighbor(String cur, Set<String> dict) {
        // hot --> lot || dot
        // 窮舉hot可能的變化 3 * 25
        List<String> neighbor = new ArrayList<>();
        
        for (int i = 0; i < cur.length(); i++) {
            for (int j = 0; j < 26; j++) {
                StringBuilder nextWord = new StringBuilder(cur);
                
                // hot不要選hot
                if (nextWord.charAt(i) == (char)(j + 97)) {
                    continue;
                }
                
                nextWord.setCharAt(i, (char)(j + 97));
                if (dict.contains(nextWord.toString())) {
                    neighbor.add(nextWord.toString());
                }
            }
        }
        return neighbor;
    }
}