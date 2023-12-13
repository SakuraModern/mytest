package io;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ObjectInputStreamTest01 {
    public static void main(String[] args) throws Exception {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("students"));
//        开始反序列化，读
        Object obj = objectInputStream.readObject();
//        反序列化回来是一个学生对象，所有会调用学生对象的toString方法。
        System.out.println(obj);
        objectInputStream.close();
    }
}
