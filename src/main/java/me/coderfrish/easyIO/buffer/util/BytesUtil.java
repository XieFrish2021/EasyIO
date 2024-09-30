package me.coderfrish.easyIO.buffer.util;

@SuppressWarnings("all")
public class BytesUtil {
    public static byte[] intToBytes(int value) {
        return new byte[] {
                (byte) (value >> 24),
                (byte) (value >> 16),
                (byte) (value >> 8),
                (byte) value
        };
    }

    public static byte[] longToBytes(long value) {
        return new byte[] {
                (byte) (value >> 56),
                (byte) (value >> 48),
                (byte) (value >> 40),
                (byte) (value >> 32),
                (byte) (value >> 24),
                (byte) (value >> 16),
                (byte) (value >> 8),
                (byte) value
        };
    }

    public static byte[] shortToBytes(short value) {
        return new byte[] {
                (byte) (value >> 8),
                (byte) value
        };
    }
}
