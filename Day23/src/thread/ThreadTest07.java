package thread;

/*
*   关于Thread.sleep()方法的一个面试题：
*
* */
public class ThreadTest07 {
    public static void main(String[] args) {
//        创建线程对象
        Thread thread = new MyThread3();
        thread.setName("thread");
        thread.start();

//        调用sleep方法
        try {
            /*
            *   这行代码不会让线程thread进入休眠状态。
            *   在执行的时候还是会转换成：Thread.sleep(1000 * 5);
            *   这行代码的作用：让当前线程进入休眠，也就是说main线程进入休眠。
            *   这行代码出现在main方法中，main线程睡眠。
            * */
            thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        5秒钟之后这里才会运行
        System.out.println("hello world");
    }
}

class MyThread3 extends Thread {
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(Thread.currentThread().getName() + "-->" + i);
        }
    }
}
