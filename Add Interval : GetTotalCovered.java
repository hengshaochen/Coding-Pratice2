// 插入O(n + n) 查詢O(1) ，插入時就直接維護好一個變數，存coverage。查詢時直接get這個變數即可
// "static void main" must be defined in a public class.
public class Main {

class Intervals {
    
    class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
        void set(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    // 建構子
    List<Interval> interval_list;
    int covered;
    public Intervals() {
        // 用LinkedList實作方便後續insert, delete
        interval_list = new LinkedList<>();
        covered = 0;
    }
    
    // O(n) Insert + O(n) Merge
    void addInterval(int start, int end) {
        // Insert O(n)
        // Covered 每次呼叫要重算
        covered = 0;
        
        Interval newInterval = new Interval(start, end);
        if (interval_list.size() == 0) {
            covered += (newInterval.end - newInterval.start);
            interval_list.add(newInterval);
            return;
        }
        
        for (int i = 0; i < interval_list.size(); i++) {
            if (interval_list.get(i).start >= newInterval.start) {
                interval_list.add(i, newInterval);
                break;
            }
        }
        // 插在最後一個位置[1,3][6,9] insert [10,13]
        if (interval_list.get(interval_list.size() - 1).start < newInterval.start) {
            interval_list.add(newInterval);
        }
        
        // Merge O(n)
        Interval pre = interval_list.get(0);
        int i = 1;
        while (i < interval_list.size()) {
            Interval cur = interval_list.get(i);
            if (pre.end >= cur.start) {
                // 合併[1,3][2,8][6,9] --> [1,8][6,9] , 刪除cur. pre變成[1,MAX(3,8)] = [1,8], 加入interval
                //      pre  cur
                int newEnd = Math.max(pre.end, cur.end);
                interval_list.remove(i);    // 刪除index = i這個節點 O(1)
                pre.set(pre.start, newEnd); // O(1), 只是修改特定物件的內容
            } else {
                // 不能合併再加上covered
                covered += (pre.end - pre.start);
                pre = cur;
                i++;
            }
        }
        // 補上最尾端
        covered += (pre.end - pre.start);
    }
    
    int getTotalCoveredLength() {
        // O(1)
        return covered;
    }
    
    
    
    int intervalNumber() {
        // 回傳總共有幾個interval區間, [1,3][6,7]這樣算兩個
        // O(1), 有一個size紀錄當前linked-list的長度
        return interval_list.size();
    }
    
    void printList() {
        for (Interval cur : interval_list) {
            System.out.println(cur.start + " " + cur.end);
        }
    }
    
}
    
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        Intervals intervals = new Intervals();
        intervals.addInterval(3, 6);
        intervals.addInterval(8, 9);
        intervals.addInterval(1, 5);
        // [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
        
        //System.out.println(intervals.intervalNumber());
        System.out.println("Total Covered: " + intervals.getTotalCoveredLength());
        //intervals.printList();
    }
    
    
}

// 插入O(1), 查詢 O(nlogn + n)，沒有真的合併interval, 有很多重疊，在查詢的時候再計算並去除重疊部分
// "static void main" must be defined in a public class.
public class Main {

class Intervals {
    
    class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
        void set(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    // 建構子
    List<Interval> interval_list;
    public Intervals() {
        // 用LinkedList實作方便後續insert, delete
        interval_list = new LinkedList<>();
    }
    
    // O(1)
    void addInterval(int start, int end) {
        Interval newInterval = new Interval(start, end);
        interval_list.add(newInterval);
    }
    
    // O(nlogn + n)
    int getTotalCoveredLength() {
        int ans = 0;
        if (interval_list.size() == 0) {
            return ans;
        }
        
        Comparator<Interval> cmp = new Comparator<Interval>() {
            public int compare(Interval e1, Interval e2) {
                return e1.start - e2.start;
            }
        };
        Collections.sort(interval_list, cmp);
        
        Interval pre = interval_list.get(0);
        for (int i = 1; i < interval_list.size(); i++) {
            Interval cur = interval_list.get(i);
            if (pre.end >= cur.start) {
                pre.end = Math.max(pre.end, cur.end);
            } else {
                ans += (pre.end - pre.start);
                pre = cur;
            }
        }
        // 把最尾端補上
        ans += (pre.end - pre.start);
        
        return ans;
    }
    
    void printList() {
        for (Interval cur : interval_list) {
            System.out.println(cur.start + " " + cur.end);
        }
    }
    
}
    
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        Intervals intervals = new Intervals();
        intervals.addInterval(3, 6);
        intervals.addInterval(8, 9);
        intervals.addInterval(1, 5);
        // [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
        
        System.out.println(intervals.getTotalCoveredLength());
        //intervals.printList();
    }
    
    
}