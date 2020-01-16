package accumulate.interviews.array.test;

import java.util.HashSet;

public class LongestConsecutiveSequenceTest {

    public static void main(String[] args) {
        System.out.println(longestConsecutiveSequence(new int[]{0, 1, 2, 3, 10}));
    }

    public static int longestConsecutiveSequence(int[] nums){
        if (nums == null || nums.length == 0)
            return 0;

        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int maxLength = 1;

        for (int n : set) {
            if (!set.contains(n - 1)) {
                int currentMaxLength = 1;
                while (set.contains(n + 1)) {
                    currentMaxLength++;
                    n++;
                }
                maxLength = Math.max(maxLength, currentMaxLength);
            }
        }

        return maxLength;
    }
}
