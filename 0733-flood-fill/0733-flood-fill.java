class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        // Store the color of the starting pixel
        int originalColor = image[sr][sc];
        
        // If the starting pixel is already the new color, return to avoid infinite loop
        if (originalColor != color) {
            dfs(image, sr, sc, originalColor, color);
        }
        
        return image;
    }

    private void dfs(int[][] image, int r, int c, int originalColor, int newColor) {
        // 1. Check boundaries
        // 2. Check if the current pixel matches the original color we want to change
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length || image[r][c] != originalColor) {
            return;
        }

        // Fill the current pixel with the new color
        image[r][c] = newColor;

        // Recursive calls for the 4 adjacent neighbors
        dfs(image, r + 1, c, originalColor, newColor);
        dfs(image, r - 1, c, originalColor, newColor);
        dfs(image, r, c + 1, originalColor, newColor);
        dfs(image, r, c - 1, originalColor, newColor);
    }
}