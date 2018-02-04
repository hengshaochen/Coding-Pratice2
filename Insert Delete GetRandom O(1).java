class RandomizedSet {

    /** Initialize your data structure here. */
    HashMap<Integer, Integer> map;
    ArrayList<Integer> list;
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (!map.containsKey(val)) {
            map.put(val, list.size());
            list.add(val);
            return true;
        }
        return false;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        // 如果要刪除的數字在最尾端-->直接刪除, 在中間-->和尾端交換然後再刪除
        /*
        [10,98,13,26]
        Case1: delete(26) : delete directly,
        Case2: delete(98) : swap to tail [10,26,13,98] --> delete
        */
            
        if (!map.containsKey(val)) {
            return false;
        } else {
            int idx = map.get(val);
            if (idx != list.size() - 1) {
                // 更新map, 把原本對應到尾端的元素index換成當前idx
                map.put(list.get(list.size() - 1), idx);
                // 不在尾端, 把當前尾端的值蓋掉idx (其實根本不用交換, 只要把尾端的放到idx就好), index不用放到尾端
                // [10,98,13,26] 刪除98, 把它變成[10,26,13,26] 接著刪除最尾端就可以達成刪除98的效果
                // ArrayList.set(Index, Element)
                list.set(idx, list.get(list.size() - 1));
            }
            //  刪除
            list.remove(list.size() - 1);
            map.remove(val);
            return true;
        }
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */


// Two HashMap Solution
class RandomizedSet {

    /** Initialize your data structure here. */
    HashMap<Integer, Integer> map1;  // value -> key
    HashMap<Integer, Integer> map2;  // key -> value
    public RandomizedSet() {
        map1 = new HashMap<>();
        map2 = new HashMap<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (!map1.containsKey(val)) {
            map1.put(val, map1.size());
            map2.put(map2.size(), val);
            return true;
        } else {
            return false;
        }
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (map1.containsKey(val)) {
            // Case1: 刪除中間元素, Case2: 刪除尾巴或是刪除完map size為空
                 
            int index = map1.get(val); 
            map1.remove(val);
            map2.remove(index);
            
            if (map1.size() == 0 || index == map1.size()) {
                // Case2, after delete map size == 0 || if last is deleted, do nothing
                return true;
            }
            
            // 從最尾巴拿元素，把這元素取代刪除後留下的空隙
            int key = map2.get(map2.size());
            map1.put(key, index);
            map2.remove(map2.size());
            map2.put(index, key);
            
            return true;
            
        } else {
            return false;
        }
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        if (map1.size() == 0) {
            return -1;
        }
        return map2.get(new Random().nextInt(map2.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */


 // Generic Type
class RandomizedSet<T> {

    /** Initialize your data structure here. */
    HashMap<T, Integer> map1;  // value -> key
    HashMap<Integer, T> map2;  // key -> value
    public RandomizedSet() {
        map1 = new HashMap<>();
        map2 = new HashMap<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(T val) {
        if (!map1.containsKey(val)) {
            map1.put(val, map1.size());
            map2.put(map2.size(), val);
            return true;
        } else {
            return false;
        }
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(T val) {
        if (map1.containsKey(val)) {
            // Case1: 刪除中間元素, Case2: 刪除尾巴或是刪除完map size為空
                 
            int index = map1.get(val); 
            map1.remove(val);
            map2.remove(index);
            
            if (map1.size() == 0 || index == map1.size()) {
                // Case2, after delete map size == 0 || if last is deleted, do nothing
                return true;
            }
            
            // 從最尾巴拿元素，把這元素取代刪除後留下的空隙
            T key = map2.get(map2.size());
            map1.put(key, index);
            map2.remove(map2.size());
            map2.put(index, key);
            
            return true;
            
        } else {
            return false;
        }
    }
    
    /** Get a random element from the set. */
    public T getRandom() {
        if (map1.size() == 0) {
            return null;
        }
        return map2.get(new Random().nextInt(map2.size()));
    }
    
    public boolean removeRandom() {
        return remove( map2.get(new Random().nextInt(map2.size()))  );
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

class Main
{
    public static void main (String[] args)
    {
        RandomizedSet<String> randSet = new RandomizedSet<String>();
        randSet.insert("Henry");
        randSet.insert("Emily");
        randSet.insert("Chen");
        System.out.println(randSet.getRandom());
        System.out.println(randSet.getRandom());
        System.out.println(randSet.getRandom());
        System.out.println(randSet.getRandom());
        System.out.println(randSet.getRandom());
        // 
        // randSet.removeRandom();
        randSet.remove("Henry");
        System.out.println("");
        System.out.println(randSet.getRandom());
        System.out.println(randSet.getRandom());
        System.out.println(randSet.getRandom());
        System.out.println(randSet.getRandom());
        System.out.println(randSet.getRandom());
        System.out.println(randSet.getRandom());
        System.out.println(randSet.getRandom());
        System.out.println(randSet.getRandom());
        System.out.println(randSet.getRandom());
        System.out.println(randSet.getRandom());
        System.out.println(randSet.getRandom());
        System.out.println(randSet.getRandom());
        System.out.println(randSet.getRandom());
        System.out.println(randSet.getRandom());
        System.out.println(randSet.getRandom());
    }
}