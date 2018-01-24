class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if( ( (1 + maxChoosableInteger) * maxChoosableInteger / 2) < desiredTotal) {
            return false;
        }
        
        int[] visited = new int[maxChoosableInteger + 1];
        HashMap<String, Boolean> memory = new HashMap<>();
        return dfs(visited, desiredTotal, memory);
    }
    
    boolean dfs(int[] visited, int cur_remain, HashMap<String, Boolean> memory) {
        String encode = Arrays.toString(visited);
        if (memory.containsKey(encode)) {
            return memory.get(encode);
        }
        for (int i = 1; i < visited.length; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                // 現在是以person1為主角思考, 如果剩下的total - 當前== 0 或是 呼叫遞回 person2不能贏 --> 代表person1贏
                if (cur_remain - i <= 0 || !dfs(visited, cur_remain - i, memory)) {
                    // 回朔
                    visited[i] = 0;
                    memory.put(encode, true);
                    return true;
                }
                visited[i] = 0;
            }
        }
        // 這層的所有方案接嘗試, 找不到解, 退回上一層
        memory.put(encode, false);
        return false;
    }
}