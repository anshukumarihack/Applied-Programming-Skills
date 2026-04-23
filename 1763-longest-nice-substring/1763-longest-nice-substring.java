import java.util.HashSet;
import java.util.Set;

class Solution {
    public String longestNiceSubstring(String s) {
        if (s.length() < 2) return "";
        
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // If the counterpart (upper/lower) is missing, s[i] is a break point
            if (set.contains(Character.toLowerCase(c)) && set.contains(Character.toUpperCase(c))) {
                continue;
            }
            
            // Divide and Conquer
            String left = longestNiceSubstring(s.substring(0, i));
            String right = longestNiceSubstring(s.substring(i + 1));
            
            return left.length() >= right.length() ? left : right;
        }
        
        // If we reach here, the whole string is nice
        return s;
    }
}