import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Build the graph: [course, pre] means pre -> course
        for (int[] edge : prerequisites) {
            int course = edge[0];
            int pre = edge[1];
            adj.get(pre).add(course);
            inDegree[course]++;
        }
        
        // Add courses with no prerequisites to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int count = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            count++;
            
            // "Finish" current course and reduce in-degree of its dependents
            for (int neighbor : adj.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // If we finished all courses, no cycle exists
        return count == numCourses;
    }
}