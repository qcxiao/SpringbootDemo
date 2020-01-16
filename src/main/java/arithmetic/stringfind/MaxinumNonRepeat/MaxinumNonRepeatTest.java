package arithmetic.stringfind.MaxinumNonRepeat;

import java.util.*;

/**
 * 查找一个字符串中最长不重复子串
 */
public class MaxinumNonRepeatTest {
    public static void main(String[] args) {
        String str = "1abababdafdasabcfdfeaba1234";
        System.out.println(find(str));
    }

    static String find(String str) {
        String [] strs = str.split("");
        Map<String, Integer> map = new HashMap<>();
        List<String> result = new ArrayList<>();
        StringBuilder maxStr = new StringBuilder();
        StringBuilder currentMaxStr = new StringBuilder();

        for (int i = 0; i < strs.length; i++) {
            for (int j = i; j < strs.length; j++) {
                if (!currentMaxStr.toString().contains(strs[j])) {
                    currentMaxStr.append(strs[j]);
                } else {
                    if (maxStr.length() <= currentMaxStr.length()){
                        maxStr = currentMaxStr;
                        map.put(currentMaxStr.toString(), currentMaxStr.length());
                        for (String key : map.keySet()) {
                            if (map.get(key) < currentMaxStr.length()) {
                                map.put(key, -1);
                            }
                        }
                    }
                    currentMaxStr = new StringBuilder();
                    break;
                }
            }

        }
        for (String key : map.keySet()) {
            if (map.get(key) != -1) {
                result.add(key);
            }
        }
        return result.toString();
    }
}
