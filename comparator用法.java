// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        // 目的：找到最大的2個數字, 維護一個min heap
        // 原本是1, 2, root = 1 --> 後來compare(1, 3) < 0成立 把3加入
        Comparator<Integer> cmp = new Comparator<Integer>() {
            public int compare(Integer e1, Integer e2) {
                if (e1 < e2) {
                    // 因為預設就是min heap, 就是compare函數不變
                    // 原本compare函數是e1.compareTo(e2), 回傳負數代表e1 < e2
                    // 回傳正數代表e1 > e2
                    return -1;
                }
                return 1;
            }
        };
        
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(2, cmp);
        
        pq.add(1);
        pq.add(2);
        if (pq.size() >= 2) {
            if (cmp.compare(pq.peek(), 3) < 0 ) {
                pq.poll();
                pq.add(3);
            }
        }
        
        System.out.println(pq);
    }
}