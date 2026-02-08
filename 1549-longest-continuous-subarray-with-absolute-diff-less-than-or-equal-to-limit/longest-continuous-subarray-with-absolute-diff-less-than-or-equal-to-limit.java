import java.util.*;

class Solution {
    public int longestSubarray(int[] nums, int limit) {

        Deque<Integer> maxD = new ArrayDeque<>();
        Deque<Integer> minD = new ArrayDeque<>();

        int left = 0, ans = 0;

        for (int right = 0; right < nums.length; right++) {

            // maintain max deque
            while (!maxD.isEmpty() && maxD.peekLast() < nums[right]) {
                maxD.pollLast();
            }
            maxD.offerLast(nums[right]);

            // maintain min deque
            while (!minD.isEmpty() && minD.peekLast() > nums[right]) {
                minD.pollLast();
            }
            minD.offerLast(nums[right]);

            // shrink window if invalid
            while (maxD.peekFirst() - minD.peekFirst() > limit) {
                if (maxD.peekFirst() == nums[left]) maxD.pollFirst();
                if (minD.peekFirst() == nums[left]) minD.pollFirst();
                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}
