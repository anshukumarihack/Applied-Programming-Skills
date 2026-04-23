import java.util.*;

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        // Start DFS from the first room
        dfs(0, rooms, visited);
        
        // Check if any room remains unvisited
        for (boolean v : visited) {
            if (!v) return false;
        }
        
        return true;
    }

    private void dfs(int room, List<List<Integer>> rooms, boolean[] visited) {
        // Mark current room as visited
        visited[room] = true;
        
        // Get all keys available in the current room
        for (int key : rooms.get(room)) {
            // If we haven't visited the room this key belongs to, go there
            if (!visited[key]) {
                dfs(key, rooms, visited);
            }
        }
    }
}