package com.skillbox.redistask;

public class RedisTest {

    // Запуск докер-контейнера:
    // docker-compose up --build

    public static void main(String[] args) throws InterruptedException {

        RedisStorage redis = new RedisStorage();
        redis.init();

        for (int i = 1; i <= 20; i++) {
            redis.logPageVisit(i);
        }
        redis.workRound();
    }
}
