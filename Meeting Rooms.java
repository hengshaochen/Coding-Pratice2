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