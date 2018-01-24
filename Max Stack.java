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