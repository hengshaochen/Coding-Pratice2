class Solution {
    public String reorganizeString(String S) {
        // 思路：用Greedy, Priority Queue(Max_heap)實作.
        // 把頻率最高的放在Heap頭, 每次抽出最高的放到String, 並把他暫時移出Heap, 下次把不相鄰的字母放入後再放回去
        
        // Step1: 統計出現頻率：
        Map<Character, Integer> map = new HashMap<>();
        for (char cur : S.toCharArray()) {
            if (!map.containsKey(cur)) {
                map.put(cur, 1);
            } else {
                map.put(cur, map.get(cur) + 1);
            }
        }
        
        // Step2: 放到Queue中
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            pq.add(entry);
        }
        
        // Step3: 從Queue拿出並建立String
        Map.Entry<Character,Integer> pre = new java.util.AbstractMap.SimpleEntry<Character, Integer>('&', 0);
        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()) {
            //int[] cur = new int[2];
            Map.Entry<Character, Integer> cur = pq.remove();
            
            if (pre.getKey() == cur.getKey()) {
                return "";
            }
            
            // 把上一個加回queue
            if(pre.getValue() > 0) {
                pq.add(pre);
            }
            
            // Build String
            sb.append(cur.getKey());
            cur.setValue(cur.getValue() - 1);
            pre = cur;
        }
        if (sb.length() == S.length()) {
            return sb.toString();
        }
        return "";
    }
}