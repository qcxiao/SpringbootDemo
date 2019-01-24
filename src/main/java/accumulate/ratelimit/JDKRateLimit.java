package accumulate.ratelimit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author: yaodao
 * @Date: 2019/1/22 20:22
 */
public class JDKRateLimit {
    public static void main(String[] args) {
        //线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        //只能5个线程同时访问
        final Semaphore semp = new Semaphore(3);

        for (int i = 0; i < 10; i++) {
            final int no = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        //获取许可
                        semp.acquire();
                        System.out.println("Accessing: " + no
                                + " --- " + new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date()));
                        //睡5s
                        Thread.sleep(5000);
                        //访问完后,释放许可，如果注释掉下面的语句,则控制台只能打印3条记录,之后线程一直阻塞
                        semp.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            };
            //执行线程
            exec.execute(runnable);
        }
        //退出线程池
        exec.shutdown();
    }
}
