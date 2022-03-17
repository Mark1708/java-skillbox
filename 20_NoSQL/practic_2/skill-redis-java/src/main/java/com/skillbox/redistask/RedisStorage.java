package com.skillbox.redistask;

import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.Random;

public class RedisStorage {

    private RedissonClient redisson;
    private RKeys rKeys;
    public RScoredSortedSet<String> onlineUsers;
    private final static String KEY = "ONLINE_USERS";

    public double getTime() {
        return (double) System.currentTimeMillis() / 1000;
    }

    void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setPassword("pass");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            System.out.println("Не удалось подключиться к Redis");
            System.out.println(Exc.getMessage());
        }

        rKeys = redisson.getKeys();
        onlineUsers = redisson.getScoredSortedSet(KEY);
        rKeys.delete(KEY);
    }

    // Фиксирует посещение пользователем страницы
    void logPageVisit(int userId) {
        //ZADD ONLINE_USERS
        onlineUsers.add(getTime(), String.valueOf(userId));
    }

    public void showFirstUser() {
        String userId = onlineUsers.first();
        System.out.printf(" - На главной странице показываем пользователя %s%n", userId);
        onlineUsers.removeAsync(userId);
        onlineUsers.addAsync(onlineUsers.lastScore() + 1, userId);
    }

    public void reLocationUser() throws InterruptedException {
        int luckyCount = new Random().nextInt(9) + 1;
        if (luckyCount >= 9) {
            int luckyNumber = new Random().nextInt(onlineUsers.size()) + 1;
            onlineUsers.removeAsync(String.valueOf(luckyNumber));
            onlineUsers.addAsync(onlineUsers.firstScore() - 1, String.valueOf(luckyNumber));
            System.out.println(" - Пользователь  " + luckyNumber +  " Оплатил подписку");
            Thread.sleep(1000);
        }
    }


    void workRound() throws InterruptedException {
        for (;;) {
            showFirstUser();
            reLocationUser();
        }
    }
}
