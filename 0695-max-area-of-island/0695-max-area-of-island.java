class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // If we find land, calculate the area of that island
                if (grid[r][c] == 1) {
                    maxArea = Math.max(maxArea, dfs(grid, r, c));
                }
            }
        }

        return maxArea;
    }

    private int dfs(int[][] grid, int r, int c) {
        // Base case: Check boundaries and if the cell is water (0)
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0) {
            return 0;
        }

        // Mark as visited by sinking the island (setting it to 0)
        grid[r][c] = 0;

        // Sum current cell (1) + all connected land cells
        return 1 + dfs(grid, r - 1, c) + 
                   dfs(grid, r + 1, c) + 
                   dfs(grid, r, c - 1) + 
                   dfs(grid, r, c + 1);
    }
}