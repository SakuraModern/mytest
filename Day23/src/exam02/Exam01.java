package exam02;

/*
*   面试题：doOther方法执行的时候需要等待doSome方法的结束吗？
*       需要。
* */
public class Exam01 {
    public static void main(String[] args) {
        MyClass2 myClass = new MyClass2();

        Thread thread1 = new MyThread2(myClass);
        Thread thread2 = new MyThread2(myClass);

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

class MyThread2 extends Thread {
    private MyClass2 myClass;

    public MyThread2(MyClass2 myClass) {
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

class MyClass2 {
    public synchronized void doSome() {
        System.out.println("doSome begin");
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("doSome over");
    }

    public synchronized void doOther() {
        System.out.println("doOther begin");
        System.out.println("doOther over");
    }
}
