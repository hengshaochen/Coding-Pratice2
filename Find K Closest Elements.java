// 法1:


// 法2: 直接依據arr[i]和x的距離排序
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        Comparator<Integer> cmp = new Comparator<Integer>() {
          public int compare(Integer e1, Integer e2) {
              return Math.abs(e1 - x) - Math.abs(e2 - x);
          }  
        };
        
        List<Integer> buf = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            buf.add(arr[i]);
        }
        Collections.sort(buf, cmp);
        
        List<Integer> ans = new ArrayList<>();
        // buf中前k個一定是最接近x, 但是要小到大排序 放到ans後再sort一次
        for (int i = 0; i < k; i++) {
            ans.add(buf.get(i));
        }
        Collections.sort(ans);
        return ans;
    }
}

// 法3:
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        Comparator<Integer> cmp = new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                int diff = Math.abs(b - x) - Math.abs(a - x);
                return diff;
            }
        };
        PriorityQueue<Integer> max_heap = new PriorityQueue<>(k, cmp);
        
        // O(nlogk)
        for (int i = 0; i < arr.length; i++) {
            if (max_heap.size() < k) {
                max_heap.add(arr[i]);
            } else {
                if (cmp.compare(max_heap.peek(), arr[i]) < 0) {
                    max_heap.remove();
                    max_heap.add(arr[i]);
                }
            }
        }
        
        // O(k)
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ans.add(max_heap.remove());
        }
        
        // O(klogk)
        Collections.sort(ans);
        return ans;
        
        
        /*
     i       
[1,2,3,4,5]
2
9
    size of max_heap = 2
    max_heap: 1, 2
        
        compare "root of heap" with "arr[i]"
                 2 - 9 = -7            3 - 9 = -6
        if distance in arr[i] is close than "root of heap" --> replace
        3
    1
    */
    }
}