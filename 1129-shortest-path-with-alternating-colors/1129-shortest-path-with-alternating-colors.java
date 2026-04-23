import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        // Build adjacency lists for red (0) and blue (1)
        List<Integer>[][] adj = new ArrayList[2][n];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) adj[i][j] = new ArrayList<>();
        }
        
        for (int[] edge : redEdges) adj[0][edge[0]].add(edge[1]);
        for (int[] edge : blueEdges) adj[1][edge[0]].add(edge[1]);

        // dist[node][color] stores the distance to reach node ending with color
        int[][] dist = new int[n][2];
        for (int[] row : dist) Arrays.fill(row, -1);

        Queue<int[]> queue = new LinkedList<>();
        // Start from node 0 with both colors
        // int[]: {node, color}
        dist[0][0] = 0;
        dist[0][1] = 0;
        queue.offer(new int[]{0, 0}); // Started with Red
        queue.offer(new int[]{0, 1}); // Started with Blue

        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int u = curr[0];
                int lastColor = curr[1];
                int nextColor = 1 - lastColor; // Alternate color

                for (int v : adj[nextColor][u]) {
                    if (dist[v][nextColor] == -1) {
                        dist[v][nextColor] = steps;
                        queue.offer(new int[]{v, nextColor});
                    }
                }
            }
        }

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int d1 = dist[i][0];
            int d2 = dist[i][1];
            
            if (d1 == -1 && d2 == -1) result[i] = -1;
            else if (d1 == -1) result[i] = d2;
            else if (d2 == -1) result[i] = d1;
            else result[i] = Math.min(d1, d2);
        }
        
        return result;
    }
}