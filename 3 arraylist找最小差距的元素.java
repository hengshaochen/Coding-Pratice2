// "static void main" must be defined in a public class.
public class Main {
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    List<Integer> list3 = new ArrayList<>();
    
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
// Input: A[] = {1, 4, 10}  B[] = {2, 15, 20} C[] = {10, 12} Output: 10 15 10。 10 from A, 15 from B and 10 from C
        
        list1.add(1);
        list1.add(4);
        list1.add(10);

        list2.add(2);
        list2.add(15);
        list2.add(20);
        
        list3.add(10);
        list3.add(12);
        
        System.out.println(shortestDistance());
    }
    
    public int shortestDistance() {
        int min = Integer.MAX_VALUE;
        
        int i = 0, j = 0, k = 0;
        while (i < list1.size() && j < list2.size() && k < list3.size()) {
            int current = Math.abs(list1.get(i) - list2.get(j)) + Math.abs(list2.get(j) - list3.get(k)) + Math.abs(list3.get(k) - list1.get(i));
            
            if (current < min) {
                min = current;
            }
            
            // 移動數字最小的那個array指標
            int min_number = Math.min(Math.min(list1.get(i), list2.get(j)), list3.get(k));
            if (min_number == list1.get(i)) {
                i++;
            } else if (min_number == list2.get(j)) {
                j++;
            } else {
                k++;
            }
        }
        return min;
    }
}