// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }

class Permutation<T> {
    class NaturalComparator<T extends Comparable<T>> implements Comparator<T> {
        public int compare(T a, T b) {
            return a.compareTo(b);
        }
    }
    
    List<T> list;
    public Permutation() {
        list = new ArrayList<>();
    }
    public Permutation(List<T> x) {
        list = new ArrayList(x);
    }
    
    
    public void add(T x) {
        list.add(x);
    }
    
    public void printList() {
        System.out.println(list);
    }
    
    public List<List<T>> doPermutation() {
        boolean[] visited = new boolean[list.size()];
        
        Collections.sort(list, new NaturalComparator());
        
        
        List<List<T>> ans = new ArrayList<>();
        helper(new ArrayList<>(), visited, list, ans);
        
        return ans;
    }
    
    public void helper(ArrayList<T> cur, boolean[] visited, List<T> list, List<List<T>> ans) {
        // Recursive Exit, base case
        if (cur.size() == list.size()) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        
        for (int i = 0; i < list.size(); i++) {
            if (visited[i]) {
                continue;
            }
            
            // remove duplicate, 只能前一個使用過，才能使用
            // 例如[1,1',2]，不能不使用1，卻使用1'
            // 但使用了1, 往深處跑跑到1', 這時候可以使用1'。因為1'的前一個有使用
            if (i > 0 && list.get(i) == list.get(i - 1) &&
                visited[i - 1] == false) {
                continue;
            }
            
            cur.add(list.get(i));
            visited[i] = true;
            
            helper(cur, visited, list, ans);
            
            cur.remove(cur.size() - 1);
            visited[i] = false;
        }
    }
    
}

    
    public Main() {
        //String[] input = {"AB", "AB", "AC"};
        //int input = 1;
        List<String> input = new ArrayList<>();
        input.add("a");
        input.add("d");
        input.add("a");
        
        Permutation p = new Permutation(input);
        p.printList();
        System.out.println(p.doPermutation());
        
        //Generics per = new Generics(input);
        //per.print();
        //System.out.println(permutationII(input));
    }
    
    
}