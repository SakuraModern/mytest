package threadsafe;

public class Test {
    public static void main(String[] args) {
//        创建账户对象
        Account account = new Account("act-001", 10000);

//        创建两个线程
        Thread thread1 = new AccountThread(account);
        Thread thread2 = new AccountThread(account);

//        设置name
        thread1.setName("thread1");
        thread2.setName("thread2");

//        启动线程取款
        thread1.start();
        thread2.start();


    }
}
