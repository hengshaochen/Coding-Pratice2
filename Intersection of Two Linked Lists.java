/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // get length of headA and headB
        int lenA = getLength(headA);
        int lenB = getLength(headB);
        
        // 把A當成最較長的那一個
        if (lenA < lenB) {
            int temp = lenA;
            lenA = lenB;
            lenB = temp;
            ListNode temp_node = headA;
            headA = headB;
            headB = temp_node;
        }
        
        int diff = lenA - lenB;
        while (diff > 0) {
            headA = headA.next;
            diff--;
        }
        
        while (headA != null && headB != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }
    
    public int getLength(ListNode head) {
        if (head == null) {
            return 0;
        }
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }
    
    
}