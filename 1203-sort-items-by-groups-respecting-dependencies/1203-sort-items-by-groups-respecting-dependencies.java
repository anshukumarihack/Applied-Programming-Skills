import java.util.*;

class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        // 1. Assign unique groups to items that belong to no group (-1)
        int groupCount = m;
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = groupCount++;
            }
        }

        // 2. Initialize graphs and in-degrees
        List<List<Integer>> itemAdj = new ArrayList<>();
        List<List<Integer>> groupAdj = new ArrayList<>();
        for (int i = 0; i < n; i++) itemAdj.add(new ArrayList<>());
        for (int i = 0; i < groupCount; i++) groupAdj.add(new ArrayList<>());

        int[] itemInDegree = new int[n];
        int[] groupInDegree = new int[groupCount];

        // 3. Build the graphs
        for (int i = 0; i < n; i++) {
            for (int prev : beforeItems.get(i)) {
                itemAdj.get(prev).add(i);
                itemInDegree[i]++;
                
                if (group[prev] != group[i]) {
                    groupAdj.get(group[prev]).add(group[i]);
                    groupInDegree[group[i]]++;
                }
            }
        }

        // 4. Perform Topological Sort on items and groups
        List<Integer> itemOrder = topoSort(itemAdj, itemInDegree, n);
        List<Integer> groupOrder = topoSort(groupAdj, groupInDegree, groupCount);

        if (itemOrder.isEmpty() || groupOrder.isEmpty()) return new int[0];

        // 5. Group the sorted items
        Map<Integer, List<Integer>> groupToItems = new HashMap<>();
        for (int item : itemOrder) {
            groupToItems.computeIfAbsent(group[item], k -> new ArrayList<>()).add(item);
        }

        // 6. Concatenate items based on the sorted group order
        int[] result = new int[n];
        int idx = 0;
        for (int groupId : groupOrder) {
            List<Integer> items = groupToItems.getOrDefault(groupId, new ArrayList<>());
            for (int item : items) {
                result[idx++] = item;
            }
        }

        return result;
    }

    private List<Integer> topoSort(List<List<Integer>> adj, int[] inDegree, int count) {
        List<Integer> order = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            order.add(curr);
            for (int next : adj.get(curr)) {
                inDegree[next]--;
                if (inDegree[next] == 0) queue.offer(next);
            }
        }
        return order.size() == count ? order : new ArrayList<>();
    }
}