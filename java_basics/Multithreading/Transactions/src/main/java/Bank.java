import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class Bank {

    private final Map<String, Account> accounts = new ConcurrentHashMap<>();
    private final Random random = new Random();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Account from = accounts.get(fromAccountNum);
        Account to = accounts.get(toAccountNum);
        boolean isFraud = false;

        if (amount > 50000 && !from.equals(to)) {
            isFraud = isFraud(fromAccountNum, toAccountNum, amount);
        }
        if (!isFraud) {
            if (!from.isBlocked() && !to.isBlocked() && !from.equals(to)) {
                synchronized (accounts) {
                    from.setMoney(from.getMoney() - amount);
                    to.setMoney(to.getMoney() + amount);
                }
                System.out.println("Успешно переведено " + amount +
                        " с аккаунта " + fromAccountNum + " на акканут " + toAccountNum);
            } else {
                System.out.println("Не удалось выполнить перевод c " +
                        fromAccountNum + " на " + toAccountNum + " из-за блокировок!");
            }
        }

        if (isFraud) {
            System.out.println("ФРОД! Перевод не выполнен! Блокируем аккаунты " + fromAccountNum + " и " + toAccountNum + "!");
            from.block();
            to.block();
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public Account getAccount(String accountNum) {
        return accounts.get(accountNum);
    }

    public long getSumAllAccounts() {
        AtomicLong sum = new AtomicLong();
        accounts.forEach((s, account) -> {
            sum.addAndGet(account.getMoney());
        });
        return sum.get();
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccNumber(), account);
    }
}
