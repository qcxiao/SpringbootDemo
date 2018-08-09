package accumulate.classload;

/**
 * Class.forname()与ClassLoader.loadClass()的区别
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        Test.class.getClassLoader().loadClass("accumulate.classload.Test");


        ClassLoader classLoader = Test.class.getClassLoader();
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(classLoader.toString());
        System.out.println(classLoader.getParent().toString());
        System.out.println(classLoader.getParent().getParent());
    }
}
