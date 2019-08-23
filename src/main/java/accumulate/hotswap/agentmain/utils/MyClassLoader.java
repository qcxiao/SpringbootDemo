//package accumulate.hotswap.agentmain.utils;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//
///**
// * 自定义类加载器
// * @Author: yaodao
// * @Date: 2018/10/17 20:46
// */
//public class MyClassLoader extends ClassLoader {
//    private String mLibPath;
//
//    public MyClassLoader(String path) {
//        mLibPath = path;
//    }
//
//    @Override
//    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        String fileName = getFileName(name);
//        File file = new File(mLibPath, fileName);
//        try {
//            FileInputStream is = new FileInputStream(file);
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            int len = 0;
//            try {
//                while ((len = is.read()) != -1) {
//                    bos.write(len);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            byte[] data = bos.toByteArray();
//            is.close();
//            bos.close();
//            return defineClass(name, data, 0, data.length);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return super.findClass(name);
//    }
//
//    //获取要加载的class文件名
//    private String getFileName(String name) {
//        int index = name.lastIndexOf('.');
//        if (index == -1) {
//            return name + ".class";
//        } else {
//            return name.substring(index + 1) + ".class";
//        }
//    }
//
//    public static void main(String[] args) {
//        //创建自定义classloader对象。
//        MyClassLoader diskLoader = new MyClassLoader("/Users/qcxiao/like/springbootdemo/src/main/java/accumulate/hotswap/agentmain/asmutil/test");
//        try {
//            //加载class文件
//            Class c = diskLoader.loadClass("accumulate.hotswap.agentmain.asmutil.test.Bazhang");
//
//            if (c != null) {
//                try {
//                    Object obj = c.newInstance();
//                    Method method = c.getMethod("newFunc", new Class[]{String.class});
//                    //通过反射调用Test类的say方法
//                    method.invoke(obj, "haha");
//                } catch (InstantiationException | IllegalAccessException
//                        | NoSuchMethodException
//                        | SecurityException |
//                        IllegalArgumentException |
//                        InvocationTargetException e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//}
