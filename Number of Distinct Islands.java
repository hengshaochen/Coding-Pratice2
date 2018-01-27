class Solution {
    int ans = 0;
    public int numDistinctIslands(int[][] grid) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    bfs(grid, i, j, set);
                }
            }
        }
        return ans;
    }
    
    public void bfs(int[][] grid, int x, int y, HashSet<String> set) {
        Queue<int[]> q = new LinkedList<>();
        int[] start = {x, y};
        q.add(start);
        
        // right, down, left, up
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int qsize = q.size();
            for (int i = 0; i < qsize; i++) {
                int[] cur = q.remove();
                sb.append( (cur[0] - start[0]) + "," + (cur[1] - start[1]) + " ");
                grid[x][y] = 0;
                
                for (int j = 0; j < 4; j++) {
                    int nextX = cur[0] + dx[j];
                    int nextY = cur[1] + dy[j];
                    if (outBound(nextX, nextY, grid)) {
                        continue;
                    }
                    
                    if (grid[nextX][nextY] == 1) {
                        q.add(new int[]{nextX, nextY});
                        grid[nextX][nextY] = 0;
                    }
                }
            }
        }
        
        //System.out.println(sb);
        if (!set.contains(sb.toString())) {
            set.add(sb.toString());
            ans++;
        }
    }
    
    public boolean outBound(int x, int y, int[][] grid) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return true;
        }
        return false;
    }
    
    
}