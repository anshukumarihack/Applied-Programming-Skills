import java.util.*;

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap<>();
        Map<String, List<String>> adj = new HashMap<>();

        // 1. Build the graph and map emails to names
        for (List<String> account : accounts) {
            String name = account.get(0);
            String firstEmail = account.get(1);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                emailToName.put(email, name);
                
                // Create undirected edges between the first email and others
                adj.computeIfAbsent(firstEmail, k -> new ArrayList<>()).add(email);
                adj.computeIfAbsent(email, k -> new ArrayList<>()).add(firstEmail);
            }
        }

        // 2. Traverse the graph using DFS to find connected components
        List<List<String>> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        for (String email : emailToName.keySet()) {
            if (!visited.contains(email)) {
                List<String> component = new ArrayList<>();
                dfs(email, adj, visited, component);
                
                // 3. Sort emails and add the name at the beginning
                Collections.sort(component);
                component.add(0, emailToName.get(email));
                result.add(component);
            }
        }

        return result;
    }

    private void dfs(String curr, Map<String, List<String>> adj, Set<String> visited, List<String> component) {
        visited.add(curr);
        component.add(curr);
        
        if (adj.containsKey(curr)) {
            for (String neighbor : adj.get(curr)) {
                if (!visited.contains(neighbor)) {
                    dfs(neighbor, adj, visited, component);
                }
            }
        }
    }
}