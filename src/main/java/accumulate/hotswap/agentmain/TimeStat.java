package accumulate.hotswap.agentmain;

import utils.RandomUtil;

/**
 * @Author: yaodao
 * @Date: 2018/10/17 12:48
 */
public class TimeStat {
    {
        System.out.println("11");
    }
    static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    public static void start() {
        threadLocal.set(System.currentTimeMillis());
    }
    public static void end() {
        long time = System.currentTimeMillis() - threadLocal.get();
        System.out.print(Thread.currentThread().getStackTrace()[2] + "方法耗费时间: " + time + "毫秒");
    }

    public static void main(String[] args) {
        start();
        try {
            Thread.sleep(RandomUtil.randomInt(100, 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        end();
    }
}
