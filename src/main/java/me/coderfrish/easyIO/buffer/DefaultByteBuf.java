package me.coderfrish.easyIO.buffer;

import me.coderfrish.easyIO.api.ByteBuffer;
import me.coderfrish.easyIO.buffer.exceptions.ReaderIndexOutOfBoundsException;
import me.coderfrish.easyIO.buffer.exceptions.WriterIndexOutOfBoundsException;
import me.coderfrish.easyIO.buffer.util.BytesUtil;

@SuppressWarnings("all")
public class DefaultByteBuf extends ByteBuffer {
    private final int capacity;
    private int readerIndex = 0;
    private int writerIndex = 0;
    private final java.nio.ByteBuffer buffer;

    public DefaultByteBuf(int capacity) {
        this.capacity = capacity;
        this.buffer = java.nio.ByteBuffer.allocate(this.capacity);
    }

    @Override
    public int readInt() {
        int anInt = this.buffer.getInt();
        byte[] bytes = BytesUtil.intToBytes(anInt);
        this.readerIndex = this.readerIndex + bytes.length;
        if (this.readerIndex - 1>= this.writerIndex) throw new ReaderIndexOutOfBoundsException();
        return anInt;
    }

    @Override
    public long readLong() {
        long aLong = this.buffer.getLong();
        byte[] bytes = BytesUtil.longToBytes(aLong);
        this.readerIndex = this.readerIndex + bytes.length;
        if (this.readerIndex - 1>= this.writerIndex) throw new ReaderIndexOutOfBoundsException();
        return aLong;
    }

    @Override
    public short readShort() {
        short aShort = this.buffer.getShort();
        byte[] bytes = BytesUtil.shortToBytes(aShort);
        this.readerIndex = this.readerIndex + bytes.length;
        if (this.readerIndex - 1>= this.writerIndex) throw new ReaderIndexOutOfBoundsException();
        return aShort;
    }

    @Override
    public int readUnsignedShort() {
        return readShort();
    }

    @Override
    public byte readByte() {
        byte b = this.buffer.get();
        this.readerIndex = this.readerIndex + 1;
        if (this.readerIndex - 1>= this.writerIndex) throw new ReaderIndexOutOfBoundsException();
        return b;
    }

    @Override
    public ByteBuffer readBytes(byte[] bytes) {
        this.buffer.get(bytes);
        this.readerIndex = this.readerIndex + bytes.length;
        if (this.readerIndex - 1>= this.writerIndex) throw new ReaderIndexOutOfBoundsException();
        return this;
    }

    @Override
    public ByteBuffer writeInt(int value) {
        byte[] bytes = BytesUtil.intToBytes(value);
        if (this.writerIndex + bytes.length >= this.capacity) throw new WriterIndexOutOfBoundsException();
        this.buffer.putInt(this.writerIndex, value);
        this.writerIndex = this.writerIndex + bytes.length;
        return this;
    }

    @Override
    public ByteBuffer writeLong(long value) {
        byte[] bytes = BytesUtil.longToBytes(value);
        if (this.writerIndex + bytes.length >= this.capacity) throw new WriterIndexOutOfBoundsException();
        this.buffer.putLong(this.writerIndex, value);
        this.writerIndex = this.writerIndex + bytes.length;
        return this;
    }

    @Override
    public ByteBuffer writeShort(short value) {
        byte[] bytes = BytesUtil.shortToBytes(value);
        if (this.writerIndex + bytes.length >= this.capacity) throw new WriterIndexOutOfBoundsException();
        this.buffer.putShort(this.writerIndex, value);
        this.writerIndex = this.writerIndex + bytes.length;
        return this;
    }

    @Override
    public ByteBuffer writeByte(byte value) {
        if (this.writerIndex + Byte.BYTES >= this.capacity) throw new WriterIndexOutOfBoundsException();
        this.buffer.put(this.writerIndex, value);
        this.writerIndex = this.writerIndex + 1;
        return this;
    }

    @Override
    public ByteBuffer writeBytes(byte[] value) {
        if (this.writerIndex + value.length >= this.capacity) throw new WriterIndexOutOfBoundsException();
        this.buffer.put(this.writerIndex, value);
        this.writerIndex = this.writerIndex + value.length;
        return this;
    }

    @Override
    public ByteBuffer writeBytes(java.nio.ByteBuffer buffer) {
        byte[] array = buffer.array();
        this.writeBytes(array);
        return this;
    }

    @Override
    public ByteBuffer writeBytes(ByteBuffer buffer) {
        this.writeBytes(buffer.array());
        return this;
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public int readerIndex() {
        return this.readerIndex;
    }

    @Override
    public int writerIndex() {
        return this.writerIndex;
    }

    @Override
    public byte[] array() {
        return this.buffer.array();
    }

    @Override
    public void clear() {
        this.buffer.clear();
    }

    @Override
    public ByteBuffer copy() {
        return new DefaultByteBuf(this.capacity)
                .writeBytes(this.buffer.slice());
    }

    @Override
    public ByteBuffer reset() {
        this.readerIndex = 0;
        return this;
    }

    @Override
    public ByteBuffer readerIndex(int i) {
        this.readerIndex = i;
        return this;
    }
}
