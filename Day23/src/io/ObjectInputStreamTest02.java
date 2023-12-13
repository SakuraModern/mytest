package io;

import bean.User;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/*
*   反序列集合
* */
public class ObjectInputStreamTest02 {
    public static void main(String[] args) throws Exception {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("users"));
//        Object obj = objectInputStream.readObject();
//        System.out.println(obj instanceof List);
        List<User> userList = (List<User>)objectInputStream.readObject();
        for (User data : userList) {
            System.out.println(data);
        }
        objectInputStream.close();
    }
}
