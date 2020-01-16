package accumulate.interviews.array;
//Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
//给定一个整数数组，其中1≤a[i]≤n (n =数组大小)，有些元素出现两次，有些元素出现一次
//
//Find all the elements of [1, n] inclusive that do not appear in this array.
// 找到[1,n]中不出现在该数组中的所有元素
//
//Could you do it without extra space and in O(n) runtime? You may assume the returned linked_list does not count as extra space.
//
//Example:
//
//Input:
//[4,3,2,7,8,2,3,1]
//
//Output:
//[5,6]

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class FindAllNumbersDisappearedInAnArray {
    public static void main(String[] args) {
        FindAllNumbersDisappearedInAnArray array = new FindAllNumbersDisappearedInAnArray();
        //System.out.println(array.findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1}));
        System.out.println(array.findDisappearedNumbers(new int[]{1, 1}));
    }
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 1; i <= nums.length; i++) {
            map.put(i, 1);
        }
        
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                map.put(nums[i], -1);
            }
        }
        
        for(int i : map.keySet()) {
            if(map.get(i) != -1) {
                result.add(i);
            }
        }
        
        return result;
    }
}
