/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        // DFS 2 pass求法
        
        // 先獲得深度
        int depth = getDepth(nestedList);
        
        // 獲得深度後從該深度開始, 越深一層就把weight - 1
        return getSum(nestedList, depth);
    }
    
    int getDepth(List<NestedInteger> nestedList) {
        int depth = 0;
        for (NestedInteger cur : nestedList) {
            if (cur.isInteger()) {
                depth = Math.max(depth, 1);
            } else {
                depth = Math.max(depth, getDepth(cur.getList()) + 1);
            }
        }
        return depth;
    }
    
    int getSum(List<NestedInteger> nestedList, int depth) {
        int ans = 0;
        
        for (int i = 0; i < nestedList.size() ;i++) {
            if (nestedList.get(i).isInteger()) {
                ans = ans + nestedList.get(i).getInteger() * depth;
            } else {
                ans = ans + getSum(nestedList.get(i).getList(), depth - 1);
            }
        }
        return ans;
    }
}

// One-Pass
class Solution {
    int max_depth = 0;
    public int depthSumInverse(List<NestedInteger> nestedList) {
        // DFS 1 pass求法
        Map<Integer, Integer> map = new HashMap<>();
        getLevelSum(nestedList, map, 1);
        
        // post processing
        int ans = 0;
        for (Integer level : map.keySet()) {
            ans = ans + map.get(level) * ((max_depth + 1) - level);
        }
        
        return ans;
    }
    void getLevelSum(List<NestedInteger> nestedList, Map<Integer, Integer> map, int depth) {
        int sum = 0;
        for (NestedInteger cur : nestedList) {
            if (cur.isInteger()) {
                sum = sum + cur.getInteger();
            } else {
                getLevelSum(cur.getList(), map, depth + 1);
            }
        }
        if (!map.containsKey(depth)) {
            map.put(depth, sum);
        } else {
            map.put(depth, map.get(depth) + sum);
        }
        max_depth = Math.max(max_depth, depth);
    }
}