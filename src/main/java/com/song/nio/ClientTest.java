package com.song.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientTest {
    public static void main(String[] args) throws Exception {
        startClient();
    }


    public static void startClient() throws Exception{
        InetSocketAddress address = new InetSocketAddress("localhost", 8000);

        SocketChannel socketChannel = SocketChannel.open(address);
        socketChannel.configureBlocking(false);

        while (! socketChannel.finishConnect()){
        }

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put("hello world".getBytes());
        buffer.flip();

        socketChannel.write(buffer);
        socketChannel.shutdownInput();
        socketChannel.close();

    }

}
