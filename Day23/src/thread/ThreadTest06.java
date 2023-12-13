package thread;

/*
*   关于线程的sleep方法：
*       static void sleep(long millis)
*       1、静态方法
*       2、参数是毫秒
*       3、作用：让当前线程进入休眠，进入“阻塞状态”，放弃占有CPU时间片，让给其它线程使用。
*           这样代码出现在A线程中，A线程就会进入休眠。
*           这样代码出现在B线程中，B线程就会进入休眠。
*       4、Thread.sleep()方法，可以做到这种效果：
*           间隔特定的时间，去执行一段特定的代码，每隔多久执行一次。
* */
public class ThreadTest06 {
    public static void main(String[] args) {
//        让当前线程进入休眠，睡眠5秒钟
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        5秒之后执行这里的代码
//        System.out.println("hello world");

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "-->" + i);
//            让当前线程睡眠1秒钟
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
