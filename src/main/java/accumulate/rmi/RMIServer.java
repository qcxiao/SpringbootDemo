package accumulate.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * 1. 创建并导出远程对象
 * 2. 用Java RMI registry 注册远程对象
 *
 * @Author: yaodao
 * @Date: 2018/8/17 17:10
 */
public class RMIServer {
    public static void main(String[] args) {
        try {
            // 注册端口
            LocateRegistry.createRegistry(1099);
            // 实例化对象
            MyRemote remote = new MyRemoteImpl();
            // 发布对象
            Naming.bind("rmi://127.0.0.1/hello", remote);

            System.out.println("绑定成功!");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e){
            e.printStackTrace();

        }
    }
}
