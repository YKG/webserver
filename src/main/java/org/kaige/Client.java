package org.kaige;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client
{
    public static void main(String args[]) throws Exception {
        String address = "localhost";
        int port = 80;
        String request = "GET /\n";

        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress(address, port));

        ByteBuffer buf = ByteBuffer.allocate(128);
        buf.clear();
        buf.put(request.getBytes());

        buf.flip();

        while (buf.hasRemaining()) {
            channel.write(buf);
        }

        ByteBuffer res = ByteBuffer.allocate(128);
        int byteRead = 0;
        while (byteRead == 0) {
            byteRead = channel.read(res);
            Thread.sleep(10);
        }
        System.out.println(byteRead + " bytes response");

        res.rewind();
        byte[] data = new byte[byteRead];
        res.get(data);

        Helper.dumpAsHex(data);
        Helper.dumpAsChar(data);

        System.out.println(new String(res.array(), 0, byteRead));
    }
}
