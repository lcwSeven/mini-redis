package com.mini.server;

import com.mini.server.handler.StringHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liucaiwen
 * 2025/11/26
 */
@Slf4j
@Data
public class RedisMiniServer implements RedisServer {

    private String host;
    private int port;
    // bossGroup 用于接收连接
    private EventLoopGroup bossGroup;
    // workGroup 用于处理任务
    private EventLoopGroup workerGroup;

    private Channel serverChannel;

    public RedisMiniServer(String host, int port) {
        this.host = host;
        this.port = port;
        this.bossGroup = new NioEventLoopGroup(1);
        this.workerGroup = new NioEventLoopGroup(4);
    }

    @Override
    public void start() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new StringHandler());
                        ch.pipeline().addLast(new StringEncoder());
                    }
                });
        // 绑定host 和 端口
        try {
            serverChannel = bootstrap.bind(host, port).sync().channel();
            log.info("RedisMiniServer started on port {}", port);
        } catch (InterruptedException e) {
            stop();
            Thread.currentThread().interrupt();
        }


    }

    @Override
    public void stop() {
        try {
            // 关闭顺序和申请顺序是相反的
            if (serverChannel != null) {
                serverChannel.close();
            }
            if (workerGroup != null) {
                workerGroup.shutdownGracefully().sync();
            }
            if (bossGroup != null) {
                bossGroup.shutdownGracefully().sync();
            }
        } catch (Exception e) {
            log.error("RedisMiniServer stop error", e);
        }


    }


}
