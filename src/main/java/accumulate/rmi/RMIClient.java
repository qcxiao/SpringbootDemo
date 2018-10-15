package accumulate.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 1. 客户端通过远程对象存根中的IP和端口打开一个服务器连接，并且序列化请求数据
 * 2. 服务器端接收请求并且转发请求到远程对象调用服务方法，并且序列化运行结果发送给客户端
 * 3. 客户端接收数据反序列化，把最终结果返回给调用者
 * @Author: yaodao
 * @Date: 2018/8/17 17:12
 */
public class RMIClient {
    public static void main(String[] args) {
        try {
            MyRemote myRemote = (MyRemote) Naming.lookup("rmi://127.0.0.1/hello");
            String ret = myRemote.sayHello();
            System.out.println(ret);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
    }
}
