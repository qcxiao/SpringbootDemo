package accumulate.jvm_;

/**
 *
 * javac src/main/java/accumulate/jvm_/HelloWorld.java
 * java -cp src/main/java/ accumulate.jvm_.HelloWorld
 * my classLoader is sun.misc.Launcher$AppClassLoader@2a139a55
 * @Author: yaodao
 * @Date: 2019/3/29 16:54
 */
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("my classLoader is " + HelloWorld.class.getClassLoader());
    }
}
