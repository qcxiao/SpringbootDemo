package top.qcxiao.springbootdemo.accumulate.socket;

import java.io.IOException;
import java.net.Socket;

/**
 * 多线程处理某一个客户端socket请求
 */
public class ChatSocket extends Thread {
    Socket socket;
    public ChatSocket(Socket socket){
        this.socket = socket;
    }
    // 向客户端输出消息
    private void outMessage(String message){
        try {
            socket.getOutputStream().write(message.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        int count = 0;
        while (true){
            count++;
            outMessage("loop:" + count);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
