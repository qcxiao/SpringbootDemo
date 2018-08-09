package accumulate.wrapper;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yaodao
 * @Date: 2018/7/25 15:11
 * @des: 包装类
 */
@Slf4j
public class WrapperTest {
    Map<String, List<String>> map = new HashMap<>();


    public static void main(String[] args) {

        /**
         * 拆、装箱
         */
        Integer i0 = 10; //装箱,调用Integer.valueOf(),即:Integer.valueOf(10);
        int j0 = i0;  //拆箱,调用Integer.intValue(),即:i0.intValue();

        Integer i = 200;
        Integer j = 200;
        System.out.println(i == j);

        Integer ii = new Integer(100);
        Integer jj = 100;
        System.out.println(ii == jj);

        Integer ii1 = new Integer(100);
        int jj1 = 100;
        System.out.println(ii1 == jj1);

        Boolean i1 = false;
        Boolean i2 = false;
        Boolean i3 = true;
        Boolean i4 = true;

        System.out.println(i1==i2);
        System.out.println(i3==i4);
        commonMethod();

    }

    /**
     * 包装类共同的方法
     */
    private static void commonMethod(){
        // 1. 带有基本值参数并创建包装类对象的构造函数
        Integer obj = new Integer(145);
        log.info("obj: {}", obj);
        // 2. 带有字符串参数并创建包装类对象的构造函数
        obj = new Integer("-1");
        log.info("obj: {}", obj);
        // 3. 可生成对象基本值的typeValue方法
        log.info("obj.intValue(): {}", obj.intValue());
        // 4. 将字符串转换为基本值的parseType方法
        log.info("Integer.parseInt(\"-1\"): {}", Integer.parseInt("-1"));
        // 5. 生成哈稀表代码的hashCode方法
        log.info("obj.hashCode(): {}", obj.hashCode());
        // 6. 对同一个类的两个对象进行比较的equals()方法

        // 7. 生成字符串表示法的toString()方法
    }

}
