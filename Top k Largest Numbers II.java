public class Solution {
    /*
    * @param k: An integer
    */
    PriorityQueue<Integer> pq;
    int k;
    Comparator<Integer> cmp;
    public Solution(int k) {
        cmp = new Comparator<Integer>() {
        public int compare(Integer e1, Integer e2) {
            if (e1 < e2) {
                return -1;
            }
            return 1;
        }
        };
        
        pq = new PriorityQueue<Integer>(k, cmp);
        this.k = k;
    }
    

    /*
     * @param num: Number to be added
     * @return: nothing
     */
    public void add(int num) {
        if (pq.size() < k) {
            pq.add(num);
        } else {
            if (cmp.compare(pq.peek(), num) < 0) {
                pq.poll();
                pq.add(num);
            }
        }
    }

    /*
     * @return: Top k element
     */
    public List<Integer> topk() {
        Iterator it = pq.iterator();
        List<Integer> ans = new ArrayList<>();
        // iterator不會保證照priorityqueue的順序走訪
        while (it.hasNext()) {
            ans.add((Integer)it.next());
        }
        Collections.sort(ans, Collections.reverseOrder());
        return ans;
    }
}