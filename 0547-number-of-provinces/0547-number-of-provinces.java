class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;

        for (int i = 0; i < n; i++) {
            // If the city hasn't been visited, it's the start of a new province
            if (!visited[i]) {
                provinces++;
                dfs(isConnected, visited, i);
            }
        }

        return provinces;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int i) {
        // Mark the current city as visited
        visited[i] = true;

        // Check all potential neighbors
        for (int j = 0; j < isConnected.length; j++) {
            // If city 'i' is connected to city 'j' and 'j' hasn't been visited
            if (isConnected[i][j] == 1 && !visited[j]) {
                dfs(isConnected, visited, j);
            }
        }
    }
}