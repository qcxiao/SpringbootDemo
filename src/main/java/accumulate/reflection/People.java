package accumulate.reflection;

/**
 * @Author: yaodao
 * @Date: 2018/7/31 21:45
 */
//定义一个待反射类
public class People {
    public String name;

    public void sayHello(){
        System.out.println("hello wrold ");
    }

    public String sayHello(int i){
        System.out.println("hello wrold " + i);
        return "i'm sayHello method";
    }
}
