package accumulate.hotswap.agentmain.jar;


/**
 * javac Provider.java
 * jar -cvfm agentmain.jar MANIFEST.MF Provider.class
 *
 * @Author: yaodao
 * @Date: 2018/10/16 20:50
 */
public class Provider {
    public void operation() {
        for (int i = 0; i < Provider.randomInt(2, 4); i++) {
            try {
                Thread.sleep(Provider.randomInt(100, 2000));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("do something...");
            try {
                Thread.sleep(Provider.randomInt(100, 1500));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public static int randomInt(int min, int max){
        return (int) (min + Math.random() * (max - min));
    }
}
