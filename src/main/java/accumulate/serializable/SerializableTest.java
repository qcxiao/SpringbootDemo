package accumulate.serializable;

import java.io.*;

/**
 * @Author: yaodao
 * @Date: 2018/12/4 14:59
 */
public class SerializableTest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name = "SheepMu";
    private transient int age = 24; // 被transient修饰的变量不能被序列化

    public static void main(String[] args) {//以下代码实现序列化
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("my.out"));//输出流保存的文件名为 my.out ；ObjectOutputStream能把Object输出成Byte流
            SerializableTest serializableTest = new SerializableTest();
            oos.writeObject(serializableTest);
            oos.flush();  //缓冲流
            oos.close(); //关闭流
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fan();//调用下面的 反序列化 代码
    }

    public static void fan(){//反序列的过程
        ObjectInputStream oin = null;//局部变量必须要初始化
        try {
            oin = new ObjectInputStream(new FileInputStream("my.out"));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        SerializableTest serializableTest = null;
        try {
            serializableTest = (SerializableTest) oin.readObject();//由Object对象向下转型为SerializableTest对象
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("name=" + serializableTest.name);
        System.out.println("age=" + serializableTest.age);
    }
}
