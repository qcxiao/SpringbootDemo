package accumulate.hotswap.simple;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

/**
 * https://www.jianshu.com/p/731bc8293365
 * https://www.jianshu.com/p/0bbd79661080
 * https://www.jianshu.com/p/6096bfe19e41
 * @Author: yaodao
 * @Date: 2018/10/16 20:42
 */
@Slf4j
public class Test {
    public static void main(String[] args) throws Exception {
        while (true) {
            ClassLoader loader = new ClassLoader() {
                @Override
                public Class<?> loadClass(String name) throws ClassNotFoundException {
                    try {
                        String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                        InputStream is = getClass().getResourceAsStream(fileName);
                        if (is == null) {
                            return super.loadClass(name);
                        }
                        byte[] b = new byte[is.available()];
                        is.read(b);
                        return defineClass(name, b, 0, b.length);
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new ClassNotFoundException(name);
                    }
                }
            };
            Class clazz = loader.loadClass("accumulate.hotswap.simple.Account");
            Object account = clazz.newInstance();
            account.getClass().getMethod("operation").invoke(account);
            Thread.sleep(5000);
        }
    }
}
