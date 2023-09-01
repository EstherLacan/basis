package com.jiangfw.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @Author: jiangfw
 * @Date: 2021-07-14
 */
public class ScattingAndGatheringDemo {

    public static void main(String[] args) throws IOException {
//        Buffer buffer = IntBuffer.allocate(5);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7777);
        serverSocketChannel.socket().bind(inetSocketAddress);

        ByteBuffer[] buffers = new ByteBuffer[2];
        buffers[0] = ByteBuffer.allocate(5);
        buffers[1] = ByteBuffer.allocate(3);


        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLength = 8;
        System.out.println("客户端连入...");
        while (true) {

            int readLength = 0;
            while (readLength < messageLength) {

                long read = socketChannel.read(buffers);
                readLength += read;
                System.out.println("read length is " + readLength);
                Arrays.stream(buffers).map(e -> "position:" + e.position() + ",limit" + e.limit()).forEach(System.out::println);

            }
            Arrays.asList(buffers).forEach(Buffer::flip);

            long writeLength = 0;
            while (writeLength < messageLength) {
                long write = socketChannel.write(buffers);
                writeLength += write;
                System.out.println("write length is " + readLength);

            }


            Arrays.asList(buffers).forEach(Buffer::clear);
            System.out.println("read length is " + readLength + ",write length is " + writeLength);
        }
    }

}
