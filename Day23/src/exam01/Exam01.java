package exam01;

import com.sun.tracing.dtrace.StabilityLevel;

/*
*   面试题：doOther方法执行的时候需要等待doSome方法的结束吗？
*       不需要，应为doOther()方法没有synchronized
* */
public class Exam01 {
    public static void main(String[] args) {
        MyClass  myClass = new MyClass();

        Thread thread1 = new MyThread(myClass);
        Thread thread2 = new MyThread(myClass);

        thread1.setName("t1");
        thread2.setName("t2");

        thread1.start();
        try {
            Thread.sleep(1000);     // 这个睡眠的作用是：为了保证t1线程先执行。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}

class MyThread extends Thread {
    private MyClass myClass;

    public MyThread(MyClass myClass) {
        this.myClass = myClass;
    }

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("t1")) {
            myClass.doSome();
        }
        if (Thread.currentThread().getName().equals("t2")) {
            myClass.doOther();
        }
    }
}

class MyClass {
    public synchronized void doSome() {
        System.out.println("doSome begin");
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("doSome over");
    }

    public void doOther() {
        System.out.println("doOther begin");
        System.out.println("doOther over");
    }
}
