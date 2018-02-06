// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    public Main() {
        // merge two sorted array, without DUPLICATE
        // arr1: [2,3,3,6,8]
        // arr2: [3,3,3,8,11,13]
        // output: [3,8]
        
        int[] arr1 = {2, 3, 3, 6, 8};
        int[] arr2 = {3, 3, 3, 8, 11, 13};
        
        System.out.println(merge(arr1, arr2));
    }
    
    public List<Integer> merge(int[] arr1, int[] arr2) {
        List<Integer> ans = new ArrayList<>();
        
        if (arr1.length == 0 || arr2.length == 0) {
            return ans;
        }
        
        int i = 0, j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) {
                // 去重, 沒有元素 或是 確定當前的沒有在ans出現過才加入
                if (ans.size() == 0 ||
                    (ans.size() > 0 && ans.get(ans.size() - 1) != arr1[i])) {
                    ans.add(arr1[i]);
                } 
                // 不管有沒有加入i, j都要++
                i++;
                j++;
            } else if (arr1[i] < arr2[j]) {
                i++;
            } else {
                j++;
            }
        }
        
        // 只要一個list跑完就好，因為一個跑完代表就不可能再有交集
        return ans;
    }
}