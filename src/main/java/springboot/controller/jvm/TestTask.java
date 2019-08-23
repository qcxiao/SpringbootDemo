package springboot.controller.jvm;

/**
 * @Author: yaodao
 * @Date: 2018/11/6 16:17
 */
public class TestTask implements Runnable {

    @Override
    public void run() {
        synchronized (this) {
            try {
                //等待被唤醒
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        //ExecutorService ex = Executors.newFixedThreadPool(1);
        //ex.execute(new TestTask());
        new Thread(new TestTask()).start();
    }
}
