import java.util.*;

class Solution {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshOranges = 0;
        
        // 1. Initial scan: count fresh and enqueue rotten
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    freshOranges++;
                }
            }
        }
        
        // If there are no fresh oranges to begin with, 0 minutes have passed
        if (freshOranges == 0) return 0;
        
        int minutes = 0;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        
        // 2. BFS traversal
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean rottedThisMinute = false;
            
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                
                for (int[] dir : directions) {
                    int nextR = curr[0] + dir[0];
                    int nextC = curr[1] + dir[1];
                    
                    // Check bounds and if the orange is fresh
                    if (nextR >= 0 && nextR < rows && nextC >= 0 && nextC < cols && grid[nextR][nextC] == 1) {
                        grid[nextR][nextC] = 2; // Make it rotten
                        queue.offer(new int[]{nextR, nextC});
                        freshOranges--;
                        rottedThisMinute = true;
                    }
                }
            }
            
            if (rottedThisMinute) minutes++;
        }
        
        // 3. Check if any fresh oranges are left unreachable
        return freshOranges == 0 ? minutes : -1;
    }
}