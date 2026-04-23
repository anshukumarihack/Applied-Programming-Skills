import java.util.Stack;

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int ast : asteroids) {
            boolean destroyed = false;
            
            // Collision condition: stack top is moving right (+) and current is moving left (-)
            while (!stack.isEmpty() && ast < 0 && stack.peek() > 0) {
                if (Math.abs(ast) > stack.peek()) {
                    // Current asteroid destroys the one in the stack
                    stack.pop();
                    continue; // Keep checking against the new stack top
                } else if (Math.abs(ast) == stack.peek()) {
                    // Both destroy each other
                    stack.pop();
                    destroyed = true;
                } else {
                    // Current asteroid is destroyed
                    destroyed = true;
                }
                break;
            }
            
            if (!destroyed) {
                stack.push(ast);
            }
        }

        // Convert stack back to array
        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        
        return result;
    }
}