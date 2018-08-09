package accumulate.classloader;

/**
 * @Author: yaodao
 * @Date: 2018/8/6 21:01
 */
public class TestC {
    public void testC() {
        TestD d = new TestD();
        System.out.println("TestD.class is:" + TestC.class.getClassLoader());
        System.out.println("d object is:" + d);
        System.out.println("d's classloader is:" + d.getClass().getClassLoader());
        System.out.println("d's parentCL is:" + d.getClass().getClassLoader().getParent());
    }
}
