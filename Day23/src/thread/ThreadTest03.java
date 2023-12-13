package thread;

import com.sun.xml.internal.ws.message.source.PayloadSourceMessage;

/*
*   实现线程的第二种方式，编写一个类实现java.lang.Runnable接口。
* */
public class ThreadTest03 {
    public static void main(String[] args) {
//        创建一个可运行的对象
        MyRunnable myRunnable = new MyRunnable();

//        将可运行的对象封装成一个线程对象
        Thread thread = new Thread(myRunnable);

//        启动线程
        thread.start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("主线程-->" + i);
        }
    }
}

/*
*   这并不是一个线程类，是一个可运行的类，它还不是一个线程。
* */
class MyRunnable  implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("分支线程-->" + i);
        }
    }
}
