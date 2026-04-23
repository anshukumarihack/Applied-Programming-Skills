import java.util.*;

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        Queue<int[]> queue = new LinkedList<>();
        
        // 1. Initialize result matrix and Queue
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                } else {
                    mat[i][j] = -1; // Mark unvisited land
                }
            }
        }
        
        // 2. Directions for 4-way movement
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        
        // 3. BFS traversal
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] d : dirs) {
                int ni = curr[0] + d[0];
                int nj = curr[1] + d[1];
                
                // If within bounds and is an unvisited '1'
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && mat[ni][nj] == -1) {
                    mat[ni][nj] = mat[curr[0]][curr[1]] + 1;
                    queue.offer(new int[]{ni, nj});
                }
            }
        }
        
        return mat;
    }
}