package com.song.snappy;

import org.xerial.snappy.Snappy;
import redis.clients.jedis.Jedis;

import java.io.*;

public class SnappyTest {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("snappyTest.txt")));
        String text = bufferedReader.readLine();

        bufferedReader.close();
        byte[] compress = Snappy.compress(text);

        FileOutputStream fileOutputStream = new FileOutputStream("snappyText.bit");

        fileOutputStream.write(compress);

        fileOutputStream.flush();
        fileOutputStream.close();

        System.out.println(compress);


        Jedis jedis = new Jedis();
        jedis.set("ddd".getBytes(), compress);

        jedis.set("eee", text);

        byte[] bytes = jedis.get("ddd".getBytes());

        byte[] uncompress = Snappy.uncompress(bytes);
        System.out.println(new String(uncompress));


    }
}
