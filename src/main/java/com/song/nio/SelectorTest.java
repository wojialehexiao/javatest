package com.song.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorTest {

    public static void main(String[] args) throws Exception {
        startServer();
    }

    public static void startServer() throws Exception{
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.bind(new InetSocketAddress(8000));

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                if (key.isAcceptable()) {
                    //
                    System.out.println("acceptable");
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isConnectable()) {
                    System.out.println("connectable");


                } else if (key.isReadable()) {
                    System.out.println("readable");

                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int length = 0;

                    while ((length = socketChannel.read(buffer)) > 0){
                        buffer.flip();
                        System.out.println(new String(buffer.array(),0, length));
                        buffer.clear();
                    }

                    socketChannel.close();

                } else if (key.isWritable()) {
                    System.out.println("writable");
                }
            }

            iterator.remove();
        }

    }
}
