package exam04;

/*
*   面试题：doOther方法执行的时候需要等待doSome方法的结束吗？
*       需要，因为静态方法是类锁，不管创建了几个对象，类锁只有一把。
* */
public class Exam01 {
    public static void main(String[] args) {
        MyClass4 myClass1 = new MyClass4();
        MyClass4 myClass2 = new MyClass4();

        Thread thread1 = new MyThread4(myClass1);
        Thread thread2 = new MyThread4(myClass2);

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

class MyThread4 extends Thread {
    private MyClass4 myClass;

    public MyThread4(MyClass4 myClass) {
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

class MyClass4 {
    public synchronized static void doSome() {
        System.out.println("doSome begin");
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("doSome over");
    }

    public synchronized static void doOther() {
        System.out.println("doOther begin");
        System.out.println("doOther over");
    }
}
