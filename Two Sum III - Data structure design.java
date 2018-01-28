// Find O(n), add O(1)
class TwoSum {

    /** Initialize your data structure here. */
    HashMap<Integer, Boolean> map;
    public TwoSum() {
        map = new HashMap<>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        if (!map.containsKey(number)) {
            map.put(number, false);
        } else {
            map.put(number, true);
        }
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for (Integer cur : map.keySet()) {
            if (map.containsKey(value - cur)) {
                // Case1 : 當前值 == 差值, 確認有2個當前值, ex: 2, 3 | target = 4
                if (cur == (value - cur)) {
                    if (map.get(cur) == true) {
                        return true;
                    }
                } 
                // Case2 : 當前值 != 差值, 直接true, ex: 2, 3 | target = 5
                else {
                    return true;
                }
            }
        }
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */

// Find O(1) Add O(n) 當find很多的時候適用
class TwoSum {

    // Follow up, 如果find很多次, 讓find變成O(1)
    // 思路：那就代表find, 用一次操作就能判斷有沒有在map中, 之前要遍歷map, 因此現在希望不遍歷map. 代表要創一個map直接存
    // 目前data structure有的sum有哪些！
    // 再創一個set保存現在有的答案數量
    HashSet<Integer> sum;
    HashSet<Integer> num;
    public TwoSum() {
        sum = new HashSet<Integer>();
        num = new HashSet<Integer>();
    }
    
    // add要花費O(n), 遍歷num, 把它加上當次的number放到sum裡面
    public void add(int number) {
        if (num.contains(number)) {
            // 因為是2sum, 之前和其他元素配對過, 只要加入自己和自己配對
            sum.add(number * 2);
        } else {
            // 不存在 --> 和其他元素配對
            Iterator<Integer> it = num.iterator();
            while (it.hasNext()) {
                sum.add(it.next() + number);
            }
            num.add(number);
        }
    }
    
    // find只需要O(1)
    public boolean find(int value) {
        return sum.contains(value);
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */