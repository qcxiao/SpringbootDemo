package arithmetic.stringfind.locationfind;

/**
 * 字符串位置查找
 */
public class SimpleFind {
    public static void main(String[] args) {
        System.out.println(simpleFind("abc1abababdafdasabcfdfeaba", "abc"));
    }

    static int simpleFind(String s, String pattern){

        String [] ss = s.split("");
        String [] patterns = pattern.split("");

        int result = -1;
        boolean flag = false;


        for (int i = 0; i < ss.length; i++) {
            for (int j = 0; j < patterns.length; j++) {
                if (ss[i].equals(patterns[j])) {
                    i++;
                    if (j == patterns.length - 1) {
                        result = i - patterns.length;
                        flag = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
        return result;
    }
}
