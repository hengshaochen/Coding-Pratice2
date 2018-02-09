// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        List<List<Integer>> lists = new LinkedList<>();
        List<Integer> cur = new LinkedList<>();
        cur.add(1);
        cur.add(3);
        cur.add(4);
        cur.add(6);
        
        List<Integer> cur2 = new ArrayList<>();
        cur2.add(1);
        cur2.add(2);
        cur2.add(3);
        cur2.add(4);
        
        List<Integer> cur3 = new ArrayList<>();
        cur3.add(2);
        cur3.add(3);
        cur3.add(4);
        cur3.add(8);
        
        
        List<Integer> cur4 = new ArrayList<>();
        cur4.add(1);
        cur4.add(3);
        cur4.add(4);
        
        lists.add(cur);
        lists.add(cur2);
        lists.add(cur3);
        lists.add(cur4);
        
        System.out.println(intersectionK(lists, 0, lists.size() - 1));
    }
    
    public List<Integer> intersectionK(List<List<Integer>> lists, int start, int end) {
        if (start == end) {
            return lists.get(start);
        }
        
        int mid = start + (end - start) / 2;
        List<Integer> left = intersectionK(lists, start, mid);
        List<Integer> right = intersectionK(lists, mid + 1, end);
        return intersection_iterator(left, right);
        // return union_iterator(left, right);
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