/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
class Solution {
    public int maxPoints(Point[] points) {
        
        int ans = 0;
        for (int i = 0; i < points.length; i++) {
            // HashMap存該斜率出現的點數, 窮舉所有點和點之間的關係找最大
            HashMap<String, Integer> map = new HashMap<>();
            // 特殊判斷：垂直, 重疊
            int overlap = 0, vertical = 0, max = 0;
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                
                // 特判斷垂直 或 重疊
                if (points[i].x == points[j].x) {
                    if (points[i].y == points[j].y) {
                        // 重疊
                        overlap++;
                    } else {
                        // 垂直
                        vertical++;
                    }
                    continue;
                }
                
                int dx = points[i].x - points[j].x;
                int dy = points[i].y - points[j].y;
                
                int g = gcd(dx, dy);
                dx = dx / g;
                dy = dy / g;
                
                String slope = dx + "/" + dy;
                
                if (!map.containsKey(slope)) {
                    map.put(slope, 1);
                } else {
                    map.put(slope, map.get(slope) + 1);
                }
                max = Math.max(max, map.get(slope));
            }
            max = Math.max(max, vertical);
            ans = Math.max(ans, max + overlap + 1);
        }
        return ans;
    }
    
    int gcd(int dx, int dy) {
        if (dy == 0) {
            return dx;
        } else {
            return gcd(dy, dx % dy);
        }
    }
}