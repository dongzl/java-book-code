package com.mongodb.wire.protocol;

import io.netty.buffer.ByteBuf;

/**
 * @author dongzonglei
 * @description
 * @date 2019-10-20 21:52
 */
public interface PacketPayload extends AutoCloseable {

    /**
     * Get byte buf.
     *
     * @return byte buf
     */
    ByteBuf getByteBuf();
}
