package threadsafe;

/*
*   银行账户
*       不使用线程同步机制，多线程对同一个账户进行取款，出现线程安全问题。
* */
public class Account {
//    账号
    private String actno;
//    余额
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

//    取款的方法
    public void withdraw(double money) {
//        thread1和thread2并发这个方法（thread1和thread2是两个栈，两个栈操作堆中同一个对象）
//        取款之前的余额
        double before = this.getBalance();

//        取款之后的余额
        double after = before - money;

//        在这里模拟一下网络延迟，100%会出现问题
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        更新余额
        this.setBalance(after);
    }
}
