// 1. Fib number
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        int n = 10;
        
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        System.out.println(dp[n]);
    }
}

// 優化空間
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        int n = 10;
        
        int prepre = 0;
        int pre = 1;
        int cur = 0;
        for (int i = 2; i <= n; i++) {
            cur = prepre + pre;
            prepre = pre;
            pre = cur;
        }
        System.out.println(cur);
    }
}

/////////////////////////////////
// CSS
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        //String input = "#F00";
        String input = "#FE0101";
        
        // 明天要加上一些corner case
        if (input.length() != 4 && input.length() != 7 || input.charAt(0) != '#') {
            // exception
            throw new NumberFormatException("Error / Invalid number.");
        }
        
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i <= 9; i++) {
            map.put( (char)(i + '0') , i);
        }
        for (int i = 0; i <= 5; i++) {
            map.put( (char)('A' + i) , i + 10);
        }
        
        // transfer all digit to upper case
        input = input.toUpperCase();
        
        // corner case, exist invalid character
        for (int i = 1; i < input.length(); i++) {
            if (!map.containsKey(input.charAt(i))) {
                throw new NumberFormatException("Error / Invalid number.");
            }
        }
        
        // transfer 4 digits to 6 digits
        if (input.length() == 4) {
            StringBuilder sb = new StringBuilder();
            sb.append('#');
            sb.append(input.charAt(1));
            sb.append(input.charAt(1));
            sb.append(input.charAt(2));
            sb.append(input.charAt(2));
            sb.append(input.charAt(3));
            sb.append(input.charAt(3));
            input = sb.toString();
        }
        
        long r = (map.get(input.charAt(1))) * 16 + (map.get(input.charAt(2)));
        long g = (map.get(input.charAt(3))) * 16 + (map.get(input.charAt(4)));
        long b = (map.get(input.charAt(5))) * 16 + (map.get(input.charAt(6)));
        
        long ans = r + (long)Math.pow(16, 2) * g + (long)Math.pow(16, 4) * b;
        
        
        System.out.println(ans);
    }
}

// String to long
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        
    }
    
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            throw new NumberFormatException("Error / Invalid number.");
        }
        
        // 去除leading空格
        str = str.trim();
        
        // 預設sign為正, 因為如果不帶正負號就直接是正, 判斷正負號
        int sign = 1;
        char firstChar = str.charAt(0);
        int start = 0, len = str.length();
        long sum = 0;
        
        // 判斷正負號： +35, -35 也有可能是35不帶正負號
        if (firstChar == '+') {
            sign = 1;
            start++;
        } else if (firstChar == '-') {
            sign = -1;
            start++;
        }
        
        for (int i = start; i < len; i++) {
            // 若遇到特殊符號則直接return目前算到的值, 例如35a66 --> ans: 35
            if (!Character.isDigit(str.charAt(i))) {
                throw new NumberFormatException("Error / Invalid number.");
            }
            
            // 正常計算
            sum = sum * 10 + str.charAt(i) - '0';
            
            // 處理overflow
            if (sign == 1 && sum > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign == -1 && (-1) * sum < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }

        return (int) sum * sign;
    }
}

// string to long
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        System.out.println(myAtoi("-131"));
    }
    
    public long myAtoi(String str) {
        if (str == null || str.length() == 0) {
            throw new NumberFormatException("Error / Invalid number.");
        }
        
        // 預設sign為正, 因為如果不帶正負號就直接是正, 判斷正負號
        int sign = 1;
        char firstChar = str.charAt(0);
        int start = 0, len = str.length();
        long sum = 0;
        
        // 判斷正負號： +35, -35 也有可能是35不帶正負號
        if (firstChar == '+') {
            sign = 1;
            start++;
        } else if (firstChar == '-') {
            sign = -1;
            start++;
        }
        
        for (int i = start; i < len; i++) {
            // 若遇到特殊符號則直接return目前算到的值, 例如35a66 --> ans: 35
            if (!Character.isDigit(str.charAt(i))) {
                throw new NumberFormatException("Error / Invalid number.");
            }
            
            // handle overflow
            if (i == len - 1 && sum == Long.MAX_VALUE / 10) {
                if (sign == 1 && str.charAt(i) > '7') {
                    throw new ArithmeticException("long overflow.");
                } else if (sign == -1 && str.charAt(i) > '8') {
                    throw new ArithmeticException("long overflow.");
                }
            }
            // 正常計算
            sum = sum * 10 + str.charAt(i) - '0';
            
        }

        return sum * sign;
    }
}

