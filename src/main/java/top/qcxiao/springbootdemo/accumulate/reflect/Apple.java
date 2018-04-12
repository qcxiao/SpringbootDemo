package top.qcxiao.springbootdemo.accumulate.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Apple {
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static void main(String[] args) throws Exception {
        /**
         * 正常的调用
         */
        Apple apple = new Apple();
        apple.setPrice(5);
        System.out.println("Apple Price:" + apple.getPrice());


        /**
         * 使用反射调用
         */
        // 通过完整类名反射出类对象
        Class clz = Class.forName("top.qcxiao.springbootdemo.accumulate.reflect.Apple");
        // 获取set方法对象
        Method setPriceMethod = clz.getMethod("setPrice", int.class);
        // 获取构造方法对象
        Constructor appleConstructor = clz.getConstructor();
        /*
         * 以下两种实例化对象的方式都可以：
         * 1. Class对象创建，只能实例化无参对象
         * 2. 构造函数创建
         */
        Object appleObj = clz.newInstance();
        //Object appleObj = appleConstructor.newInstance();
        // 此处invoke方法相当执行set方法
        setPriceMethod.invoke(appleObj, 14);
        // 获取get方法对象
        Method getPriceMethod = clz.getMethod("getPrice");
        // 通过invoke执行get方法
        System.out.println("Apple Price:" + getPriceMethod.invoke(appleObj));
    }
}
