package exam03;

/*
*   面试题：doOther方法执行的时候需要等待doSome方法的结束吗？
*       不需要，因为MyClass对象是两个，有两把锁。
* */
public class Exam01 {
    public static void main(String[] args) {
        MyClass3 myClass1 = new MyClass3();
        MyClass3 myClass2 = new MyClass3();

        Thread thread1 = new MyThread3(myClass1);
        Thread thread2 = new MyThread3(myClass2);

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

class MyThread3 extends Thread {
    private MyClass3 myClass;

    public MyThread3(MyClass3 myClass) {
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

class MyClass3 {
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