// thr-tree
class Node{
        Node left;
        Node right;
        Node middle;
        int val;
        Node(int val){
                this.val = val;
        }
}

public class TrinaryTree {        
        Node root;
        public TrinaryTree(){
                this.root = null;
        }
        public TrinaryTree(Node root){
                this.root = root;
        }
        
        //insert a value to the appropriate position in the tree
        public void insert(int val){
                if (root != null){
                        root = insert(root, val);
                }else{
                        root = new Node(val);
                }
        }
        
        public Node insert(Node node, int val){
                if (node == null){
                        node = new Node(val);
                }
                else if(val < node.val){
                        node.left = insert(node.left, val);
                }
                else if(val == node.val){
                        node.middle = insert(node.middle, val);
                }
                else{
                        node.right = insert(node.right, val);
                }
                return node;
        }
        
        //delete a value from the tree
        public void delete(int val){
                root = delete(root, val);
        }
        public Node delete(Node node, int val){
                if (node == null){
                    System.out.println("The node "+val+ " doesn't exist");
                    return null;
                }
                else if (val < node.val){
                        node.left = delete(node.left, val);
                }
                else if (val > node.val){
                        node.right = delete(node.right, val);
                }
                else{
                        if (node.middle != null){
                                node.middle = delete(node.middle, val);
                        }
                        else if (node.right != null){
                                node.val = getMin(node.right).val;
                                System.out.println( node.val);
                                //node = delete(node.right, getMin(node.right).val);
                                node.right = delete(node.right, getMin(node.right).val);
                        }
                        else{
                                node = node.left;
                        }                        
                }
                return node;
        }
        // find min as helper function to delete
        private Node getMin(Node node) {
                if (node != null){
                        while(node.left != null){
                                node = node.left;
                        }
                }
                return node;
        }
        // pre-order traverse the tree and print the value        
        public void print(Node root){
                if (root == null)        return;
                if (root != null){
                        System.out.println("Node value: "+ root.val);
                        print(root.left);
                        print(root.middle);
                        print(root.right);
                }
        }
        public void print() {
                print(root);
        }
        public static void main(String[] args) {
        TrinaryTree tree = new TrinaryTree();
                tree.insert(5);
                tree.insert(4);
                tree.insert(9);
                tree.insert(5);
                tree.insert(7);
                tree.insert(2);
                tree.insert(2);
                tree.insert(99);
                //preorder traversal, the sequence should be 5422597. 1point 3acres 璁哄潧
                System.out.println("Preorder traversal: ");
                tree.print();
                tree.delete(4);
                // the sequence should be 542297
                System.out.println("After delete 5:"); 
                tree.print();
                // delete a node not exited
                tree.delete(10);
                System.out.println("After delete 10:");
        }
}

public Node Delete(Node node, int value). 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
  {
        if (node.value > value)
        {
               node.left = Delete(node.left, value);
        }
        else if(node.value < value)
       {
              node.right = Delete(node.right, value);
       }
       else
      {
            if (node.middle != null). 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
           {
                  node.middle = Delete(node.middle, value);
           }
           else if(node.right != null)
           {
                   int min = minimum(node.right).value;
                   node.value = min;
                   node.right = Delete(node.right, min);
           }
           else. 1point 3acres 璁哄潧
           {
                 node = node.left;
           }
      }
      return node;
  }