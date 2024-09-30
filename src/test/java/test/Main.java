package test;

import me.coderfrish.easyIO.api.ByteBuffer;

public class Main {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate();

        buffer.writeInt(10086);
        System.out.println(buffer.readInt());
    }
}
