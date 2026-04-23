import java.util.PriorityQueue;

class Solution {
    public int[][] kClosest(int[][] points, int k) {
        // Max-Heap to keep the 'k' smallest distances at the bottom.
        // We compare (x2^2 + y2^2) with (x1^2 + y1^2).
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> {
            int dist1 = p1[0] * p1[0] + p1[1] * p1[1];
            int dist2 = p2[0] * p2[0] + p2[1] * p2[1];
            return Integer.compare(dist2, dist1); // Max-heap logic
        });

        for (int[] point : points) {
            pq.add(point);
            // If heap size exceeds k, remove the point with the largest distance
            if (pq.size() > k) {
                pq.poll();
            }
        }

        // Prepare the result array from the k points remaining in the heap
        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = pq.poll();
        }

        return result;
    }
}