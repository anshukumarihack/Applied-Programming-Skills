import java.util.Arrays;

class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) return 0;

        // Sort by end coordinate to pick the optimal shooting point
        // Use Integer.compare to avoid overflow issues with subtraction
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int arrows = 1;
        int currentEnd = points[0][1];

        for (int i = 1; i < points.length; i++) {
            // If the current balloon starts after the previous arrow's position
            if (points[i][0] > currentEnd) {
                arrows++;
                // Update the shooting position to the end of the current balloon
                currentEnd = points[i][1];
            }
        }

        return arrows;
    }
}