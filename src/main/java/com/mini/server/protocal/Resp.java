package com.mini.server.protocal;

import io.netty.buffer.ByteBuf;


/**
 * @author liucaiwen
 * 2025/12/3
 */
public abstract class Resp {

    // SimpleString "+OK\r\n"
    // Errors "Error message\r\n"
    // RedisInteger :0\r\n

    public static final byte[] CRLF = "\r\n".getBytes();

    public static Resp decode(ByteBuf buf) {
        if (buf.readableBytes() <= 0) {
            throw new RuntimeException("没有一个完整的命令");
        }
        char b = (char) buf.readByte();

        switch (b) {
            case '+':
                return new SimpleString(getString(buf));
            case '-':
                return new Errors();
        }
        return null;
    }

    private static String getString(ByteBuf buf) {
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        return new String(bytes);
    }

    public abstract void encode(Resp resp, ByteBuf buf);
}
