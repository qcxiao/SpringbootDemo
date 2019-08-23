package springboot.controller.jvm;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: yaodao
 * @Date: 2018/11/6 17:11
 */
public class WaitingIO {
    public static void main(String[] args) throws IOException {
        InputStream is = System.in;
        int i = is.read();
        System.out.println("exit");
    }
}
