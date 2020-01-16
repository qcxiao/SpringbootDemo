package accumulate.io.socket.basic;

import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @Author: yaodao
 * @Date: 2018/9/12 14:40
 */
@Slf4j
public class SocketClient {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            // 指定套接字
            socket = new Socket("127.0.0.1", 8888);
            // 获取输出流
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            // 获取输入流
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            // 向服务端发起通信
            dos.writeUTF("客户端请求连接");
            log.info("【SocketClient】接收到服务端的消息: " + dis.readUTF());

        } catch (IOException e){
            e.printStackTrace();
        } finally {
           try {
               socket.close();
           } catch (IOException e){
               e.printStackTrace();
           }
        }
    }
}
