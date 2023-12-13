package threadsafe02;

/*
*   银行账户
*       不使用线程同步机制，多线程对同一个账户进行取款，出现线程安全问题。
* */
public class Account {
//    账号
    private String actno;
//    余额
    private double balance;

//    对象
    Object obj = new Object();      // 实例变量（Account对象是多线程共享的，Account对象中的实例变量obj也是共享的。）

    public Account() {
    }

    public Account(String actno, double balance) {
        this.actno = actno;
        this.balance = balance;
    }

    public String getActno() {
        return actno;
    }

    public void setActno(String actno) {
        this.actno = actno;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

//    取款的方法
    public void withdraw(double money) {
//        以下这几行代码必须是线程排队的，不能并发。
//        一个线程把这里的代码全部执行结束之后，另一个线程才能进来。
        /*
        *   synchronized(){
        *       // 线程同步代码块
        *   }
        *   synchronized后面小括号中传的这个“数据”是相当关键的，这个数据必须是多线程共享的数据，才能达到多线程排队。
        *   ()中写什么？
        *       主要是想要哪些线程同步？
        *       假设thread1、thread2、thread3、thread4、thread5有这5个线程，
        *       只希望thread1、thread2、thread3排队，thread4、thread5不需要排队，怎么办？
        *           一定要在()中写一个thread1、thread2、thread3共享的对象，而这个对象对于thread4、thread5来说并不是共享的。
        *   这里的共享对象是：账户对象
        *   账户对象是共享的，那么this就是账户对象
        *   这里不一定是this，只要是多线程共享的那个对象就行
        *
        *   在java语言中，任何一个对象都有“一把锁”，其实这把锁就是标记。（只是把它叫做锁）
        *   100个对象，100把锁，1个对象对应一把锁。
        *
        *   以下代码的执行原理？
        *       1、假设thread1和thread2线程并发，开始执行以下代码的时候，肯定有一个先一个后。
        *       2、假设thread1先执行了，遇到了synchronized，这个时候自动找“后面共享对象”的对象锁，找到之后并占有这把锁，然后执行
        *       同步代码块中的程序，在程序执行过程中一直都是占有这把锁的，直到同步代码块代码结束，这把锁才会释放。
        *       3、假设thread1已经占有了这把锁，此时thread2也遇到synchronized关键字，也会去占有后面共享对象的这把锁，结果这把锁
        *       被thread1占有，thread2只能在同步代码块外面等待thread1的结束，直到thread1把同步代码块执行结束了，thread1会归还这
        *       把锁，此时thread2终于等到这把锁，然后thread2占有这把锁之后，进入同步代码块执行程序。
        *
        *   这样就达到了线程排队执行。
        *   这里需要注意的是：这个共享对象一定要选好了，这个共享对象一定是你需要排队执行的这些线程对象锁共享的
        * */
//        Object obj2 = new Object();     // 这样编写就不安全，因为obj是局部变量，不是共享对象
//        synchronized(this) {
//        synchronized(obj) {
//        synchronized(obj2) {      // 这样编写就不安全，因为obj是局部变量，不是共享对象
//        synchronized("abc") {      // "abc"在字符串常量池当中
//        synchronized(null) {      // 报错：空指针
//        synchronized(this) {
            double before = this.getBalance();

            double after = before - money;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.setBalance(after);
//        }
    }
}
