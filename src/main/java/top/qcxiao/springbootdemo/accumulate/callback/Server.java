package top.qcxiao.springbootdemo.accumulate.callback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server {
    public void getClientMsg(CSCallBack csCallBack , String msg) {
        log.info("【服务端】接收到客户端发送的消息为：{}", msg);

        // 模拟服务端需要对数据处理
        try {
            Thread.sleep(15 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("【服务端】执行完成");
        String status = "200";
        log.info("【服务端】数据处理成功，返回成功状态：{}", status);
        csCallBack.process(status);
    }
}
