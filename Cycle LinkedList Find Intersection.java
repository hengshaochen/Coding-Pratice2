// package whatever; // don't place package name!

import java.io.*;

class MyCode {
  class ListNode {
    ListNode next;
    int val;
    ListNode (int val) {
      this.val = val;
    }
  }
  public static void main (String[] args) {
    new MyCode();
  }

  public MyCode() {
    ListNode head = new ListNode(1);
    ListNode cur = head;

    cur.next = new ListNode(2);
    cur = cur.next;
    
    ListNode two = cur;
    
    cur.next = new ListNode(3);
    cur = cur.next;
      
    cur.next = new ListNode(4);
    cur = cur.next;
      
    ListNode four = cur;
      
    cur.next = new ListNode(5);
    cur = cur.next;
      
    cur.next = two;


    //////////////////////////////////

    ListNode head2 = new ListNode(6);
    ListNode cur2 = head2;

    cur2.next = new ListNode(7);
    cur2 = cur2.next;
      
    cur2.next = four;
      
    /*
    for (int i = 0; i < 15; i++) {
        System.out.print(head2.val + " ");
        head2 = head2.next;
    }
    */

    findInter_maybe_cycle(head, head2);
  }

  public void findInter_maybe_cycle(ListNode head, ListNode head2) {

    // 如果有環的話就切斷，沒環等於沒做事, 然後接著找交點
    deleteCycle(head);
    deleteCycle(head2);

    ListNode intersection_start_point = getIntersectionNode(head, head2);
    if (intersection_start_point != null) {
        System.out.println("ans: " + (getIntersectionNode(head, head2)).val);
    } else {
        System.out.println("NO INTERSECTION BETWEEN TWO LINKED-LIST");
    }
  }
      public boolean deleteCycle(ListNode head) {
        if (head == null || head.next==null) {
            return false;
        }

        // 找是否有cycle
        ListNode fast, slow;
        fast = head.next;
        slow = head;
        while (fast != slow) {
            if(fast==null || fast.next==null)
                return false;
            fast = fast.next.next;
            slow = slow.next;
        } 

        // 找環的起頭: 1->2->3->back to 2   2就是我們要找的

        // 1 2 3 2'
        //       ij encounter 在2'
        // 把i放回起點, j在encounter, 然後一次走一步, 交會的就是環的起點

        // 紀錄環起點的前一個點, 也就是上面例子的3
        ListNode pre_of_start = slow;  // 就是環的尾端，環的尾端這個點下一個點就是環的起點
        while (head != slow.next) {
            head = head.next;
            slow = slow.next;
            pre_of_start = slow;
        }
        System.out.println("環的起點：" + head.val);
        System.out.println("環的尾端（用來切斷環用）：" + pre_of_start.val);
        pre_of_start.next = null;   // 斷開
        return true;
    }

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