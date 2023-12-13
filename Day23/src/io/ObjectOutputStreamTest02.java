package io;

import bean.User;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/*
*   一次序列化多个对象？
*       - 可以，可以将对象放到集合当中，序列化集合。
*   提示：
*       参与序列化的ArrayList集合以及集合中的元素User都需要实现java.io.Serializable接口
* */
public class ObjectOutputStreamTest02 {
    public static void main(String[] args) throws Exception {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "zhangsan"));
        userList.add(new User(2, "lisi"));
        userList.add(new User(3, "wangwu"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("users"));

//        序列化一个集合，这个集合对象中放了很多其他对象
        objectOutputStream.writeObject(userList);

        objectOutputStream.flush();
        objectOutputStream.close();
    }
}
