package com.song.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest {

    public static void main(String[] args) throws Exception {

        mapMethod();

        nomapMethod();

        oioMethod();

        transferFromMethod();
        transferToMethod();

    }

    public static void transferToMethod() throws Exception {


        long l = System.currentTimeMillis();
        RandomAccessFile aFile = new RandomAccessFile("/Users/song/Downloads/Spark-The-Definitive-Guide-master.zip", "rw");
        RandomAccessFile cpTo = new RandomAccessFile("data/test5.zip", "rw");

        FileChannel channel = aFile.getChannel();

        long count = channel.size();


        channel.transferTo(0, count, cpTo.getChannel());


        aFile.close();
        channel.close();

        System.out.println("spend time: [" + (System.currentTimeMillis() - l) + "]");
    }


    public static void transferFromMethod() throws Exception {


        long l = System.currentTimeMillis();
        RandomAccessFile aFile = new RandomAccessFile("/Users/song/Downloads/Spark-The-Definitive-Guide-master.zip", "rw");
        RandomAccessFile cpTo = new RandomAccessFile("data/test4.zip", "rw");

        FileChannel channel = aFile.getChannel();

        long count = channel.size();

        cpTo.getChannel().transferFrom(channel, 0, count);


        aFile.close();
        channel.close();

        System.out.println("spend time: [" + (System.currentTimeMillis() - l) + "]");
    }

    public static void oioMethod() throws Exception {


        RandomAccessFile bFile = new RandomAccessFile("/Users/song/Downloads/Spark-The-Definitive-Guide-master.zip", "rw");
        long l = System.currentTimeMillis();
        FileInputStream aFile = new FileInputStream("/Users/song/Downloads/Spark-The-Definitive-Guide-master.zip");
        FileOutputStream cpTo = new FileOutputStream("data/test3.zip");


        byte[] buffer = new byte[(int) bFile.length()];

        aFile.read(buffer);

        cpTo.write(buffer);


        aFile.close();
        cpTo.flush();
        cpTo.close();

        System.out.println("spend time: [" + (System.currentTimeMillis() - l) + "]");
    }


    public static void nomapMethod() throws Exception {

        long l = System.currentTimeMillis();
        RandomAccessFile aFile = new RandomAccessFile("/Users/song/Downloads/Spark-The-Definitive-Guide-master.zip", "rw");
        RandomAccessFile cpTo = new RandomAccessFile("data/test2.zip", "rw");

        FileChannel channel = aFile.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate((int) aFile.length());
        buffer.clear();
        channel.read(buffer);

        buffer.flip();

        cpTo.getChannel().write(buffer);


        aFile.close();
        channel.close();


        System.out.println("spend time: [" + (System.currentTimeMillis() - l) + "]");
    }

    public static void mapMethod() throws Exception {

        long l = System.currentTimeMillis();
        RandomAccessFile aFile = new RandomAccessFile("/Users/song/Downloads/Spark-The-Definitive-Guide-master.zip", "rw");
        RandomAccessFile cpTo = new RandomAccessFile("data/test.zip", "rw");


        FileChannel channel = aFile.getChannel();

        MappedByteBuffer mmb = channel.map(FileChannel.MapMode.READ_ONLY, 0, aFile.length());

//        byte[] array = mmb.array();

//        mmb.flip();

        FileChannel channel1 = cpTo.getChannel();

        channel1.write(mmb);


        aFile.close();
        channel.close();
        cpTo.close();

        System.out.println("spend time: [" + (System.currentTimeMillis() - l) + "]");
    }


    public static void copy() throws Exception {
        // 设置输入源 & 输出地 = 文件
        String infile = "C:\\copy.sql";
        String outfile = "C:\\copy.txt";

        // 1. 获取数据源 和 目标传输地的输入输出流（此处以数据源 = 文件为例）
        FileInputStream fin = new FileInputStream(infile);
        FileOutputStream fout = new FileOutputStream(outfile);

        // 2. 获取数据源的输入输出通道
        FileChannel fcin = fin.getChannel();
        FileChannel fcout = fout.getChannel();

        // 3. 创建缓冲区对象
        ByteBuffer buff = ByteBuffer.allocate(1024);

        while (true) {

            // 4. 从通道读取数据 & 写入到缓冲区
            // 注：若 以读取到该通道数据的末尾，则返回-1
            int r = fcin.read(buff);
            if (r == -1) {
                break;
            }
            // 5. 传出数据准备：调用flip()方法
            buff.flip();

            // 6. 从 Buffer 中读取数据 & 传出数据到通道
            fcout.write(buff);

            // 7. 重置缓冲区
            buff.clear();

        }
    }


}


