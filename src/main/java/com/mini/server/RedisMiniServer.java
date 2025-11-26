package com.mini.server;

/**
 * @author liucaiwen
 * 2025/11/26
 */
public class RedisMiniServer implements RedisServer {

    private String host;
    private int port;

    public RedisMiniServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
