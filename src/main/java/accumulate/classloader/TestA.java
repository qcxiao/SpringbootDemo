package accumulate.classloader;

/**
 * @Author: yaodao
 * @Date: 2018/8/6 20:59
 */
public class TestA {
    public void testB() {
        TestB b = new TestB();
        System.out.println("b is:" + b);
        System.out.println("b classloader is:" + b.getClass().getClassLoader());
        System.out.println("b parentCL is:" + b.getClass().getClassLoader().getParent());
        b.testC();
        TestD d = new TestD();
        System.out.println("a--d is:" + d);
        System.out.println("a--d classloader is:" + d.getClass().getClassLoader());
        System.out.println("a--d parentCL is:" + d.getClass().getClassLoader().getParent());
    }
}
