package top.qcxiao.springbootdemo.accumulate.socket;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端监听器
 */
public class ServerListener extends Thread {
    @Override
    public void run() {
        try {
            // 服务端监听端口8888
            ServerSocket serverSocket = new ServerSocket(8888);
            while (true){
                // block
                // 获取到客户端的socket连接
                Socket socket = serverSocket.accept();
                JOptionPane.showMessageDialog(null, "已连接");
                // 将监听到的客户端socket请求传递给具体的线程处理
                new ChatSocket(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
