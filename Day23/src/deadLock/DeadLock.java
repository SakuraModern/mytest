package deadLock;

/*
*   死锁代码，要会写。
* */
public class DeadLock {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();

//        thread1和thread2两个线程共享o1，o2
        Thread thread1 = new MyThread6(o1 , o2);
        Thread thread2 = new MyThread5(o1 , o2);

        thread1.start();
        thread2.start();
    }
}

class MyThread6 extends Thread {
    Object o1;
    Object o2;

    public MyThread6(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    @Override
    public void run() {
        synchronized(o1) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(o2) {

            }
        }
    }
}

class MyThread5 extends Thread {
    Object o1;
    Object o2;

    public MyThread5(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    @Override
    public void run() {
        synchronized(o2) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(o1) {

            }
        }
    }
}
