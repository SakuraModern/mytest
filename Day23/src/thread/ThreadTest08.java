package thread;

/*
*   sleep睡眠太久了，如果希望半道上醒来，应该怎么办？也就是怎么唤醒一个正在睡眠的线程？
*       注意：这个不是终断线程的执行，是终止线程的睡眠。
* */
public class ThreadTest08 {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable2());
        thread.setName("thread");
        thread.start();

//        希望5秒之后，thread线程醒来
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        终断thread线程的睡眠（这种终断睡眠的方式依靠了java的异常处理机制）
        thread.interrupt();     // 干扰
    }
}

class MyRunnable2 implements Runnable {
//    重点：run()当中的异常不能throws，只能try catch
//    因为run()方法在父类中没有抛出任何异常，子类不能比父类抛出更多的异常
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " --> begin");
        try {
//            睡眠一年
            Thread.sleep(1000 * 60 * 60 * 24 * 365);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        一年之后才会执行这里
        System.out.println(Thread.currentThread().getName() + " --> end");

//        调用doOther
//        doOther();
    }

//    其他方法可以throws
/*    public void doOther() throws Exception {

    }*/
}
