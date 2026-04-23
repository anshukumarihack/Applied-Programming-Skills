import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        int[] result = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Build the adjacency list and fill in-degree array
        for (int[] edge : prerequisites) {
            int course = edge[0];
            int pre = edge[1];
            adj.get(pre).add(course);
            inDegree[course]++;
        }
        
        // Add all courses with no prerequisites to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int index = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            result[index++] = current;
            
            // Reduce in-degree for all dependent courses
            for (int neighbor : adj.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // If index reached numCourses, we found a valid path.
        // Otherwise, there was a cycle.
        return (index == numCourses) ? result : new int[0];
    }
}