// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        String s = "abdeeeeffffggh";
        char target = 'b';
        
        int start = 0;
        int end = s.length() - 1;
        
        // 我忽视了c比s末尾字符还大的情况
        if (target > s.charAt(s.length() - 1)) {
            System.out.println("NOT FIND!");
        }
        
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (s.charAt(mid) == target) {
                // 相等繼續往後找有沒有嚴格大的
                start = mid;
            } else if (s.charAt(mid) < target) {
                // 當前的比要找的小, 往右找
                start = mid;
            } else {
                // 當前的比要找的大, 往左找
                end = mid;
            }
        }
        
        if (s.charAt(start) != target) {
            System.out.println(start);
        } else if (s.charAt(end) != target) {
            System.out.println(end);
        } else {
            System.out.println("NOT FIND!");
        }
    }
}