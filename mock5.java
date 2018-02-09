Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.
      0 1  2 3  4 5 6  7 8
    [-2,1,-3,4,-1,2,1,-5,4]
sum -2,-1,-4,0,-1,1,2,-3,1
sum 

subarray(0,2) = -4
subarray(0,6) =  2
subarray(3,6) = 2 - (-4) = 6

cur_sum - previous_min_value = index(3) - index(6)  sum of subarray from(3,6)
cur_sum(6) = 2, previous_min_sum_value_ = -4 -->   cur_sum(6) - prevous_min_value


    public int maxSub(int[] input) {
        if (input == null || input.length == 0) {
            return 0;
        }
        
        int pre_sum_min = 0;
        int max = Integer.MIN_VALUE;
        int cur_sum = 0;
        for (int i = 0; i < input.length; i++) {
            cur_sum = cur_sum + input[i];
            max = Math.max(max, cur_sum - pre_sum_min);  // line 12
            pre_sum_min = Math.min(pre_sum_min, cur_sum);
        }
        
        return max;
    }
    Time: O(n), Space: O(1)
    
    
 
    Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)

1*1 + 4*2 + 6*3 = 27
Example 2:
Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)

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
 
      // Set this NestedInteger to hold a nested list and adds a nested integer to it.
      public void add(NestedInteger ni);
 
      // @return the nested list that this NestedInteger holds, if it holds a nested list
      // Return null if this NestedInteger holds a single integer
      public List<NestedInteger> getList();
  }
 
Example2: [1,[4,[6]]]
public int depthSum(List<NestedInteger> nestedList) {
    if (nestedList == null || nestedList.size() == 0) {
        return 0;
    }
    
    Queue<NestedInteger> q = new LinkedList<>();
    for (NestedInteger cur : nestedList) {
        q.add(cur);
    }
    
    int ans = 0;
    int level = 1;
    while (!q.isEmpty()) {
        int qsize = q.size();
        int level_sum = 0;
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

qsize = 3 
i = 0, 1, 2

0 + 1 * 3 = 3

level_sum = 1+2 = 3

----
qsize = 2
i = 0 , 1
level_sum = 3, 
3 * 2 = 6

6 + 3 = 9

[4]
4 * 3 = 12

9 + 12 = 21


/*  EX:
    [1,2,[3,[4]]]
    
    1*1 + 2*1 = 3
    [3,[4]]
    3*2 = 6  ,  6 + 3 = 9
    [4]
    4*3 = 12  ,  12 + 9 = 21
*/
[[[[[[[[[[[5]]]]]]]]]]]
}

List<Integer> ans = LinkedList<>();

ans.add(1);