package main;

import static java.lang.System.out;
import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;
import java.util.Date;

public class RedisStorage {
    private RedissonClient redisson;
    private RKeys rKeys;
    private RScoredSortedSet<String> registeredUsers;
    private final static String KEY = "REGISTERED_USERS";

    private double getTime() {
        return new Date().getTime() / 1000.;
    }
    void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }
        rKeys = redisson.getKeys();
        registeredUsers = redisson.getScoredSortedSet(KEY);
        rKeys.delete(KEY);
    }
    void shutdown() {
        redisson.shutdown();
    }
    void addUser(String userName)
    {
        registeredUsers.add(getTime(), userName);
    }
    void setLast(String userName)
    {
        registeredUsers.addScore(userName, getTime() - registeredUsers.getScore(userName));
    }
    public RScoredSortedSet<String> getRegisteredUsers() {
        return registeredUsers;
    }
}
