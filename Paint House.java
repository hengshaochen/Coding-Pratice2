class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        for (int i = 1; i < costs.length; i++) {
            costs[i][0] = costs[i][0] + Math.min(costs[i - 1][1], costs[i - 1][2]);
            costs[i][1] = costs[i][1] + Math.min(costs[i - 1][0], costs[i - 1][2]);
            costs[i][2] = costs[i][2] + Math.min(costs[i - 1][0], costs[i - 1][1]);
        }
        int final_house = costs.length - 1;
        return Math.min(costs[final_house][0], Math.min(costs[final_house][1], costs[final_house][2]));
    }
}

// Paint House II
class Solution {
    public int minCostII(int[][] costs) {
        // 最小值 = min1, 第二小的 = min2
        // 1        2
        // red 4    blue black green...
        // blue 5   red
        // 第一個房子是red, 第二個只能選除了red之外的k - 1種
        // 第一個房子是blue, 第二個只能選red 選最小
        
        if (costs == null || costs.length == 0) {
            return 0;
        }
        
        int n = costs.length;    // n個房子
        int k = costs[0].length; // k種顏色
        
        // min1, min2存這次最小 以及第二小的 塗色累積cost的最後塗色指標，可以想像成cur
        // last1, last2代表上次最小，以及第二小，累積塗色cost的最後塗色指標，想像成pre
        // 初始化為-1代表沒塗色過
        int min1 = -1, min2 = -1;
        
        for (int i = 0; i < n; i++) {
            // 跑所有房子
            int last1 = min1, last2 = min2;
            min1 = -1;
            min2 = -1;
            for (int j = 0; j < k; j++) {
                if (j != last1) {
                    // 當前跑道的顏色和之前最小的顏色不同，就拿last1，就是之前最小的顏色加上當前
                    costs[i][j] = costs[i][j] + (last1 < 0 ? 0 : costs[i - 1][last1]);
                } else {
                    // 和最小顏色相同，取第二小的
                    costs[i][j] = costs[i][j] + (last2 < 0 ? 0 : costs[i - 1][last2]);
                }
                
                if (min1 < 0 || costs[i][j] < costs[i][min1]) {
                    // 如果當前算出來的比之前最小的還小，更新min，並把之前最小賦值給第二小
                    min2 = min1;
                    min1 = j;
                } else if (min2 < 0 || costs[i][j] < costs[i][min2]) {
                    min2 = j;
                }
            }
        }
        return costs[n - 1][min1];
        
    }
}