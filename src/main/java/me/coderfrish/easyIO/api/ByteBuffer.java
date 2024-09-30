package me.coderfrish.easyIO.api;

import me.coderfrish.easyIO.buffer.DefaultByteBuf;

@SuppressWarnings("all")
public abstract class ByteBuffer {
    public abstract int readInt();
    public abstract long readLong();
    public abstract short readShort();
    public abstract int readUnsignedShort();
    public abstract byte readByte();
    public abstract ByteBuffer readBytes(byte[] bytes);

    public abstract ByteBuffer writeInt(int value);
    public abstract ByteBuffer writeLong(long value);
    public abstract ByteBuffer writeShort(short value);
    public abstract ByteBuffer writeByte(byte value);
    public abstract ByteBuffer writeBytes(byte[] value);
    public abstract ByteBuffer writeBytes(java.nio.ByteBuffer buffer);
    public abstract ByteBuffer writeBytes(ByteBuffer buffer);

    public abstract int capacity();
    public abstract int readerIndex();
    public abstract int writerIndex();
    public abstract byte[] array();
    public abstract void clear();
    public abstract ByteBuffer copy();
    public abstract ByteBuffer reset();
    public abstract ByteBuffer readerIndex(int i);

    public static ByteBuffer allocate(int capacity) {
        return new DefaultByteBuf(capacity);
    }

    public static ByteBuffer allocate() {
        return allocate(1024);
    }
}
