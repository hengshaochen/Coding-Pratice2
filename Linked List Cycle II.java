// Determine Cycle or not
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @param head: The first node of linked list.
     * @return: True if it has a cycle, or false
     */
    public boolean hasCycle(ListNode head) {  
        if (head == null) { return false; }
        ListNode fast = head.next;
        ListNode slow = head;
        
        while (fast != slow) {
            // 若任一指標都指向null --> 沒cycle
            // 要加fast.next是因為避免outOfBoundary,
            // 若fast.next為null, fast.next.next會outOfBoundary
            if (fast == null || fast.next == null) {
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        // 若fast == true, 代表快慢指標相遇 --> 有cycle
        return true;
    }
}

// find the start point of cycle
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next==null) {
            return null;
        }

        ListNode fast, slow;
        fast = head.next;
        slow = head;
        while (fast != slow) {
            if(fast==null || fast.next==null)
                return null;
            fast = fast.next.next;
            slow = slow.next;
        } 
        
        // 找環的起頭: 1->2->3->back to 2   2就是我們要找的
        
        // 1 2 3 2'
        //       ij encounter 在2'
        // 把i放回起點, j在encounter, 然後一次走一步, 交會的就是環的起點
        while (head != slow.next) {
            head = head.next;
            slow = slow.next;
        }
        return head;
    }
}