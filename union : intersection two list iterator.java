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
        List<Integer> list1 = new LinkedList<>();
        List<Integer> list2 = new LinkedList<>();
        
        list1.add(2);
        list1.add(2);
        list1.add(2);
        list1.add(5);
        list1.add(7);
        list1.add(9);
        
        list2.add(2);
        list2.add(2);
        list2.add(3);
        list2.add(3);
        list2.add(5);
        list2.add(7);
        // System.out.println(intersection_iterator(list1, list2));
        System.out.println(union_iterator(list1, list2));
        
        
    }
    
    public List<Integer> intersection_iterator(List<Integer> list1, List<Integer> list2) {
        ListIterator iter1 = list1.listIterator();
        ListIterator iter2 = list2.listIterator();
        
        List<Integer> ans = new LinkedList<>();
        
        while (iter1.hasNext() && iter2.hasNext()) {
            int next1 = (int)iter1.next();
            int next2 = (int)iter2.next();
            //System.out.println("next:" + next1 + " " + next2);
            if (next1 == next2) {
                ans.add(next1);
                
                /*
                // 去重 若要變成[2,5,7]而不是[2,2,5,7]
                while (iter1.hasNext()) {
                    int next_next_1 = (int)iter1.next();
                    if (next_next_1 != next1) {
                        iter1.previous();
                        break;
                    }
                }
                while (iter2.hasNext()) {
                    int next_next_2 = (int)iter2.next();
                    if (next_next_2 != next2) {
                        iter2.previous();
                        break;
                    }
                }
                */
                
            } else if (next1 < next2) {
                iter2.previous();
            } else {
                iter1.previous();
            }
        }
        return ans;
    }
    
/*
int arr1[] = {2, 2, 2, 5, 7, 9};
int arr2[] = {2, 2, 3, 3, 5, 7};
ans: 2,2,2,3,3,5,7,9
*/
    public List<Integer> union_iterator(List<Integer> list1, List<Integer> list2) {
        ListIterator iter1 = list1.listIterator();
        ListIterator iter2 = list2.listIterator();
        
        List<Integer> ans = new LinkedList<>();
        
        while (iter1.hasNext() && iter2.hasNext()) {
            int next1 = (int)iter1.next();
            int next2 = (int)iter2.next();
            System.out.println("next:" + next1 + " " + next2);
            if (next1 == next2) {
                ans.add(next1);
                
                
            } else if (next1 < next2) {
                ans.add(next1);
                iter2.previous();
            } else {
                ans.add(next2);
                iter1.previous();
            }
            /*
            // 如果要去重會變成[2,3,5,7]
                while (iter1.hasNext()) {
                    int next_next_1 = (int)iter1.next();
                    if (next_next_1 != next1) {
                        iter1.previous();
                        break;
                    }
                }
                while (iter2.hasNext()) {
                    int next_next_2 = (int)iter2.next();
                    if (next_next_2 != next2) {
                        iter2.previous();
                        break;
                    }
                }
            */
        }
        
        while (iter1.hasNext()) {
            ans.add((int)iter1.next());
        }
        while (iter2.hasNext()) {
            ans.add((int)iter2.next());
        }
        
        return ans;
    }
}