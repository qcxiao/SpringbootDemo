package accumulate.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @Author: yaodao
 * @Date: 2018/8/17 17:09
 */
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote  {
    protected MyRemoteImpl() throws RemoteException {

    }
    @Override
    public String sayHello() throws RemoteException {
        return "Hello Word!";
    }
}
