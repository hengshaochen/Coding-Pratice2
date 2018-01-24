
class Solution {
    public int evalRPN(String[] tokens) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        
        for (String cur : tokens) {
            if (cur.compareTo("+") == 0 || cur.compareTo("-") == 0 ||
                cur.compareTo("*") == 0 || cur.compareTo("/") == 0) {
                int post = stack.pop();
                int pre = stack.pop();
                if (cur.compareTo("+") == 0) { ans = (pre + post); }
                if (cur.compareTo("-") == 0) { ans = (pre - post); }
                if (cur.compareTo("*") == 0) { ans = (pre * post); }
                if (cur.compareTo("/") == 0) { ans = (pre / post); }
                stack.push(ans);
            } else {
                stack.push(Integer.parseInt(cur));
                // 萬一只有一個["18"] 或是["18", "19"] 就要直接傳stack最上面那個當答案
                ans = Integer.parseInt(cur);
            }
        }
        
        return ans;
    }
}