// "static void main" must be defined in a public class.
public class Main {
    class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        ListNode head1 = new ListNode(1);
        ListNode cur = head1;
        cur.next = new ListNode(2);
        cur = cur.next;
        cur.next = new ListNode(2);
        cur = cur.next;
        cur.next = new ListNode(2);
        cur = cur.next;
        cur.next = new ListNode(5);
        cur = cur.next;
        
        ListNode head2 = new ListNode(2);
        ListNode cur2 = head2;
        cur2.next = new ListNode(2);
        cur2 = cur2.next;
        cur2.next=  new ListNode(3);
        cur2 = cur2.next;
        cur2.next=  new ListNode(5);
        cur2 = cur2.next;
        
        ListNode result = union(head1, head2);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

// 1->2->2->2->5
// 2->2->3->5
// Ans: 2->5

// intersection + 去重。
// 想把ans變成1->2->2->3->5只要把那兩個打星號的while去除即可
public ListNode intersection(ListNode list1, ListNode list2) {
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;
    
    while(list1 != null && list2 != null) {
        if (list1.val == list2.val) {
            int temp = list1.val;
            
            cur.next = list1;
            cur = cur.next;
            list1 = list1.next;
            list2 = list2.next;
            
            // *
            while (list1 != null && list1.val == temp) {
                list1 =list1.next;
            }
            while (list2 != null && list2.val == temp) {
                list2 =list2.next;
            }
        } else if (list1.val < list2.val) {
            list1 = list1.next;
        } else {
            list2 = list2.next;
        }
    }
    /*
    while (list1 != null) {
        cur.next = list1;
    }
    while (list2 != null) {
        cur.next = list2;
    }
    */
    return dummy.next;
}
    
// union
// 1->2->2->2->5
// 2->2->3->5
// Ans: 1->2->3->5
// 想變成1->2->2->3->5不能直接把*號刪掉會有ＢＵＧ，直接刪會變成1->2->2->2->3->5
public ListNode union(ListNode list1, ListNode list2) {
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;
    
    while(list1 != null && list2 != null) {
        if (list1.val == list2.val) {
            int temp = list1.val;
            
            cur.next = list1;
            cur = cur.next;
            list1 = list1.next;
            list2 = list2.next;
            // *
            while (list1 != null && list1.val == temp) {
                list1 =list1.next;
            }
            while (list2 != null && list2.val == temp) {
                list2 =list2.next;
            }
            
        } else if (list1.val < list2.val) {
            cur.next = list1;
            cur = cur.next;
            list1 = list1.next;
        } else {
            cur.next = list2;
            cur = cur.next;
            list2 = list2.next;
        }
    }
    
    
    while (list1 != null) {
        cur.next = list1;
    }
    while (list2 != null) {
        cur.next = list2;
    }
    
    return dummy.next;
}

}