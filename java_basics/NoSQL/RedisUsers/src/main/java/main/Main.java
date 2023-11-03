package main;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int registeredUsers = 20;

        RedisStorage redis = new RedisStorage();
        redis.init();

        for (int i = 0; i < registeredUsers; i++) {
            redis.addUser(String.valueOf(i + 1));
            Thread.sleep(3);
        }

        AtomicInteger userCount = new AtomicInteger();
        AtomicInteger randomPayedUser = new AtomicInteger();
        AtomicInteger randomUserInQueue = new AtomicInteger((int) (Math.random() * 10));
        ArrayList<Integer> payedUsers = new ArrayList<>();

        while (true) {
            userCount.set(0);
            payedUsers.clear();
            redis.getRegisteredUsers().forEach(userName -> {
                if (userCount.get() == randomUserInQueue.get() ||
                        userCount.get() + 10 == randomUserInQueue.get()) {
                    randomPayedUser.set((int) (Math.random() * 19) + 1);
                    payedUsers.add(randomPayedUser.get());
                    out.println(">Пользователь " + randomPayedUser + " оплатил платную услугу");
                    redis.setLast(String.valueOf(randomPayedUser.get()));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (userCount.get() == 10) {
                    randomUserInQueue.set((int) (Math.random() * 10) + 10);
                }
                if (!payedUsers.contains(Integer.parseInt(userName))) {
                    out.println("— На главной странице показываем пользователя " + userName);
                    redis.setLast(userName);
                }
                userCount.getAndIncrement();
            });
        }
    }
}