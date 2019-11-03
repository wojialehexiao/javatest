package com.song.concurrent;

public class ThreadLocalTest {

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            ThreadLocalModel.set();

            System.out.println(ThreadLocalModel.get());
        }

        Thread.sleep(1000);

        new Thread(() -> {
            ThreadLocalModel.set();
            System.out.println(ThreadLocalModel.get());
        }).start();

        Thread.sleep(1000);
        System.out.println(ThreadLocalModel.get());
    }
}
