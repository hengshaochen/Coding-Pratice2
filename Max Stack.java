// 正確版本：用Priority
class MaxStack {

    /** initialize your data structure here. */
    Stack<Integer> stack;
    PriorityQueue<Integer> pq;
    public MaxStack() {
        stack = new Stack<>();
        pq = new PriorityQueue<Integer>( (Integer e1, Integer e2) -> e2 - e1 );
    }
    
    public void push(int x) {
        stack.push(x);
        pq.add(x);
    }
    
    public int pop() {
        int ans = stack.pop();
        pq.remove(ans);
        return ans;
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int peekMax() {
        return pq.peek();
    }
    
    public int popMax() {
        int max = pq.remove();
        Stack<Integer> buf = new Stack<>();
        
        // 刪除最大值必須要把不是最大值的先暫存放到另一個stack, 然後後來再擺回來
        while (!stack.isEmpty()) {
            if (stack.peek() != max) {
                buf.push(stack.pop());
            } else {
                // stack.peek() == max
                stack.pop();
                break;
            }
        }
        
        // 把暫存的東西放回原本stack
        while (!buf.isEmpty()) {
            stack.push(buf.pop());
        }
        return max;
    }
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

// 有BUG版本, 有一個很長的test case過不了 wrong answer.
// 需要自己維護三個變數max_stack_size, max_ptr, max_val, 用上面Priority Queue完全不用維護這些變數
class MaxStack {

    /** initialize your data structure here. */
    Stack<Integer> max_stack;
    int max_ptr, max_val, max_stack_size;
    public MaxStack() {
        max_stack = new Stack<>();
        max_ptr = -1;
        max_val = Integer.MIN_VALUE;
        max_stack_size = 0;
        
    }
    
    public void push(int x) {
        // maintain MAX Pointer
        if (x >= max_val) {
            max_ptr = max_stack_size;
            max_val = x;
        }
        max_stack.push(x);
        max_stack_size++;
    }
    
    public int pop() {
        if (max_stack_size - 1 == max_ptr) {
            return popMax();
        }
        max_stack_size--;
        return max_stack.pop();
    }
    
    public int top() {
        return max_stack.peek();
    }
    
    public int peekMax() {
        return max_val;
    }
    
    public int popMax() {
        // POP the MAX VALUE in STACK
        // 使用另一個暫存stack, 幫助把MAX VALUE找到後在歸位
        // pop完後, 更新新的MAX值
        int ans = 0;
        
        if (max_stack_size - 1 != max_ptr) {
            // 代表MAX VALUE不在stack的最頂
            Stack<Integer> buf = new Stack<>();
            for (int i = 0; i < max_stack_size - max_ptr - 1; i++) {
                buf.push(max_stack.pop());
            }
            ans = max_stack.pop();
            System.out.println("ans:   +" + ans);
            while (!buf.isEmpty()) {
                max_stack.push(buf.pop());
            }
        } else {
            ans = max_stack.pop();
            System.out.println("ans  else "  + ans );
        }
        max_stack_size--;
        
        // 維護新的MAX
        Stack<Integer> buf = new Stack<>();
        max_ptr = -1;
        max_val = Integer.MIN_VALUE;
        while (!max_stack.isEmpty()) {
            int cur = max_stack.pop();
            buf.push(cur);
            if (cur >= max_val) {
                max_ptr = max_stack_size - 1;
                max_val = cur;
            }
            max_stack_size--;
        }
        while (!buf.isEmpty()) {
            max_stack.push(buf.pop());
            max_stack_size++;
        }
        System.out.println("max_stack_size:" + max_stack_size + " max_val: " + max_val + "max_ptr: " + max_ptr);
        return ans;
    }
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

// 法2: 用兩個Stack, popMax是O(n)，其他都是O(1)
class MaxStack {

    /** initialize your data structure here. */
    Stack<Integer> s;
    Stack<Integer> maxStack;
    public MaxStack() {
        s = new Stack<>();
        maxStack = new Stack<>();
    }
    
    public void push(int x) {
        if (maxStack.isEmpty()) {
            maxStack.push(x);
        } else {
            // 如果maxStack的頂 > x, 還是再push頂的元素，不然就push x
            if (maxStack.peek() > x) {
                maxStack.push(maxStack.peek());
            } else {
                maxStack.push(x);
            }
        }
        s.push(x);
    }
    
    public int pop() {
        maxStack.pop();
        return s.pop();
    }
    
    public int top() {
        return s.peek();
    }
    
    public int peekMax() {
        return maxStack.peek();
    }
    
    public int popMax() {
        int max_value = peekMax();
        
        Stack<Integer> buffer = new Stack<>();
        while (top() != max_value) {
            buffer.push(pop());
        }
        pop(); // pop掉max值
        while (!buffer.isEmpty()) {
            push(buffer.pop());  // 這個push是，我上面改寫過的，會維持maxStack的push
        }
        return max_value;
    }
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

 // 法3: 要求全部操作都要O(logn)完成, 除了peek。用Double LinkedList + TreeMap
class MaxStack {
    TreeMap<Integer, List<Node>> map;
    DoubleLinkedList dll;

    public MaxStack() {
        map = new TreeMap();
        dll = new DoubleLinkedList();
    }

    public void push(int x) {
        Node node = dll.add(x);
        if(!map.containsKey(x))
            map.put(x, new ArrayList<Node>());
        map.get(x).add(node);
    }

    public int pop() {
        int val = dll.pop();
        List<Node> L = map.get(val);
        L.remove(L.size() - 1);
        if (L.isEmpty()) map.remove(val);
        return val;
    }

    public int top() {
        return dll.peek();
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        int max = peekMax();
        List<Node> L = map.get(max);
        Node node = L.remove(L.size() - 1);
        dll.unlink(node);
        if (L.isEmpty()) map.remove(max);
        return max;
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