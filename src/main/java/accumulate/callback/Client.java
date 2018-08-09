package accumulate.callback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client implements CSCallBack {
    private Server server;

    public Client(Server server) {
        this.server = server;
    }

    public void sendMsg(final String msg) {
        log.info("【客户端】发送消息：" + msg);
        // 新起线程的作用就是使asynchronizationTest方法得以异步调用
        new Thread(() -> {
            server.getClientMsg(Client.this, msg);
        }).start();
    }
    /**
     * 具体的回调方法
     *
     * @param status
     */
    public void process(String status) {
        log.info("【客户端】接收到服务端回调状态为：{}", status);
    }

    public void asynchronizationTest() {
        log.info("【客户端】执行正常逻辑");
    }
}