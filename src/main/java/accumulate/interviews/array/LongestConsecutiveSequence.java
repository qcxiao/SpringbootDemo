package accumulate.interviews.array;
// Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

// For example,
// Given [100, 4, 200, 1, 3, 2],
// The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

// Your algorithm should run in O(n) complexity.

import java.util.HashSet;
import java.util.Set;

class LongestConsecutiveSequence {

    public static void main(String[] args) {
        LongestConsecutiveSequence lcs = new LongestConsecutiveSequence();
        //System.out.println(lcs.longestConsecutive(new int[]{100, 40, 200, 1, 3, 2, 5, 2, 4}));
        System.out.println(lcs.longestConsecutive(new int[]{100, 4, 1, 3}));
        // System.out.println(lcs.longestConsecutive(new int[]{100, 40, 200}));
    }
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        Set<Integer> set = new HashSet<>();
        for(int n: nums) {
            set.add(n);
        }
        
        int maxLength = 0;
        for(int n : set) {
            // 当前数减1并且不在集合内，进入判断
            if(!set.contains(n - 1)) {
                int currentMaxLength = 1;
                while(set.contains(n + 1)) {
                    currentMaxLength++;
                    n++;
                }
                // 每次得到的maxLength和currentMaxLength里取最大值并赋值给外层变量
                // maxLength = Math.max(maxLength, currentMaxLength);
                maxLength = maxLength > currentMaxLength ? maxLength : currentMaxLength;
            }
        }
        return maxLength;
    }
}
