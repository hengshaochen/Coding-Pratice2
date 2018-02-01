// 正確版本
class RandomizedCollection {

    /** Initialize your data structure here. */
    HashMap<Integer, List<Integer>> map;
    List<List<Integer>> list;
    public RandomizedCollection() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        // 處理map
        boolean contain = map.containsKey(val);
        if (!contain) {
            map.put(val, new ArrayList<Integer>());
        }
        map.get(val).add(list.size());
        
        // 處理arraylist
        List<Integer> cur = new ArrayList<>();
        cur.add(val);   // value1 
        cur.add(map.get(val).size() - 1);  // value2: 指向map中list的第#個位置
        list.add(cur);
        return !contain;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int delete_index = map.get(val).get(map.get(val).size() - 1);
        // int delete_index_in_map_arraylist = list.get(delete_index).get(1);
        // list中最後一個entry取代要刪除的那個entry
        List<Integer> delete = list.get(delete_index);
        list.set(delete_index, list.get(list.size() - 1));
        
        // 更新hashmap: 
        // map.get(list.get(delete_idex).get(0)) 找到要更新的那個arraylist
        // list.get(list.size() - 1).get(1) 要更新的arraylist的index位置
        // delete_index 要更新成新的的index位置

        map.get(list.get(delete_index).get(0)).set(list.get(list.size() - 1).get(1), delete_index);
        list.remove(list.size() - 1);
        map.get(delete.get(0)).remove((int)delete.get(1));
        if (map.get(delete.get(0)).size() == 0) {
            map.remove(delete.get(0));
        }
        
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size())).get(0);
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */


// DEBUG版本
class RandomizedCollection {

    /** Initialize your data structure here. */
    HashMap<Integer, List<Integer>> map;
    List<List<Integer>> list;
    public RandomizedCollection() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        // 處理map
        boolean contain = map.containsKey(val);
        if (!contain) {
            map.put(val, new ArrayList<Integer>());
        }
        map.get(val).add(list.size());
        
        // 處理arraylist
        List<Integer> cur = new ArrayList<>();
        cur.add(val);   // value1 
        cur.add(map.get(val).size() - 1);  // value2: 指向map中list的第#個位置
        list.add(cur);
        return !contain;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int delete_index = map.get(val).get(map.get(val).size() - 1);
       //  System.out.println(delete_index);
        // int delete_index_in_map_arraylist = list.get(delete_index).get(1);
        // list中最後一個entry取代要刪除的那個entry
        List<Integer> delete = list.get(delete_index);
        list.set(delete_index, list.get(list.size() - 1));
        
        // 更新hashmap: 
        // map.get(list.get(delete_idex).get(0)) 找到要更新的那個arraylist
        // list.get(list.size() - 1).get(1) 要更新的arraylist的index位置
        // delete.get(1) 要更新成新的index
        /*
        System.out.println(map.get(list.get(delete_index).get(0)));
        System.out.println(list.get(list.size() - 1).get(1));
        System.out.println(delete.get(1));
        */
        // map.get(list.get(delete_index).get(0)).set(list.get(list.size() - 1).get(1), delete.get(1));
        map.get(list.get(delete_index).get(0)).set(list.get(list.size() - 1).get(1), delete_index);
        list.remove(list.size() - 1);
        System.out.println("DD: " +  delete.get(1));
        map.get(delete.get(0)).remove((int)delete.get(1));
        System.out.println( map.get(delete.get(0)));
        if (map.get(delete.get(0)).size() == 0) {
            map.remove(delete.get(0));
        }
        
        for (Integer cur : map.keySet()) {
            System.out.println(cur + " " + map.get(cur));
        }
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size())).get(0);
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */