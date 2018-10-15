package accumulate.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @Author: yaodao
 * @Date: 2018/8/17 17:09
 */
public interface MyRemote extends Remote {
    String sayHello() throws RemoteException;
}
