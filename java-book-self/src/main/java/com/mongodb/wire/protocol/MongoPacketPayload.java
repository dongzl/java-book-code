package com.mongodb.wire.protocol;

import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author dongzonglei
 * @description
 * @date 2019-10-20 21:54
 */
@RequiredArgsConstructor
@Getter
public class MongoPacketPayload implements PacketPayload {
    
    private final ByteBuf byteBuf;

    /**
     * Read 1 byte fixed length integer from byte buffers.
     *
     * @return 1 byte fixed length integer
     */
    public int readInt1() {
        return byteBuf.readByte() & 0xff;
    }

    /**
     * Write 1 byte fixed length integer to byte buffers.
     *
     * @param value 1 byte fixed length integer
     */
    public void writeInt1(final int value) {
        byteBuf.writeByte(value);
    }

    /**
     * Read 2 byte fixed length integer from byte buffers.
     *
     * @return 2 byte fixed length integer
     */
//    public int readInt2() {
//        return byteBuf.readShort() & 0xffff;
//    }

    /**
     * Write 2 byte fixed length integer to byte buffers.
     *
     * @param value 2 byte fixed length integer
     */
//    public void writeInt2(final int value) {
//        byteBuf.writeShort(value);
//    }

    /**
     * Read 4 byte fixed length integer from byte buffers.
     *
     * @return 4 byte fixed length integer
     */
    public int readInt4() {
        return byteBuf.readInt();
    }

    /**
     * Write 4 byte fixed length integer to byte buffers.
     *
     * @param value 4 byte fixed length integer
     */
    public void writeInt4(final int value) {
        byteBuf.writeInt(value);
    }

    /**
     * Read 8 byte fixed length integer from byte buffers.
     *
     * @return 8 byte fixed length integer
     */
    public long readInt8() {
        return byteBuf.readLong();
    }

    /**
     * Write 8 byte fixed length integer to byte buffers.
     *
     * @param value 8 byte fixed length integer
     */
    public void writeInt8(final long value) {
        byteBuf.writeLong(value);
    }

    /**
     * Write variable length bytes to byte buffers.
     *
     * @param value fixed length bytes
     */
    public void writeBytes(final byte[] value) {
        byteBuf.writeBytes(value);
    }

    /**
     * Bytes before zero.
     *
     * @return the number of bytes before zero
     */
    public int bytesBeforeZero() {
        return byteBuf.bytesBefore((byte) 0);
    }

    /**
     * Read null terminated string from byte buffers.
     *
     * @return null terminated string
     */
    public String readStringNul() {
        byte[] result = new byte[byteBuf.bytesBefore((byte) 0)];
        byteBuf.readBytes(result);
        byteBuf.skipBytes(1);
        return new String(result);
    }

    /**
     * Write null terminated string to byte buffers.
     *
     * @param value null terminated string
     */
    public void writeStringNul(final String value) {
        byteBuf.writeBytes(value.getBytes());
        byteBuf.writeByte(0);
    }

    /**
     * Write rest of packet string to byte buffers.
     *
     * @param value rest of packet string
     */
    public void writeStringEOF(final String value) {
        byteBuf.writeBytes(value.getBytes());
    }

    /**
     * Skip reserved from byte buffers.
     *
     * @param length length of reserved
     */
    public void skipReserved(final int length) {
        byteBuf.skipBytes(length);
    }

    @Override
    public void close() throws Exception {
        byteBuf.release();
    }
}
