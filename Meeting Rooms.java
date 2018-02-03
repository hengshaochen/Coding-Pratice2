/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
        // Corner case: 如果intervals為空
        // 思路：排序，如果可以Merge Interval就是false
        if (intervals == null || intervals.length == 0) {
            return true;
        }
        Comparator<Interval> cmp = new Comparator<Interval>() {
            public int compare(Interval e1, Interval e2) {
                if (e1.start < e2.start) {
                    return -1;
                } else if (e1.start == e2.start) {
                    return 0;
                } else {
                    return 1;
                }
            }
        };
        Arrays.sort(intervals, cmp);
        
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i].end > intervals[i + 1].start) {
                return false;
            }
        }
        return true;
    }
}

// Meeting Rooms II
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        // Corner case: 如果intervals為空
        // 思路：如果可以合併，meeting room count++, 然後當前的end延伸下去
        // [0,30] [5,10] -> [0,30] , [15,20]
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Comparator<Interval> cmp = new Comparator<Interval>() {
            public int compare(Interval e1, Interval e2) {
                if (e1.start < e2.start) {
                    return -1;
                } else if (e1.start == e2.start) {
                    return 0;
                } else {
                    return 1;
                }
            }
        };
        Arrays.sort(intervals, cmp);
        Interval cur = intervals[0];
        int ans = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (cur.end > intervals[i].start) {
                ans++;
                //[[0, 30],[5, 10],[15, 20] [21, 40]]
                //           cur
                //cur.end = Math.max(cur.end, intervals[i].end);
            }
        }
        return ans;
    }
}

// Meeting Room II
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        
        // 思路：掃描線算法(Sweep Line)
        // 作法：先把start排序，end排序。接著i控制start, j控制end. 當starts[i] < ends[j] 代表需要額外房間, ans++, i++
        // 當starts[i] >= ends[j] 代表此處的start不需要額外房間，因為已經有一個end結束了，因此i++, 且end++
        // 最後回傳ans
        
        //          0 .... 5 .... 10 .... 15 ......... 20 ......... 30
        // start:   |      |               |
        // end  :                  |                    |            |
        // 追蹤程式：當start = 0需要一個房間 ans++ = 1, start = 5還需要一個房間 ans++ = 2, 這時i指向15, j指向10, 這時15 >= 10, 代表start
        // 不需要額外房間，因為已經有一個會議結束在10的時候，因此j++, i++。這時i已經超過intervals.length，代表沒有其他會議。因此答案是2
        
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        
        Arrays.sort(starts);
        Arrays.sort(ends);
        
        int ans = 0, i = 0, j = 0;
        
        // i每輪不管怎樣都會向右移動一格
        while (i < intervals.length) {
            if (starts[i] < ends[j]) {
                // 代表當前開啟新的會議，但在這個時間上沒有會議結束，因此要開新房間，例如0秒跟5秒。
                // 代表需要會議房間
                ans++;
                i++;
            } else {
                // starts[i] >= ends[j]
                // 代表當前新開啟的會議 例如在15秒，之前有一個會議在10秒結束了。因此15秒的會議可以跑去10秒的會議。
                // 因此不需要額外會議房間。
                j++;
                i++;
            }
        }
        return ans;
    }
}