class MaxStack {

    /** initialize your data structure here. */
    DoubleLinkedList list;
    Node mid;
    int size;
    public MaxStack() {
        list = new DoubleLinkedList();
        mid = list.head;
        size = 0;
    }
    
    public void push(int x) {
        list.add(x);
        if (size % 2 == 0) {
            mid = mid.next;
        }
        size++;
    }
    
    public int pop() {
        if (size % 2 != 0) {
            mid = mid.prev;
        }
        size--;
        return list.pop();
    }
    
    public int top() {
        return list.peek();
    }
    
    public int peekMax() {
        return mid.val;
    }
    
    public int popMax() {
        
        int current_mid_value = mid.val;
        
        if (size % 2 == 0) {
            // 偶數，mid會變成往下一個元素
            mid = mid.next;
            list.unlink(mid.prev);  // 移動完指標後，刪除之前的middle
        } else {
            // 奇數，mid會變成上一個元素
            mid = mid.prev;
            list.unlink(mid.next);  // 一動完指標後，刪除之前的middle
        }
        
        size--;
        return current_mid_value;
    }
}

class DoubleLinkedList {
    Node head, tail;

    public DoubleLinkedList() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }

    public Node add(int val) {
        Node x = new Node(val);
        x.next = tail;
        x.prev = tail.prev;
        tail.prev = tail.prev.next = x; // tail.pre, tail.pre.next 兩個一次assign成x
        return x;
    }

    public int pop() {
        return unlink(tail.prev).val;
    }

    public int peek() {
        return tail.prev.val;
    }

    public Node unlink(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return node;
    }
}

class Node {
    int val;
    Node prev, next;
    public Node(int v) {val = v;}
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */