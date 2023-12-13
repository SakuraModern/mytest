package threadsafe03;

/*
*   银行账户
*       不使用线程同步机制，多线程对同一个账户进行取款，出现线程安全问题。
* */
public class Account {
    private String actno;
    private double balance;

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

    /*
    *   取款方法
    *       可以在实例方法上使用synchronized。
    *
    *       synchronized出现在实例方法上，一定锁的是this，不能是其他的对象了，所以这种方式不灵活。
    *
    *       另外一个缺点：synchronized出现在实例方法上，表示整个方法体都需要同步，可能会无故扩大同步的范围，
    *   导致程序的执行效率降低，所以这种方式不常用。
    *
    *       synchronized使用在实例方法上有什么优点？
    *           代码写的少了，节俭了。
    *
    *       如果共享的对象就是this，并且需要同步的代码块是整个方法体，建议使用这种方式。
    * */
    public synchronized void withdraw(double money) {
        double before = this.getBalance();

        double after = before - money;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.setBalance(after);
    }
}
