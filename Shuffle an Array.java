// 最優解法：O(n)
class Solution {

    int[] nums;
    Random random;
    public Solution(int[] nums) {
        this.nums = nums;
        random = new Random();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        // by Knuth shuffle
        int[] next = nums.clone();
        for (int i = 1; i < next.length; i++) {
            int idx = random.nextInt(i + 1);
            swap(next, idx, i);
        }
        return next;
    }
    
    public void swap(int[] next, int idx, int i) {
        int temp = next[idx];
        next[idx] = next[i];
        next[i] = temp;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */



// 超時
class Solution {
    HashMap<Integer, int[]> map;
    int cur_num;
    int map_cnt;
    public Solution(int[] nums) {
        map = new HashMap<>();
        cur_num = 0;
        map_cnt = 0;
        permutation(new int[nums.length], new boolean[nums.length], 0, map, nums);
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        System.out.println("A");
        cur_num = 0;
        return map.get(0);
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        System.out.println(map_cnt);
        Random rand = new Random();
        
        // 一直重新rand直到不同
        int next = rand.nextInt(map_cnt);
        while (map_cnt != 1 && next == cur_num) {
            next = rand.nextInt(map_cnt);
        }
        cur_num = next;
        
        return map.get(cur_num);
    }
    
    public void permutation(int[] cur, boolean[] visited, int size, HashMap<Integer, int[]> map, int[] nums) {
        // base case
        System.out.println(size + " " + cur.length);
        if (size == cur.length) {
            // ADD ANS TO MAP
            // deep copy
            int[] buf = new int[cur.length];
            for (int i = 0; i < buf.length; i++) {
                buf[i] = cur[i];
            }
            
            map.put(map_cnt++, buf);
            return;
            
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                cur[size++] = nums[i];
                visited[i] = true;
                
                permutation(cur, visited, size, map, nums);
                
                cur[--size] = 0;
                visited[i] = false;
            }
        }
        
        
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */