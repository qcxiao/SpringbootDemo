package accumulate.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yaodao
 * @Date: 2018/8/6 20:58
 */
public class MyClassLoaderTest {

    @SuppressWarnings("rawtypes")
    private static Map<String, Class> map = new HashMap<>();

    public static class ReLaunchClassLoader extends URLClassLoader {
        public ReLaunchClassLoader(URL[] urls, ClassLoader cls) {
            super(urls, cls);
        }

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            if (map.get(name) != null) {
                System.out.println("load classcache from map name:" + name);
                return map.get(name);
            }
            return super.loadClass(name);
        }
    }

    public static class ModuleClassLoader extends URLClassLoader {
        public ModuleClassLoader(URL[] urls, ClassLoader cls) {
            super(urls, cls);
        }

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            return super.loadClass(name);
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            Class<?> clazz = this.findLoadedClass(name);  // 父类已加载
            if (clazz == null) {  //检查该类是否已被加载过
                byte[] classData = getClassData(name);  //根据类的二进制名称,获得该class文件的字节码数组
                if (classData == null) {
                    throw new ClassNotFoundException();
                }
                clazz = defineClass(name, classData, 0, classData.length);  //将class的字节码数组转换成Class类的实例
            }
            return clazz;
        }

        private byte[] getClassData(String name) {
            InputStream is = null;
            try {
                String path = classNameToPath(name);
                URL url = new URL(path);
                byte[] buff = new byte[1024 * 4];
                int len;
                is = url.openStream();
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                while ((len = is.read(buff)) != -1) {
                    os.write(buff, 0, len);
                }
                return os.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        private String classNameToPath(String name) {
            String classPath = this.getURLs()[0].toString() + "/" + name.replace(".", "/") + ".class";
            System.out.println("[Loaded----- " + name + " from " + classPath);
            return classPath;
        }
    }

    public static void loadClass(File file, String className) throws Exception {
        ModuleClassLoader mcl = new ModuleClassLoader(new URL[]{file.toURL()}, null);
        Class cls = mcl.loadClass(className);
        map.put(cls.getName(), cls);

    }

    public static void main(String[] args) throws Exception {
        MyClassLoaderTest clt = new MyClassLoaderTest();
        clt.testCL();
    }

    public void testCL() throws Exception {
        String className1 = "TestB";
        String className2 = "TestC";

        loadClass(new File("/Users/qcxiao/like/springbootdemo/target/classes/top/qcxiao/springbootdemo/accumulate/classloader"), className1);//@a
        loadClass(new File("/Users/qcxiao/like/springbootdemo/target/classes/top/qcxiao/springbootdemo/accumulate/classloader"), className2);//@b

        System.out.println("--------------------------------");

        File file2 = new File("/Users/qcxiao/like/springbootdemo/target/classes/top/qcxiao/springbootdemo/accumulate/classloader2");
        ReLaunchClassLoader icl = new ReLaunchClassLoader(new URL[]{file2.toURL()}, MyClassLoaderTest.class.getClassLoader().getParent());
        Class c_class = Class.forName("accumulate.classloader.TestA", true, icl); // 相当于Spring-Boot中的 Application,@c
        System.out.println("a cl:" + c_class.getClassLoader());
        Object c = c_class.newInstance();
        Method m1 = c_class.getDeclaredMethod("testB"); // 反射调用
        m1.invoke(c);

    }
}
