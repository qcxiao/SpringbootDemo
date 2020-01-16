package accumulate.ratelimit;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: yaodao
 * @Date: 2019/1/22 20:50
 */
public class GuavaRateLimit3 {
    //key-value (service,Qps) ，接口服务的限制速率
    private static final ConcurrentMap<String, Double> resourceMap = Maps.newConcurrentMap();
    //userkey-service,limiter ,限制用户对接口的访问速率
    private static final ConcurrentMap<String, RateLimiter> userResourceLimiterMap = Maps.newConcurrentMap();

    static {
        //init ，初始化方法A的Qps为50
        resourceMap.putIfAbsent("methodA", 10.0);
    }

    public static void updateResourceQps(String resource, double qps) {
        resourceMap.put(resource, qps);
    }

    public static void removeResource(String resource) {
        resourceMap.remove(resource);
    }

    public static int enter(String resource, String userKey) {
        long t1 = System.currentTimeMillis();
        Double qps = resourceMap.get(resource);

        //不限流
        if (qps == null || qps.doubleValue() == 0.0) {
            return 0;
        }

        String keySer = resource + userKey;
        RateLimiter rateLimiter = userResourceLimiterMap.get(keySer);
        //if null , new limit
        if (rateLimiter == null) {
            rateLimiter = RateLimiter.create(qps);

            RateLimiter putByOtherThread = userResourceLimiterMap.putIfAbsent(keySer, rateLimiter);
            if (putByOtherThread != null) {
                rateLimiter = putByOtherThread;
            }
            rateLimiter.setRate(qps);
        }

        //非阻塞
        if (!rateLimiter.tryAcquire()) {
            //限速中，提示用户
            System.out.println("use :" + (System.currentTimeMillis() - t1) + "ms ; "
                    + resource + " visited too frequently by key:" + userKey);
            return 99;
        } else {
            //正常访问
            System.out.println("use :" + (System.currentTimeMillis() - t1) + "ms ; ");
            return 0;
        }

    }

    private static void testA() throws InterruptedException {
        int i = 0;
        while (true) {
            i++;
            long t2 = System.currentTimeMillis();
            System.out.println(" begin:" + t2 + " , hanchao:" + i);

            int result = GuavaRateLimit3.enter("methodA", "hanchao");
            if (result == 99) {
                i = 0;
                Thread.sleep(1000);
            }
        }
    }

    private static void testB() throws InterruptedException {
        //测试other
        int y = 0;
        while (true) {
            y++;
            long t3 = System.currentTimeMillis();
            System.out.println(" begin:" + t3 + " , tom:" + y);

            int result2 = GuavaRateLimit3.enter("methodB", "tom");
            if (result2 == 99) {
                y = 0;
                Thread.sleep(1000);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // testA();
        GuavaRateLimit3.updateResourceQps("methodB", 5.0);
        testB();

    }
}
