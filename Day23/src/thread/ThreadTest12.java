package thread;

import com.sun.xml.internal.ws.api.server.ThreadLocalContainerResolver;

import javax.swing.*;

/*
*   让位，当前线程暂停，回到就绪状态，让给其它线程。
*   静态方法：Thread.yield();
* */
public class ThreadTest12 {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable6());
        thread.setName("thread");
        thread.start();

        for (int i = 1; i <= 10000; i++) {
            System.out.println(Thread.currentThread().getName() + "-->" + i);
        }
    }
}

class MyRunnable6 implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 10000; i++) {
//            每100个让位一次
            if (i % 100 == 0) {
                Thread.yield();     // 当前线程暂停以下，让给主线程。
            }
            System.out.println(Thread.currentThread().getName() + "-->" + i);
        }
    }
}
