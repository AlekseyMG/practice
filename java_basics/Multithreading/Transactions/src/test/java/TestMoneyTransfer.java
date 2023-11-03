import static junit.framework.TestCase.assertEquals;
import org.junit.Test;

public class TestMoneyTransfer {
    Bank bank;
    private final int accountsInBank = 5; //количество аккаунтов в тестовом банке
    private final int transfersCount = 100; //количество переводов
    private final String fromAccount = "3"; //из какого аккаунта будут переводы
    private final String toAccount = "1"; //в какой аккаунт
    private final int accountStartMoney = 10_000_000; //начальный баланс тестовых аккаунтов
    private final int transferredMoney = 11_111; //тестовая сумма перевода без проверок на фрод
    private final int transferredMoneyWithBlock = 111_111; //тестовая сумма с проверками на фрод
    private final int maxRandomTransfer = 70_000; //максимальная сумма перевода для теста рандомных переводов

    //нужно ли разблокировать аккаунты во время тестов
    //для следущего перевода при срабатывании фрод блокировки
    private final boolean withUnblock = true;

    @Test //переводы маленьких сумм без выделения отдельных потоков
    public void TransferMoney() throws InterruptedException {
        setBank();
        System.out.println("--------------------Transfer Money---------------------");
        System.out.println("переводы маленьких сумм без выделения отдельных потоков");
        System.out.println("-------------------------------------------------------");
        for (int i = 0; i < transfersCount; i++) {
            bank.transfer(fromAccount, toAccount, transferredMoney);
        }
        for (int i = 0; i < accountsInBank; i++) {
            System.out.println("Аккаунт " + i + " - Баланс: " + bank.getBalance(Integer.toString(i)));
        }
        System.out.println("Общий баланс аккаунтов: " + bank.getSumAllAccounts() + "\n");
        assertEquals(10_000_000 * accountsInBank, bank.getSumAllAccounts());
    }

    @Test //переводы больших сумм без выделения отдельных потоков
    public void TransferMoneyWithBlocks() throws InterruptedException {
        setBank();
        System.out.println("-------------Transfer Money With Blocks--------------");
        System.out.println("переводы больших сумм без выделения отдельных потоков");
        System.out.println("-----------------------------------------------------");
        for (int i = 0; i < transfersCount; i++) {
            bank.transfer(fromAccount, toAccount, transferredMoneyWithBlock);
            if (withUnblock) {
                bank.getAccount(fromAccount).unblock();
                bank.getAccount(toAccount).unblock();
                System.out.println("Разблокировали аккаунты!");
            }
        }
        for (int i = 0; i < accountsInBank; i++) {
            bank.getAccount(Integer.toString(i)).unblock();
            System.out.println("Аккаунт " + i + " - Баланс: " + bank.getBalance(Integer.toString(i)));
        }
        System.out.println("Общий баланс аккаунтов: " + bank.getSumAllAccounts() + "\n");
        assertEquals(10_000_000 * accountsInBank, bank.getSumAllAccounts());
    }

    @Test //переводы маленьких сумм с выделением отдельных потоков
    public void MultithreadingTransferMoney() throws InterruptedException {
        setBank();
        System.out.println("------------Multithreading Transfer Money-------------");
        System.out.println("переводы маленьких сумм с выделением отдельных потоков");
        System.out.println("------------------------------------------------------");
        for (int i = 0; i < transfersCount; i++) {
            new Thread(() -> {
                try {
                    bank.transfer(fromAccount, toAccount, transferredMoney);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(1000);
        for (int i = 0; i < accountsInBank; i++) {
            System.out.println("Аккаунт " + i + " - Баланс: " + bank.getBalance(Integer.toString(i)));
        }
        System.out.println("Общий баланс аккаунтов: " + bank.getSumAllAccounts() + "\n");
        assertEquals(10_000_000 * accountsInBank, bank.getSumAllAccounts());
    }

    @Test //переводы больших сумм с выделением отдельных потоков
    public void MultithreadingTransferMoneyWithBlocks() throws InterruptedException {
        setBank();
        System.out.println("-----Multithreading Transfer Money With Blocks------");
        System.out.println("переводы больших сумм с выделением отдельных потоков");
        System.out.println("----------------------------------------------------");
        for (int i = 0; i < transfersCount; i++) {
            new Thread(() -> {
                try {
                    bank.transfer(fromAccount, toAccount, transferredMoneyWithBlock);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println("\nВсе потоки запущены! Ожидаем завершения потоков...\n");

        if (withUnblock) {
            for (int i = 0; i < transfersCount / 2; i++) {
                Thread.sleep(2001);
                bank.getAccount(fromAccount).unblock();
                bank.getAccount(toAccount).unblock();
                System.out.println("-- Разблокировали аккаунты! --");
            }
            System.out.println("Больше не разблокируем. Ожидаем завершения потоков...");
            //Thread.sleep(transfersCount * transfersCount);
        } else {
            Thread.sleep(transfersCount * 1001);
        }
        System.out.println();
        for (int i = 0; i < accountsInBank; i++) {
            bank.getAccount(Integer.toString(i)).unblock();
            System.out.println("Аккаунт " + i + " - Баланс: " + bank.getBalance(Integer.toString(i)));
        }
        System.out.println("Общий баланс аккаунтов: " + bank.getSumAllAccounts() + "\n");
        assertEquals(10_000_000 * accountsInBank, bank.getSumAllAccounts());
    }
    @Test //переводы случайных сумм между случайными аккаунтами
    public void MultithreadingRandomTransferMoney() throws InterruptedException {
        setBank();
        System.out.println("-------Multithreading Random Transfer Money--------");
        System.out.println("переводы случайных сумм между случайными аккаунтами");
        System.out.println("---------------------------------------------------");
        for (int i = 0; i < transfersCount; i++) {
            String randomAccount1 = Integer.toString((int) (Math.random() * accountsInBank));
            String randomAccount2 = Integer.toString((int) (Math.random() * accountsInBank));
            new Thread(() -> {
                try {
                    bank.transfer(randomAccount1, randomAccount2, (long) (Math.random() * maxRandomTransfer));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println("\nВсе потоки запущены! Ожидаем завершения потоков...\n");

        if (withUnblock) {
            for (int i = 0, j = 0; i < transfersCount / 2; i++, j++) {
                bank.getAccount(Integer.toString(j)).unblock();
                if (j >= accountsInBank - 1) {
                    j = 0;
                    System.out.println("-- Разблокировали все аккаунты! --");
                    Thread.sleep(2000);
                }
            }
            System.out.println("Больше не разблокируем. Ожидаем завершения потоков...");
            Thread.sleep(transfersCount / 3 * 1000);
        } else {
            Thread.sleep(transfersCount / 3 * 1000);
        }
        System.out.println();
        for (int i = 0; i < accountsInBank; i++) {
            bank.getAccount(Integer.toString(i)).unblock();
            System.out.println("Аккаунт " + i + " - Баланс: " + bank.getBalance(Integer.toString(i)));
        }
        System.out.println("Общий баланс аккаунтов: " + bank.getSumAllAccounts() + "\n");
        assertEquals(10_000_000 * accountsInBank, bank.getSumAllAccounts());
    }

    private void setBank() {
        bank = new Bank();
        for (int i = 0; i < accountsInBank; i++) {
            Account account = new Account();
            account.setMoney(accountStartMoney);
            account.setAccNumber(Integer.toString(i));
            bank.addAccount(account);
        }
    }
}
