package accumulate.hotswap.simple;

/**
 * @Author: yaodao
 * @Date: 2018/10/16 20:50
 */
public class Account {
    public void operation() {
        System.out.println("operation...new");
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
