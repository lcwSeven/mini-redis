package com.mini.server.protocal;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author liucaiwen
 * 2025/12/3
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SimpleString extends Resp{

    private String buf;

    public SimpleString(String str) {
        this.buf = buf;
    }

    @Override
    public void encode(Resp resp, ByteBuf buf) {

    }
}
