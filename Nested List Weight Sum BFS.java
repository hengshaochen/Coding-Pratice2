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
    public int depthSum(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        
        Queue<NestedInteger> q = new LinkedList<>();
        for (NestedInteger cur : nestedList) {
            q.add(cur);
        }
        
        // [1,2,[3,[4]]]
        
        int ans = 0;
        int level = 1;
        while (!q.isEmpty()) {
            int level_sum = 0;
            int qsize = q.size();
            for (int i = 0; i < qsize; i++) {
                NestedInteger cur = q.remove();
                if (cur.isInteger()) {
                    level_sum += cur.getInteger();
                } else {
                    for (int j = 0; j < cur.getList().size(); j++) {
                        q.add(cur.getList().get(j));
                    }
                }
            }
            ans = ans + ((level) * (level_sum));
            level++;
        }
        return ans;
    }
}

// II
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
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        
        Queue<NestedInteger> q = new LinkedList<>();
        for (NestedInteger cur : nestedList) {
            q.add(cur);
        }
        
        // [1,2,[3,[4]]]
        
        int ans = 0;
        //int level = 1;
        int pre_sum = 0;   // ***
        while (!q.isEmpty()) {
            int level_sum = 0;
            int qsize = q.size();
            for (int i = 0; i < qsize; i++) {
                NestedInteger cur = q.remove();
                if (cur.isInteger()) {
                    level_sum += cur.getInteger();
                } else {
                    for (int j = 0; j < cur.getList().size(); j++) {
                        q.add(cur.getList().get(j));
                    }
                }
            }
            pre_sum = pre_sum + level_sum;   // ***
            ans = ans + pre_sum;             // 先前的又再加一次, 例如[1,2,[3,[4]]], level = 2時, 上一層pre_sum = 1 + 2 = 3
            // 處理[3,[4]], pre_sum = 3 + 3 = 6 , ans = 3 + 6 = 9
            // 處理[4], pre_sum = 6 + 4 = 10, ans = 9 + 10 = 19
            // level++;
        }
        return ans;
    }
}