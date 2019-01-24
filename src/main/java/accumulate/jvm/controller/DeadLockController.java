package accumulate.jvm.controller;

import accumulate.jvm.DeadLock;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: yaodao
 * @Date: 2018/11/8 16:12
 */
@RestController
public class DeadLockController {

    @RequestMapping("deadLock")
    public void deadlock(){
        Object obj1 = new Object();
        Object obj2 = new Object();

        // 起10个线程，这里把10改成1就不会有死锁问题了-因为单线程
        ExecutorService ex = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            int order = i % 2 == 0 ? 1 : 0;
            ex.execute(new DeadLock(order, obj1, obj2));
        }
    }
}
