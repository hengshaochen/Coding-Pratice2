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