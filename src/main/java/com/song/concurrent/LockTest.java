package com.song.concurrent;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockTest {

    public static void main(String[] args) {
        System.out.println("111111");
        long start = System.currentTimeMillis();
        LockSupport.parkNanos(1000*1000*10000L);

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

//        while (true) {
//            LockSupport.parkNanos(1);
//        }



        System.out.println("111111");
        long end = System.currentTimeMillis();

        System.out.println(end - start);


    }
}
