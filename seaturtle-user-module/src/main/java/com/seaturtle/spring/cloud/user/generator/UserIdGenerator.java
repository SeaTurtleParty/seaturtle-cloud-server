package com.seaturtle.spring.cloud.user.generator;

import java.util.Random;

/**
 * author Theft
 * date 2018/9/24
 */
public class UserIdGenerator {

    private static final Random random = new Random();

    public static long generUserId() {
        long currentTime = System.currentTimeMillis() / 1000;
        int seed = random.nextInt(10000);
        return currentTime - (seed * 1024);
    }

}
