package accumulate.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author: yaodao
 * @Date: 2018/7/31 21:45
 */
public class Client {
    public static void main(String[] args) throws Exception {
        Class<People> cls = People.class;
        Field field = cls.getField("name");
        People people = cls.newInstance();
        field.set(people,"hello");
        System.out.println(people.name);


        Class cls2 = Class.forName("accumulate.reflection.People");
        Method sayHello = cls2.getMethod("sayHello", int.class);
        People people2 = (People) cls2.newInstance();
        Object obj = sayHello.invoke(people2, new Object[]{1});
        System.out.println(obj);
    }
}
