package accumulate.classloader;

/**
 * @Author: yaodao
 * @Date: 2018/8/6 20:58
 */
public class TestB {
    public void testC() {
        TestC c = new TestC();
        System.out.println("TestC.class is:" + TestC.class.getClassLoader());
        System.out.println("c object is:" + c);
        System.out.println("c's classloader is:" + c.getClass().getClassLoader());
        System.out.println("c's parentCL is:" + c.getClass().getClassLoader().getParent());
        c.testC();
    }
}
