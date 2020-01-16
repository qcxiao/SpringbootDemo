package accumulate.ThreadLocal;

/**
 * https://www.cnblogs.com/ldq2016/p/9041856.html
 *
 * https://www.jianshu.com/p/30ee77732843
 */
public class ThreadLocalDemo2 {

    // 通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
       public Integer initialValue(){
           return 0;
       }
    };

    public int getNextValue(){
        threadLocal.set(threadLocal.get() + 1);
        return threadLocal.get();
    }

    /**
     * 考察输出的结果信息，每个线程所产生的序号虽然都共享同一个ThreadLocalDemo2实例，
     * 但它们并没有发生相互干扰的情况，而是各自产生独立的序列号，
     * 这是因为我们通过ThreadLocal为每一个线程提供了单独的副本。
     */
    public static void main(String[] args) {
        ThreadLocalDemo2 demo = new ThreadLocalDemo2();

        // 3个线程共享demo，各自产生序列号
        Client client1 = new Client(demo);
        Client client2 = new Client(demo);
        Client client3 = new Client(demo);

        client1.start();
        client2.start();
        client3.start();
    }


    private static class Client extends Thread {
        private ThreadLocalDemo2 demo;

        public Client(ThreadLocalDemo2 demo){
            this.demo = demo;
        }

        public void run(){
            for (int i = 0; i < 3; i++) {
                // 每个线程打出3个序列值
                System.out.println("thread[" + Thread.currentThread().getName() + "] --> demo["
                        + demo.getNextValue() + "]");
            }
        }
    }

}


