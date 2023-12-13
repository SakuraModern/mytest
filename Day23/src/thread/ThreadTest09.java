package thread;

/*
*   在java中怎么强行终止一个线程的执行
*       这种方式存在很大的缺点：容易丢失数据，因为这种方式是直接将线程杀死了，
*   线程没有保存的数据将会丢失，不建议使用。
* */
public class ThreadTest09 {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable3());
        thread.setName("thread");
        thread.start();

//        模拟5秒
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        5秒钟之后强行终止thread线程
        thread.stop();      // 已过时
    }
}

class MyRunnable3 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " --> " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
