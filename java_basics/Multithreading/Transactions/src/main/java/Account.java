public class Account {

    private long money;
    private String accNumber;
    private volatile boolean isBlocked = false;

    public synchronized long getMoney() {
        if (isBlocked) {
            System.out.println("Аккаунт " + accNumber + " заблокирован!");
            return 0;
        } else {
            return money;
        }
    }

    public synchronized void setMoney(long money) {
        if (isBlocked) {
            System.out.println("Аккаунт " + accNumber + " заблокирован!");
        } else {
            this.money = money;
        }
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        if (isBlocked) {
            System.out.println("Аккаунт " + accNumber + " заблокирован!");
        } else {
            this.accNumber = accNumber;
        }
    }

    public synchronized boolean isBlocked() {
        return isBlocked;
    }

    public synchronized void block() {
        isBlocked = true;
    }

    public synchronized void unblock() {
        isBlocked = false;
    }
}
