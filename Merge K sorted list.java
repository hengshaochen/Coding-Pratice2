/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, (e1, e2) -> e1.val - e2.val);
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        
        // 把所有list都放到priority queue中
        for (int i = 0; i < lists.length; i++) {
            // ** 可能會有空list在lists中.
            if (lists[i] != null) {
                pq.add(lists[i]);
            }
        }
        
        // 把當前第一個數字最小那串拿出來, 把他第一個node的連上答案, 把該串第一個node刪除後, 放回priority queue調整...一直repeat即可
        // ex: list1: 1, 2, 3 / list2: 5, 7 / list3: 9, 10 --第一回合-> list1: 2, 3 / list2: 5, 7 / list3: 9, 10
        while (!pq.isEmpty()) {
            cur.next = pq.remove();
            cur = cur.next;
            
            // ***上面例子：把1從1,2,3拿出來後, 要把2,3放回. 因為1下一個是2 並不等於null, 因此可以把2->3放回queue中
            if (cur.next != null) {
                pq.add(cur.next);
            }
        }
        return dummy.next;
    }
}