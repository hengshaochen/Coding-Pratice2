/* k select O(n): selection sort解法时间复杂度是O(n)，具体可以参考
http://www.jiuzhang.com/solution/kth-largest-element/, 就是把求kth问题，变成求前k小的所有数据
（你把kth的值找出来后，扫一遍所有的数据，小于等于kth的值的，就属于topk的）
*/
/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */


public class Solution {
    /*
     * @param points: a list of points
     * @param origin: a point
     * @param k: An integer
     * @return: the k closest points
     */
    public Point[] kClosest(Point[] points, Point origin, int k) {
        Point k_small = quickSelect(points, origin, 0, points.length - 1, points.length - k + 1);
        
        Point[] ans = new Point[k];
        
        int cnt = 0;
        for (Point cur : points) {
            if ( getDistance(cur, origin) <= getDistance(k_small, origin)) {
                ans[cnt++] = cur;
            }
        }
        
        
        
        return ans;
    }

    private Point quickSelect(Point[] nums, Point origin, int start, int end, int k) {
        int i = start, j = end;
        Point pivot = nums[(start + end) / 2];
        
        while (i <= j) {
            while (i <= j && getDistance(nums[i], origin) > getDistance(pivot, origin)) {
                i++;
            }
            while (i <= j && getDistance(nums[j], origin) < getDistance(pivot, origin)) {
                j--;
            }
            
            if (i <= j) {
                Point temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                
                i++;
                j--;
            }
        }
        
        // 往左
        if (start + k - 1 <= j) {
            return quickSelect(nums, origin, start, j, k);
        }


        // 往右
        if (start + k - 1 >= i) {
            return quickSelect(nums, origin, i, end, k - (i - start));
        }
        
        // 剛好在j i之間
        return nums[j + 1];
    }
    
    private int getDistance(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }
}


// 維護MAX HEAP, O(nlogk)

/**
Definition for a point.
class Point {
int x;
int y;
Point() { x = 0; y = 0; }
Point(int a, int b) { x = a; y = b; }
}
*/
public class Solution {
/*
* @param points: a list of points
* @param origin: a point
* @param k: An integer
* @return: the k closest points
*/
    public Point[] kClosest(Point[] points, Point origin, int k) {
        Point[] ans = new Point[k];
        if (points.length == 0) {
            return ans;
        }
        
        Comparator<Point> cmp = new Comparator<Point>() {
            public int compare(Point e1, Point e2) {
                int diff1 = (e1.x - origin.x) * (e1.x - origin.x) + (e1.y - origin.y) * (e1.y - origin.y);
                int diff2 = (e2.x - origin.x) * (e2.x - origin.x) + (e2.y - origin.y) * (e2.y - origin.y);
                if (diff1 - diff2 < 0) {
                    return 1;
                } else if (diff1 - diff2 == 0) {
                    if (e1.x - e2.x < 0) {
                        return -1;
                    } else if (e1.x - e2.x == 0 && e1.y - e2.y < 0) {
                        return -1;
                    }
                }
                return -1;
            }
        };
        
        PriorityQueue<Point> pq = new PriorityQueue<Point>(k, cmp);
        
        for (Point cur : points) {
            if (pq.size() < k) {
                pq.add(cur);
            } else {
                if (cmp.compare(pq.peek(), cur) < 0) {
                    pq.remove();
                    pq.add(cur);
                }
            }
        }
        
        for (int i = k - 1; i >= 0; i--) {
            ans[i] = pq.remove();
        }
        
        return ans;
    
    }
    private int getDistance(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }
}

// 暴力法 用排序的O(nlogn)
/**
Definition for a point.
class Point {
int x;
int y;
Point() { x = 0; y = 0; }
Point(int a, int b) { x = a; y = b; }
}
*/
public class Solution {
/*
* @param points: a list of points
* @param origin: a point
* @param k: An integer
* @return: the k closest points
*/
    public Point[] kClosest(Point[] points, Point origin, int k) {
    // 法2:用max_heap固定heap size為k 每次抓最大的出來跟挑戰者比較
        Comparator<Point> cmp;
    	cmp = new Comparator<Point>() {
    	    public int compare(Point e1, Point e2) {
    	        int diff = getDistance(e2, origin) - getDistance(e1, origin);
    	        
    	        if (diff == 0) {
    	            diff = e2.x - e1.x;
    	        }
    	        if (diff == 0) {
    	            diff = e2.y - e1.y;
    	        }
    	        
    	        return diff;
    	      }
    	 };
        Queue<Point> max_heap = new PriorityQueue<>(k, cmp);
        
        for (int i = 0; i < points.length; i++) {
            if (max_heap.size() < k) {
                max_heap.add(points[i]);
            } else if (max_heap.size() == k) {
                Point cur = max_heap.poll();
    	        int cur_dis = getDistance(cur, origin);
    	        int new_dis = getDistance(points[i], origin);
    	        
                if (new_dis < cur_dis) {
                    max_heap.add(points[i]);
                } else {
                    max_heap.add(cur);
                }
            }
        }
        
        Point[] ans = new Point[k];
        for (int i = k - 1; i >= 0; i--) {
            ans[i] = max_heap.poll();
        }
        
        return ans;
    }
    private int getDistance(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }
}