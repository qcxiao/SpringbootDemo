package accumulate.innerclass;

/**
 * 内部类的使用说明
 *
 * @Author: yaodao
 * @Date: 2019/1/10 14:27
 */
public class OuterClass {
    int num1 = 1; //成员变量

    public void outerMethod() {
        System.out.println("It's Method of OuterClass");
        int num2 = 2; // 方法内局部变量
        class Innerclass {
            public void innerMethod() {
                // 方法中内部类的方法，可以正常访问外部类的成员变量
                System.out.println(num1);
                // JDK1.8以前，方法中内部类的方法，不能直接访问外部类的方法的局部变量，必须声明为final
                System.out.println(num2);
            }
        }
        new Innerclass().innerMethod();
    }

    class Innerclass {
        public void innerMethod() {
            System.out.println("It's Method of innerMethod");
        }
    }

}