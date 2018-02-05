// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
class Generics<T> {
    T val;
    
    Generics(T x) {
        this.val = x;
    }
    public void print() {
        System.out.println(val);
    }
    
    public void permutationII() {
    }
}
    
    public Main() {
        String[] input = {"AB", "AB", "AC"};
        //int input = 1;
        
        //Generics per = new Generics(input);
        //per.print();
        System.out.println(permutationII(input));
    }
    
    List<List<Object>> permutationII(Object nums) {
        List<List<Object>> ans = new ArrayList<>();
        if (nums == null || nums.getLength(nums) == 0) {
            return ans;
        }
        
        Arrays.sort(nums);
        
        boolean[] visited = new boolean[nums.length];
        
        dfs(new ArrayList<Object>(), ans, nums, visited);
        
        return ans;
    }
    
    public void dfs(List<Object> cur, List<List<Object>> ans, Object[] nums, boolean[] visited) {
        if (cur.size() == nums.length) {
            ans.add(new ArrayList<Object>(cur));
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1] == true) {
                    continue;
                }
                
                cur.add(nums[i]);
                visited[i] = true;
                
                dfs(cur, ans, nums, visited);
                
                cur.remove(cur.size() - 1);
                visited[i] = false;
            }
        }
    }
}