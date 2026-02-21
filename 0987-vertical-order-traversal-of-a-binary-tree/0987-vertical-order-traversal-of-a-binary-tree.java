class Solution {
    class Node {
        int row, col, val;
        Node(int r, int c, int v) {
            row = r;
            col = c;
            val = v;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<Node> list = new ArrayList<>();
        Queue<Object[]> queue = new LinkedList<>();

        queue.offer(new Object[]{root, 0, 0});

        while (!queue.isEmpty()) {
            Object[] arr = queue.poll();
            TreeNode node = (TreeNode) arr[0];
            int row = (int) arr[1];
            int col = (int) arr[2];

            list.add(new Node(row, col, node.val));

            if (node.left != null)
                queue.offer(new Object[]{node.left, row + 1, col - 1});
            if (node.right != null)
                queue.offer(new Object[]{node.right, row + 1, col + 1});
        }

        Collections.sort(list, (a, b) -> {
            if (a.col != b.col) return a.col - b.col;
            if (a.row != b.row) return a.row - b.row;
            return a.val - b.val;
        });

        List<List<Integer>> result = new ArrayList<>();
        int prevCol = Integer.MIN_VALUE;

        for (Node n : list) {
            if (n.col != prevCol) {
                result.add(new ArrayList<>());
                prevCol = n.col;
            }
            result.get(result.size() - 1).add(n.val);
        }

        return result;
    }
}
