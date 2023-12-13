package thread;

/*
*   1、怎么获取当前线程的名字
*       Thread thread = Thread.currentThread();
*       返回值Thread就是当前线程
*   2、获取线程对象的名字
*       线程对象.getName();
*   3、修改线程对象的名字
*       线程对象.setName("线程名字");
*   4、当线程没有设置名字的时候，默认的名字有什么规律？
*       Thread-0
*       Thread-1
*       Thread-2
*       ...
* */
public class ThreadTest05 {
    public static void main(String[] args) {
//        currentThread就是当前线程对象
//        这个代码出现在main方法当中，所以当前线程就是主线程。
        Thread currentThread = Thread.currentThread();
        System.out.println(currentThread.getName());

//        创建线程对象
        MyThread2 myThread = new MyThread2();

//        设置线程的名字
        myThread.setName("qwe");

//        获取线程的名字
        String myThreadName = myThread.getName();
        System.out.println(myThreadName);

        MyThread2 myThread2 = new MyThread2();
        System.out.println(myThread2.getName());

        myThread2.start();

//        启动线程
        myThread.start();
    }
}

class MyThread2 extends Thread {
    public void run() {
        for (int i = 0; i < 100; i++) {
//            currentThread就是当前线程对象。
//            当myThread线程执行run方法，那么这个当前线程就是myThread
//            当myThread2线程执行run方法，那么这个当前线程就是myThread2
            Thread currentThread = Thread.currentThread();
            System.out.println(currentThread.getName() + "-->" + i);
        }
    }
}
