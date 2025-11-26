package com.mini.server;

/**
 * @author liucaiwen
 * 2025/11/26
 */
public class RedisServerLauncher {

    public static void main(String[] args) {
        RedisServer redisServer = new RedisMiniServer("localhost", 6379);
        redisServer.start();
    }
}
