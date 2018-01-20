/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        // 初始化把當前名人設為 #0 個人
        int cur_celebrity = 0;
        
        for (int i = 1; i <= n - 1; i++) {
            if (knows(cur_celebrity, i)) {
                // 如果a認識b, a不是名人, 把當前名人改成b
                cur_celebrity = i;
            }
        }
        
        // 最後可能的名人candidate, 確認他是否被所有人所認識（因為有可能整個party中沒有名人)
        for (int i = 0; i <= n - 1; i++) {
            if (i != cur_celebrity) {
                // 確認所有人都不認識名人
                if (!knows(i, cur_celebrity)) {
                    return -1;
                }
                // 確認名人不認識所有人
                if (knows(cur_celebrity, i)) {
                    return -1;
                }
            }
        }
        return cur_celebrity;
        
    }
}