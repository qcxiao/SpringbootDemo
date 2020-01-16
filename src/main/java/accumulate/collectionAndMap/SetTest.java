package accumulate.collectionAndMap;

import com.google.common.collect.Sets;

import java.util.*;

/**
 * @Author: yaodao
 * @Date: 2018/10/16 17:40
 */
public class SetTest {
    public static void main(String[] args) throws Exception {
        Set set = new HashSet();
        set.add(1);
        set.add(1);
        set.add(2);
        System.out.println(set);
        List list = new ArrayList<>();
        list.add(1);
        list.add(1);
        Object arrayObjects = list.toArray();
        System.out.println();
        Map<String, String> map = new HashMap();
        map.put("2", "3");
        map.put("3", "3");
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        int [] nums = {2, 7, 11, 15};
        int target = 9;
        SetTest solution = new SetTest();
        System.out.println(Arrays.toString(solution.twoSum(nums, target)));
    }
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        for(int i = 0; i < nums.length; i++){
            for (int j = i + 1; j < nums.length; j++){
                if(target == (nums[i] + nums[j])){
                    System.out.println(nums[i]);
                    res[0] = nums[i];
                    res[1] = nums[j];
                }
            }
        }
        return res;
    }
}
