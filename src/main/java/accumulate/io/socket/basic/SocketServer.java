package accumulate.io.socket.basic;

import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端实现
 * @Author: yaodao
 * @Date: 2018/9/12 14:34
 */
@Slf4j
public class SocketServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try{
            // 绑定本地端口
            serverSocket = new ServerSocket(8888);
            // 调用accept()进入阻塞
            socket = serverSocket.accept();
            // 获取输出流
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            // 获取输入流
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            // 向客户端发起通信
            dos.writeUTF("连接成功");

            log.info("【SocketServer】接收到客户端的连接请求: " + dis.readUTF());
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                serverSocket.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}
