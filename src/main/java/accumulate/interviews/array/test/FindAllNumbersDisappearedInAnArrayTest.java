package accumulate.interviews.array.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllNumbersDisappearedInAnArrayTest {

    public static void main(String[] args) {
        System.out.println(findAllNumbersDisappearedInAnArray(new int[]{1,1}));
    }

    public static List<Integer> findAllNumbersDisappearedInAnArray(int [] nums) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= nums.length; i++) {
            map.put(i, 1);
        }

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], -1);
            }
        }

        for (int i : map.keySet()) {
            if (map.get(i) != -1) {
                result.add(i);
            }
        }

        return result;
    }
}
