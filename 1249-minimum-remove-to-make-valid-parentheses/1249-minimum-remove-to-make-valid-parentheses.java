import java.util.*;

class Solution {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder(s);

        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '(') {
                stack.push(i);
            } else if (sb.charAt(i) == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    sb.setCharAt(i, '*'); // mark invalid ')'
                }
            }
        }

        // mark remaining '(' as invalid
        while (!stack.isEmpty()) {
            sb.setCharAt(stack.pop(), '*');
        }

        // build final string
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) != '*') {
                result.append(sb.charAt(i));
            }
        }

        return result.toString();
    }
}
