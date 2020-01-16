package accumulate.innerclass;

/**
 * @Author: yaodao
 * @Date: 2019/1/10 14:29
 */
public class OuterClassTest {
    public static void main(String[] args) {
        /**
         * 不能在static方法中直接new内部类，否则出现错误：
         * No enclosing instance of type OuterClass is accessible. Must qualify the allocation with an enclosing instance of type OuterClass (e.g. x.new A() where x is an instance of OuterClass).
         * 这是因为静态方法是在类实例化之前就可以使用的，通过类名调用，这时动态内部类都还没实例化而出错
         * 因为main方法是静态方法，所以必须使用x.new A()的方式实例化内部类
         */
        OuterClass.Innerclass in = new OuterClass().new Innerclass();
        in.innerMethod();

        OuterClass outerClass = new OuterClass();
        outerClass.outerMethod();

    }
}
